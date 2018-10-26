package com.mirc.serverauth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;


@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    public static final String RESOURCE_ID = "auth";


    @Value("${jwt.certificate.store.file}")
    private Resource keystore;

    @Value("${jwt.certificate.store.password}")
    private String keystorePassword;

    @Value("${jwt.certificate.key.alias}")
    private String keyAlias;

    @Value("${jwt.certificate.key.password}")
    private String keyPassword;


    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;


    @Primary
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setAccessTokenValiditySeconds(300);
//        defaultTokenServices.setRefreshTokenValiditySeconds(-1);
//        defaultTokenServices.setSupportRefreshToken(true);
//        defaultTokenServices.setReuseRefreshToken(false);
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenServices(tokenServices())
                .accessTokenConverter(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
        ;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                keystore, keystorePassword.toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(
                keyAlias, keyPassword.toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair);
        return converter;
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("html")
//                .resourceIds(RESOURCE_ID)
//                .authorities("ADMIN")
                .scopes("myscope") //此处的scopes是无用的，可以随意设置
                .secret(passwordEncoder.encode("html"))//
                .authorizedGrantTypes("authorization_code")
                .autoApprove(true)
                .redirectUris("http://localhost:9060/r/login","http://localhost:9061/app/login","http://user-20181013xm:9060/r/login","http://localhost:9066/at/getToken")

//                .accessTokenValiditySeconds(30)
//                .refreshTokenValiditySeconds(1800);
        ;
    }

    @Bean
    public ApprovalStore approvalStore() {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore());
        return store;
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
        // 需要使用 redis 的话，放开这里
//            return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
//                .realm(RESOURCE_ID)
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients()
        ;
    }

}
