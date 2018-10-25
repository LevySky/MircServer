package com.mirc.serverauth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Collection;


@Entity
public class UserInfo implements Serializable, UserDetails {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    private String username;
    private String password;
    private String role;
    private String editIp;
    private String editUser;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public UserInfo(){

    }

    public UserInfo(Integer id,String username, String password, String role, boolean accountNonExpired, boolean accountNonLocked,
                    boolean credentialsNonExpired, boolean enabled) {
        // TODO Auto-generated constructor stub
        this.username = username;
        this.password = password;
        this.role = role;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // 这是权限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    public void setPassword( String password){
        this.password = password;
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return accountNonExpired;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return accountNonLocked;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return credentialsNonExpired;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }


    public String getEditIp() {
        return editIp;
    }

    public void setEditIp(String editIp) {
        this.editIp = editIp;
    }

    public String getEditUser() {
        return editUser;
    }

    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }
}
