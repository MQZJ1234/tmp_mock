package com.tmp.api;

import com.tmp.common.ResultMap;
import com.tmp.pojo.Person;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@RestController
@RequestMapping(value = "/api")
public class TmpController {


    @PostMapping(value = "/register")
    public ResultMap register(String username, String password) {

        int code = 200;
        String msg = "";
        Person person = null;

        if (StringUtils.isEmpty(username)) {
            code = 500;
            msg = "用户名不为空";
        } else if (StringUtils.isEmpty(password)) {
            code = 500;
            msg = "密码不为空";
        } else if (username.trim().length() < 2 || username.trim().length() > 16) {
            code = 500;
            msg = "用户名长度[2,16]";
        } else if (password.trim().length() < 6 || password.trim().length() > 16) {
            code = 500;
            msg = "密码长度[6,16]";
        } else {
            person = new Person();
            person.setUsername(username);
            person.setPassword(password);

            code = 200;
            msg = "注册成功~";
        }
        return new ResultMap<Person>(code, msg, person);
    }

    @PostMapping(value = "/dbregister")
    public ResultMap register(@RequestBody Person person) {

        int code = 200;
        String msg = "";

        if (StringUtils.isEmpty(person.getUsername())) {
            code = 500;
            msg = "用户名不为空";
        } else if (StringUtils.isEmpty(person.getPassword())) {
            code = 500;
            msg = "密码不为空";
        } else if (person.getUsername().trim().length() < 2 || person.getUsername().trim().length() > 16) {
            code = 500;
            msg = "用户名长度[2,16]";
        } else if (person.getPassword().trim().length() < 6 || person.getPassword().trim().length() > 16) {
            code = 500;
            msg = "密码长度[6,16]";
        } else {
            code = 200;
            msg = "注册成功~";
        }
        return new ResultMap<Person>(code, msg, person);
    }

    @GetMapping(value = "/find")
    public Person testGet(String username, String mobile) {
        Person p = new Person();
        p.setMobile(mobile);
        p.setUsername(username);
        return p;
    }

    @GetMapping(value = "/testCookies")
    public Person testCookies(HttpServletResponse response) {
        Cookie cookie = new Cookie("sid1", "testCookies");
        response.addCookie(cookie);

        Person person = new Person();
        person.setUsername("testCookies");

        return person;
    }

    @GetMapping(value = "/testGetWithCookies")
    public Person testGetWithCookies(HttpServletRequest request, HttpServletResponse response) {

        Enumeration<String> headerNames = request.getHeaderNames();

        System.out.println("testGetWithCookies 值: ");

        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String header = request.getHeader(name);
            System.out.println(name + "==" + header);
        }

        System.out.println("----------------------------------------");
        Cookie[] cookies = request.getCookies();

        Cookie cookie1 = new Cookie("sid2-1", "testGetWithCookies--1");
        // cookie1.setDomain("www");
        cookie1.setMaxAge(2000);
        Cookie cookie2 = new Cookie("sid2-2", "testGetWithCookies--2");
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        Person person = new Person();
        person.setUsername("testGetWithCookies");

        return person;
    }

    @PostMapping(value = "/testPostWithCookie")
    public ResultMap testPostWithCookie(@RequestBody Person person, HttpServletRequest request, HttpServletResponse response) {

        int code = 200;
        String msg = "成功";

        System.out.println("testPostWithCookie 值: ");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String header = request.getHeader(name);
            System.out.println(name + "==" + header);
        }

        System.out.println("----------------------------------------");

        Cookie cookie1 = new Cookie("sid2-1", "testGetWithCookies--1");
        // cookie1.setDomain("www");
        cookie1.setMaxAge(2000);
        Cookie cookie2 = new Cookie("sid2-2", "testGetWithCookies--2");
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        person.setUsername("testGetWithCookies");

        return new ResultMap<Person>(code, msg, person);
    }

    @PostMapping(value = "/testPostFormWithCookie")
    public ResultMap testPostFormWithCookie( Person person, HttpServletRequest request, HttpServletResponse response) {

        int code = 200;
        String msg = "成功";

        System.out.println("testPostFormWithCookie 值: ");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String header = request.getHeader(name);
            System.out.println(name + "==" + header);
        }

        System.out.println("----------------------------------------");

        Cookie cookie1 = new Cookie("sid2-1", "testGetWithCookies--1");
        cookie1.setMaxAge(2000);
        Cookie cookie2 = new Cookie("sid2-2", "testGetWithCookies--2");
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        person.setUsername("testPostFormWithCookie");

        return new ResultMap<Person>(code, msg, person);
    }
}
