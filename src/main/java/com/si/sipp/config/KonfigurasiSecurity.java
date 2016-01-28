package com.si.sipp.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebMvcSecurity
public class KonfigurasiSecurity extends WebSecurityConfigurerAdapter {

    private static final String SQL_LOGIN
            = "select username, password, active as enabled "
            + "from c_security_user where username = ?";

    private static final String SQL_PERMISSION
            = "select u.username, r.nama as authority "
            + "from c_security_user u join s_user_role ur on u.id = ur.id_user "
            + "join s_roles r on ur.id_role = r.id "
            + "where u.username = ?";

    @Autowired
    private DataSource ds;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(ds)
                .usersByUsernameQuery(SQL_LOGIN)
                .authoritiesByUsernameQuery(SQL_PERMISSION);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/","/home").hasAnyRole("ADMIN", "STAFF")
                .antMatchers("/master/customer").hasAnyRole("ADMIN", "STAFF")
                .antMatchers("/master/supplier").hasAnyRole("ADMIN", "STAFF")
                .antMatchers("/master/item").hasAnyRole("ADMIN", "STAFF")
                .antMatchers("/master/pertamina").hasAnyRole("ADMIN", "STAFF")
                .antMatchers("/master/pertamina_list").hasAnyRole("ADMIN", "STAFF")
                .antMatchers("/master/pertamina_form").hasAnyRole("ADMIN", "STAFF")
                .antMatchers("/transaksi/pembelian_list").hasAnyRole("ADMIN", "STAFF")
                .antMatchers("/transaksi/pembelian_form").hasAnyRole("ADMIN", "STAFF")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home",true)
                .and()
            .logout()
            .and()
            .addFilterAfter(new CsrfAttributeToCookieFilter(), CsrfFilter.class)
            .csrf().csrfTokenRepository(csrfTokenRepository());
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}
