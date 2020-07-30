package com.wang.controller.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfigurer implements WebMvcConfigurer{
	  @Autowired
	  private LoginInterceptor loginInterceptor;
	 
	  // 这个方法是用来配置静态资源的，比如html，js，css，等等
	  	@Override
	   public void addInterceptors(InterceptorRegistry registry) {
	        //注册TestInterceptor拦截器
	        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);
	        registration.addPathPatterns("/**");                      //所有路径都被拦截
	        registration.excludePathPatterns(                         //添加不拦截路径
	                                         "/login.html",			//登录
	                                         "/loginMainPethod",
	                                         "/**/*.html",            //html静态资源
	                                         "/**/*.js",              //js静态资源
	                                         "/**/*.css",             //css静态资源
	                                         "/**/*.woff",
	                                         "/**/*.ttf"
	                                         );    
	    }
	 
	  // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
	 /* @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    // addPathPatterns("/**") 表示拦截所有的请求，
	    // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
	    registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login", "/register");
	  }*/
 
}
