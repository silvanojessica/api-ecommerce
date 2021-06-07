package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	    @Autowired
	    private UsuarioServiceImpl usuarioService;
	    @Autowired
	    private JwtService jwtService;

	    @Bean
	    public PasswordEncoder passwordEncoder(){
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public OncePerRequestFilter jwtFilter(){
	        return new JwtAuthFilter(jwtService, usuarioService);
	    }

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	            .userDetailsService(usuarioService)
	            .passwordEncoder(passwordEncoder());
	    }

	    @Override
	    protected void configure( HttpSecurity http ) throws Exception {
	        http
	            .csrf().disable()
	            .authorizeRequests()
	                .antMatchers("/api/clientes/**")
	                    .hasAnyRole("USER", "ADMIN")
	                .antMatchers("/api/pedidos/**")
	                    .hasAnyRole("USER", "ADMIN")
	                .antMatchers("/api/produtos/**")
	                    .hasRole("ADMIN")
	                .antMatchers(HttpMethod.POST, "/api/usuarios/**")
	                    .permitAll()
	                .anyRequest().authenticated()
	            .and()
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	                .addFilterBefore( jwtFilter(), UsernamePasswordAuthenticationFilter.class);
	        ;
	    }

	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers(
	                "/v2/api-docs",
	                "/configuration/ui",
	                "/swagger-resources/**",
	                "/configuration/security",
	                "/swagger-ui.html",
	                "/webjars/**");
}
