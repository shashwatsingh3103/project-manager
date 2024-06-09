package com.springboot.projectmanager.securityconfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class appconfig {

	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
		
		http.sessionManagement(sesson-> sesson.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(auth-> auth.requestMatchers("/api/**").authenticated()
			.anyRequest()	
			.permitAll())
		.addFilterBefore(new tokenvalidatorfilter(), BasicAuthenticationFilter.class)
		.csrf(csrf->csrf.disable())
		.cors(cors->cors.configurationSource(corsconfigsource()));
		return http.build();
		
	
	}

	private CorsConfigurationSource corsconfigsource() {
	   return new CorsConfigurationSource() {
		
		@Override
		public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
			CorsConfiguration cfg= new CorsConfiguration();
			List<String> list= new ArrayList<>();
			list.add( "http://localhost:5173");
			list.add("http://localhost:8989");
			cfg.setAllowedOriginPatterns(list);
			List<String> list2= new ArrayList<>();
			list2.add("Authorization");
			cfg.addAllowedMethod("*");
			cfg.addAllowedHeader("*");
			cfg.setAllowCredentials(true);
			cfg.setExposedHeaders(list2);
			cfg.setMaxAge(3600L);
			return cfg;
			
		}
	};
		
	}
	@Bean
PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}
	

	
}
