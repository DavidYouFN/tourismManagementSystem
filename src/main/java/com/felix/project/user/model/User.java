package com.felix.project.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felix.project.user.validate.IsMobile;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class User {
    private String id;

    @NotBlank(message="用户名不能为空")
    private String username;

    private String gender;

    @NotBlank(message="手机号码不能为空")
    @IsMobile
    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    @NotBlank(message="邮箱不能为空")
    @Pattern(regexp="[a-za-z0-9._%+-]+@[a-za-z0-9.-]+\\.[a-za-z]{2,4}", message="邮件格式错误")
    private String email;

    @NotBlank(message="身份证号码不能为空")
    @Pattern(regexp="^(\\d{18,18}|\\d{15,15}|(\\d{17,17}[x|X]))$", message="身份证格式错误")
    private String identityid;

    @NotBlank(message="密码不能为空")
    @Size(min=6, max=20, message="密码长度只能在6-20之间")
    private String password;

    private String area;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIdentityid() {
        return identityid;
    }

    public void setIdentityid(String identityid) {
        this.identityid = identityid == null ? null : identityid.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }
}