package com.mirc.serverauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        //这里可以可以通过username（登录时输入的用户名）然后到数据库中找到对应的用户信息，并构建成我们自己的UserInfo来返回。
       UserInfo user =  repository.findByUsername(username);

        if(user != null)
        {
            //System.out.println("loadUserByUsername___"+user.getPassword());
            //假设返回的用户信息如下;
          //  UserInfo userInfo=new UserInfo("a", "123", "ROLE_ADMIN", true,true,true, true);
            return user;

        }

        return null;
    }
}


