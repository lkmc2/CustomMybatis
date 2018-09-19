package com.lin.full.entity;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description 用户
 */
public class User {

    private int id;
    private int age;
    private String name;

    public User() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

}
