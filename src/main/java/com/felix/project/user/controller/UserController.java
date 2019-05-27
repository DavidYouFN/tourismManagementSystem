package com.felix.project.user.controller;

import com.alipay.api.AlipayApiException;
import com.felix.project.user.model.Shop;
import com.felix.project.commonConfig.util.JsonUtil;
import com.felix.project.commonConfig.util.MD5Util;
import com.felix.project.commonConfig.util.StaticProperties;
import com.felix.project.commonConfig.util.UUIDUtils;
import com.felix.project.user.model.Seller;
import com.felix.project.user.model.User;
import com.felix.project.user.service.AdminService;
import com.felix.project.user.service.SellerService;
import com.felix.project.user.service.UserPropertyService;
import com.felix.project.user.service.UserService;
import com.felix.project.user.util.RandomValidateCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author fangyong
 * @Date 2019/4/11 17:22
 **/

@RestController
@RequestMapping("/user")
@Api(tags = "用户API")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    SellerService sellerService;

    @Resource
    UserPropertyService userPropertyService;

    @Resource
    AdminService adminService;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * @Author fangyong
     * @Description 用户注册账号
     * @Date 2019/4/13 20:27
     * @Param
     * @return
     **/
    @ApiOperation(value = "用户注册账号" ,  notes="用户注册账号")
    @RequestMapping(value = "/userRegister",method = {RequestMethod.POST, RequestMethod.GET})
    public String userRegister(@Valid User user,BindingResult error){
        if (error.hasErrors()){
            return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_FAIL,error.getFieldError().getDefaultMessage(),"");
        }
        return userService.userRegister(user);
    }

    /**
     * @Author fangyong
     * @Description 登录
     * @Date 2019/4/13 23:53 
     * @Param 
     * @return 
     **/
    @ApiOperation(value = "登录" ,  notes="登录")
    @RequestMapping(value = "/login",method = {RequestMethod.POST, RequestMethod.GET})
    public String login(HttpServletRequest request, String username, String password) {
        String MD5Password = MD5Util.makeMD5(password);
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username,MD5Password);
            org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            User user = userService.getUserByUsername(username);
            subject.getSession().setTimeout(1000*60*30);
            request.getSession().setAttribute(StaticProperties.SESSION_USER,user);
            return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS, StaticProperties.RESPONSE_MESSAGE_SUCCESS, user);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_FAIL, StaticProperties.RESPONSE_MESSAGE_FAIL, "Fail to login!");
        }
    }
    
    /**
     * @Author fangyong
     * @Description 注销
     * @Date 2019/4/14 0:21 
     * @Param 
     * @return 
     **/
    @ApiOperation(value = "注销" ,  notes="注销")
    @RequestMapping(value = "/loginOut",method = RequestMethod.POST)
    public String loginOut(){
        SecurityUtils.getSubject().logout();
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,"注销成功","");
    }

    /**
     * @Author fangyong
     * @Description 修改密码
     * @Date 2019/4/14 0:24
     * @Param
     * @return
     **/
    @ApiOperation(value = "修改密码" ,  notes="修改密码")
    @RequestMapping(value = "/modifyPassword", method = {RequestMethod.POST,RequestMethod.GET})
    public String modifyPassword(String username, String password){
        return userService.modifyPassword(username,password);
    }

    /**
     * @Author fangyong
     * @Description 获取个人信息
     * @Date 2019/4/14 0:59
     * @Param
     * @return
     **/
    @ApiOperation(value = "获取登录用户信息" ,  notes="获取登录用户信息")
    @RequestMapping(value = "/getUserInfo", method = {RequestMethod.POST,RequestMethod.GET})
    public String getUserInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute(StaticProperties.SESSION_USER) == null) {
            return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_FAIL, StaticProperties.RESPONSE_MESSAGE_FAIL, "用户未登录");
        }else if (request.getSession(false) == null){
            return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_FAIL, StaticProperties.RESPONSE_MESSAGE_FAIL, "session已过期");
        }
        User userInfo = (User) session.getAttribute(StaticProperties.SESSION_USER);
        User user = new User();
        user.setBirth(userInfo.getBirth());
        user.setUsername(userInfo.getUsername());
        user.setArea(userInfo.getArea());
        user.setGender(userInfo.getGender());
        user.setEmail(userInfo.getEmail());
        user.setPhone(userInfo.getPhone());
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,user);
    }
    
    /**
     * @Author fangyong
     * @Description 生成验证码
     * @Date 2019/4/14 17:39 
     * @Param 
     * @return 
     **/
    @ApiOperation(value = "生成验证码" ,  notes="生成验证码")
    @RequestMapping(value = "/getVerify",method={RequestMethod.POST,RequestMethod.GET})
    public String getVerify(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Access-Control-Allow-Origin","true");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        }catch (Exception e){
            logger.error("获取验证码失败",e);
        }
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    /**
     * @Author fangyong
     * @Description 校验验证码
     * @Date 2019/4/14 17:41
     * @Param
     * @return
     **/
    @ApiOperation(value = "校验验证码" ,  notes="校验验证码")
    @RequestMapping(value = "/checkVerify",method = {RequestMethod.POST,RequestMethod.GET})
    public String checkVerify(String inputStr, HttpSession session) {
        try{
            //从session中获取随机数
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            if (random == null) {
                return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_FAIL,StaticProperties.RESPONSE_MESSAGE_FAIL,"");
            }
            if (random.equals(inputStr)) {
                return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
            } else {
                return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_FAIL,StaticProperties.RESPONSE_MESSAGE_FAIL,"");
            }
        }catch (Exception e){
            logger.error("验证码校验失败",e);
            return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_FAIL,StaticProperties.RESPONSE_MESSAGE_FAIL,"");
        }
    }


    /**
     * @Author fangyong
     * @Description 管理员登录
     * @Date 2019/5/18 13:09
     * @Param
     * @return
     **/
    @ApiOperation(value = "管理员登录" ,  notes="管理员登录")
    @RequestMapping(value = "/adminLogin",method = {RequestMethod.POST, RequestMethod.GET})
    public String adminLogin(HttpServletRequest request,String adminName, String adminPassword){
        return adminService.adminLogin(adminName,adminPassword);
    }

    /**
     * @Author fangyong
     * @Description 用户充值
     * @Date 2019/4/21 14:05
     * @Param
     * @return
     **/
    @ApiOperation(value = "用户充值" ,  notes="用户充值")
    @RequestMapping(value = "/addProperty",method = {RequestMethod.POST, RequestMethod.GET})
    public String addProperty(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                              @RequestParam(value = "property", required = false) String property,
                              String userId) throws AlipayApiException, IOException {
        return userPropertyService.addProperty(httpRequest,httpResponse,userId, property);
    }

    /**
     * @Author fangyong
     * @Description 查看我的钱包
     * @Date 2019/4/25 12:51
     * @Param
     * @return
     **/
    @ApiOperation(value = "查看我的钱包" ,  notes="查看我的钱包")
    @RequestMapping(value = "/getWalletInfo",method = {RequestMethod.POST, RequestMethod.GET})
    public String getWalletInfo(HttpServletRequest request,String userId){
        return userPropertyService.getWalletInfo(userId);
    }
    
    /**
     * @Author fangyong
     * @Description 添加店铺
     * @Date 2019/5/9 13:49 
     * @Param 
     * @return 
     **/
    @ApiOperation(value = "添加店铺" ,  notes="添加店铺")
    @RequestMapping(value = "/addShop",method = {RequestMethod.POST, RequestMethod.GET})
    public String addShop(Shop shop,Seller seller){
        return userService.addShop(shop,seller);
    }

    /**
     * @Author fangyong
     * @Description 获取用户列表
     * @Date 2019/5/10 23:41
     * @Param
     * @return
     **/
    @ApiOperation(value = "获取用户列表" ,  notes="获取用户列表")
    @RequestMapping(value = "/getUserList",method = {RequestMethod.POST, RequestMethod.GET})
    public String getUserList(){
        return userService.getUserList();
    }

    /**
     * @Author fangyong
     * @Description 获取商家列表
     * @Date 2019/5/10 23:59
     * @Param
     * @return
     **/
    @ApiOperation(value = "获取商家列表" ,  notes="获取商家列表")
    @RequestMapping(value = "/getSellerList",method = {RequestMethod.POST, RequestMethod.GET})
    public String getSellerList(){
        return sellerService.getSellerList();
    }

    /**
     * @Author fangyong
     * @Description 获取管理员列表
     * @Date 2019/5/13 22:00
     * @Param
     * @return
     **/
    @ApiOperation(value = "获取管理员信息" ,  notes="获取管理员列表")
    @RequestMapping(value = "/getAdminInfo",method = {RequestMethod.POST, RequestMethod.GET})
    public String getAdminList(){
        return adminService.getAdminInfo();
    }

    /**
     * @Author fangyong
     * @Description 删除商家
     * @Date 2019/5/17 17:28
     * @Param
     * @return
     **/
    @ApiOperation(value = "删除商家" ,  notes="删除商家")
    @RequestMapping(value = "/delSeller",method = {RequestMethod.POST, RequestMethod.GET})
    public String delSeller(String sellerId){
        return sellerService.delSeller(sellerId);
    }

    /**
     * @Author fangyong
     * @Description 修改店铺信息
     * @Date 2019/5/18 11:11
     * @Param
     * @return
     **/
    @ApiOperation(value = "修改店铺信息" ,  notes="修改店铺信息")
    @RequestMapping(value = "/modifyShop",method = {RequestMethod.POST, RequestMethod.GET})
    public String modifySeller(Seller seller){
        return sellerService.modifySeller(seller);
    }
    
    /**
     * @Author fangyong
     * @Description 根据userId获取用户信息
     * @Date 2019/5/22 0:20 
     * @Param 
     * @return 
     **/
    @ApiOperation(value = "根据userId获取用户信息" ,  notes="根据userId获取用户信息")
    @RequestMapping(value = "/getUserInfoByUserId",method = {RequestMethod.POST, RequestMethod.GET})
    public String getUserInfoByUserId(String userId){
        return userService.getUserInfoByUserId(userId);
    }

    /**
     * @Author fangyong
     * @Description 修改用户个人信息
     * @Date 2019/5/24 9:08
     * @Param
     * @return
     **/
    @ApiOperation(value = "修改用户个人信息" ,  notes="修改用户个人信息")
    @RequestMapping(value = "/modifyUserInfo",method = {RequestMethod.POST, RequestMethod.GET})
    public String modifyUserInfo(User user){
        return userService.modifyUserInfo(user);
    }
    
    /**
     * @Author fangyong
     * @Description 获取用户数量
     * @Date 2019/5/25 23:34 
     * @Param 
     * @return 
     **/
    @ApiOperation(value = "获取用户数量" ,  notes="获取用户数量")
    @RequestMapping(value = "/getUserCount",method = {RequestMethod.POST, RequestMethod.GET})
    public String getUserCount(){
        return userService.getUserCount();
    }
    
    /**
     * @Author fangyong
     * @Description 获取商家数量
     * @Date 2019/5/25 23:57 
     * @Param 
     * @return 
     **/
    @ApiOperation(value = "获取商家数量" ,  notes="获取商家数量")
    @RequestMapping(value = "/getSellerCount",method = {RequestMethod.POST, RequestMethod.GET})
    public String getSellerCount(){
        return userService.getSellerCount();
    }
    
    /**
     * @Author fangyong
     * @Description 获取当日注册用户数量
     * @Date 2019/5/26 0:21 
     * @Param 
     * @return 
     **/
    @ApiOperation(value = "获取当日注册用户数量" ,  notes="获取当日注册用户数量")
    @RequestMapping(value = "/getUserCountOfToday",method = {RequestMethod.POST, RequestMethod.GET})
    public String getUserCountOfToday(){
        return userService.getUserCountOfToday();
    }

    /**
     * @Author fangyong
     * @Description 获取一星期注册用户数量
     * @Date 2019/5/26 18:16
     * @Param
     * @return
     **/
    @ApiOperation(value = "获取一星期注册用户数量" ,  notes="获取一星期注册用户数量")
    @RequestMapping(value = "/getUserCountOfDate",method = {RequestMethod.POST, RequestMethod.GET})
    public String getUserCountOfSevenDay(String item){
        return userService.getUserCountOfSevenDay(item);
    }

}
