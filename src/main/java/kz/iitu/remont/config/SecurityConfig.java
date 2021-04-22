package kz.iitu.remont.config;

import kz.iitu.remont.service.impl.ReparierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ReparierServiceImpl reparierService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/repariers/signUp/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/devices/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/repariers/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/centers/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/finishedDevices/**").permitAll()
                .antMatchers("/api/repariers/**").hasAnyAuthority("ADMIN", "REPARIER")
                .antMatchers("/api/repariers/username/**").hasAnyAuthority("ADMIN", "REPARIER")
                .antMatchers(HttpMethod.DELETE,"/api/repariers/**").hasAnyAuthority("ADMIN")
                .antMatchers("/api/centers/**").hasAnyAuthority("ADMIN")
                .antMatchers("/api/finishedDevices/**").hasAnyAuthority("REPARIER")
                .antMatchers(HttpMethod.GET,"/api/devices/**").hasAnyAuthority("REPARIER", "ADMIN")
                .anyRequest().authenticated() // Rest accesses available for all authorized users
                .and()
                .addFilter(new JwtTokenGeneratorFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(reparierService)
                .passwordEncoder((passwordEncoder()));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
