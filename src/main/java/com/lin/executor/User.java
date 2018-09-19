package com.lin.executor;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description 用户
 */
public class User {

    private Integer id;
    private Integer age;
    private String name;

    public User() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAge(Integer age) {
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
