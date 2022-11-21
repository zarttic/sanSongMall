/**
 * {@code @Description}  拦截器
 *
 * @author liyajun
 * {@code @create}          2022-11-17 22:31
 */

package com.group7.sanSongMall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    /**
     * 解决跨域请求
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true);
    }

    /**
     * 异步请求配置
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }

    /**
     * 配置拦截器、拦截路径
     * 每次请求到拦截的路径，就会去执行拦截器中的方法
     * @param
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        //排除拦截，除了注册登录(此时还没token)，其他都拦截
        excludePath.add("/sysController/login/**");  //登录
        excludePath.add("/sysController/register/**");     //注册
        excludePath.add("/sysController/info/**");     //注册
        excludePath.add("/sysController/exit/**");     //注册
        excludePath.add("/sysController/getVerifyCodeImage/**");     //验证码
        excludePath.add("/test/**");
        excludePath.add("/favicon.ico");
        //验证码

//        excludePath.add("/static/**");  //静态资源
//        excludePath.add("/assets/**");  //静态资源
        registry
                .addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath).excludePathPatterns("/swagger-resources/**","/swagger-ui/**", "/v3/**", "/error");
        WebMvcConfigurer.super.addInterceptors(registry);

    }
}
