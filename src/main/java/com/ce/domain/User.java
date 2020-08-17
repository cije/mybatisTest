package com.ce.domain;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class User implements Serializable {
    private int id;
    private String username;
    private LocalDateTime birthday;
    private String sex;
    private String address;

    /**
     * 一对多关系映射：主表实体应包含从表实体的集合
     */
    private List<Account> accounts;

    /**
     * 多对多的关系映射：一个用户可以具备多个角色
     */
    private List<Role> roles;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String birthdayStr = formatter.format(birthday);
        String accountsStr = accounts == null || accounts.isEmpty() ? "" : accounts.toString();
        String roleStr = roles == null || roles.isEmpty() ? "" : roles.toString();
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday=" + birthdayStr +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                "}  " + accountsStr + roleStr;
    }
}
