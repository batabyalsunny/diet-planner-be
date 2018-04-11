package com.mindfire.dietplanner;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		// Basic API Users
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled from api_users where username = ?")
				.authoritiesByUsernameQuery("select username, role from api_users where username = ?");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Admin API user
		auth.inMemoryAuthentication().withUser("dieat.mail@gmail.com").password("5f4dcc3b5aa765d61d8327deb882cf99").authorities("ROLE_ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Security configurations
		http.authorizeRequests()
				.anyRequest().fullyAuthenticated()
			.and()
				.httpBasic()
			.and()
				.csrf().disable();
	}
}
