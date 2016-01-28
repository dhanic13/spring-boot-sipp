package com.si.sipp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

@Configuration
public class KonfigurasiWeb extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/master/customer").setViewName("master/customer");
        registry.addViewController("/master/supplier").setViewName("master/supplier");
        registry.addViewController("/master/item").setViewName("master/item");
        //registry.addViewController("/master/pertamina").setViewName("master/pertamina");
        registry.addViewController("/master/pertamina_list").setViewName("master/pertamina_list");
        registry.addViewController("/master/pertamina_form").setViewName("master/pertamina_form");
        registry.addViewController("/transaksi/pembelian_list").setViewName("transaksi/pembelian_list");
        registry.addViewController("/transaksi/pembelian_form").setViewName("transaksi/pembelian_form");
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ThymeleafLayoutInterceptor());
    } 
    
    @Bean
    public JasperReportsViewResolver getJasperReportsViewResolver() {
        JasperReportsViewResolver resolver = new JasperReportsViewResolver();
        resolver.setPrefix("classpath:/reports/");
        resolver.setSuffix(".jrxml");
        resolver.setViewNames("report_*");
        resolver.setViewClass(JasperReportsMultiFormatView.class);
        resolver.setOrder(0);
        return resolver;
    }
}
