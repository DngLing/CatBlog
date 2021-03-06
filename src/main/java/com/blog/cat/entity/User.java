package com.blog.cat.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author yangyang
 */
@Data
public class User {
    private String uid;
    private String pwd;
    private long phone;
    private String email;
    private String nickname;
    private int gender;
    private Date birth;
    private int level;
    private int role;
    private String info;
    private String profession;
}
