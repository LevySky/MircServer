package com.mirc.serverauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true) // Allow method annotations like @PreAuthorize
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(OAuth2ServerConfig.RESOURCE_ID).stateless(true);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {


        http.httpBasic().disable();
        http.authorizeRequests().antMatchers("/actuator/**","/getToken","/api/**", "/bak/**", "/css/**", "/ctrl/**",
                "/fonts/**", "/img/**", "/images/**","/js/*/**", "/l10n/**", "/tpl/**", "/login/**", "/vendor/**").permitAll().anyRequest().authenticated();
    }

}
