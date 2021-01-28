package com.my.business;

import com.my.business.dao.ItemDao;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 *
 * @author 李自豪（zihao.li01@ucarinc.com）
 * @since 2021/1/17
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    ItemDao itemDao;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                Object loginId = request.getSession().getAttribute("loginId");
                if (loginId == null) {
                    request.getRequestDispatcher("/toLogin").forward(request, response);
                    return false;
                }
                return true;
            }
        }).addPathPatterns("/item/**");

        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                if (request.getSession().getAttribute("allType") == null) {
                    request.getSession().setAttribute("allType", itemDao.findAllType());
                }
                return true;
            }
        }).addPathPatterns("/**").excludePathPatterns("/toLogin");
    }
}
