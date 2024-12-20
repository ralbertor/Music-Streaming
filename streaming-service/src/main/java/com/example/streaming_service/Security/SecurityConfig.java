
/** 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig {
   
   @Bean
   public UserDetailsService UserDetailsService(){
      return new InMemoryUserDetailsManager(
         User.withUsername("admin")
            .password("{noop}adminpassword")
            .roles("ADMIN")
            .build(),
         User.withUsername("user")
            .password("{noop}userpassword")
            .roles("USER")
            .build(),
         User.withUsername("creator")
            .password("{noop}creatorpassword")
            .roles("CREATOR")
            .build()
      );
   }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(UserDetailsService());
        return authenticationManagerBuilder.build();
    }
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http.csrf(csrf -> csrf.disable())
         .authorizeHttpRequests(auth -> auth
        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() //Swagger permitido sin autenticación
        .requestMatchers("/api/generos/{id}").hasAnyRole("USUARIO","ADMIN", "CREADOR")
        .requestMatchers("/api/generos/**").hasAnyRole("ADMIN", "CREADOR")
        .requestMatchers("/api/canciones/{id}").hasAnyRole("USUARIO", "CREADOR", "ADMIN") //usuarios logueados
        .requestMatchers("/api/canciones/**").hasAnyRole("ADMIN", "CREADOR")
        .requestMatchers("/api/canciones/crearConGeneros").hasAnyRole("ADMIN", "CREATOR")
        .requestMatchers("/api/artistas/{id}").hasAnyRole("USUARIO","ADMIN", "CREADOR")
        .requestMatchers("/api/artistas/**").hasAnyRole("ADMIN", "CREADOR")
        .requestMatchers("/api/artistas/crearConAlbumYCanciones").hasAnyRole("ADMIN", "CREATOR")
        .requestMatchers("/api/albumes/{id}").hasAnyRole("USUARIO","ADMIN", "CREADOR")
        .requestMatchers("/api/albumes/**").hasAnyRole("ADMIN", "CREADOR")
        .anyRequest().authenticated())
        .formLogin(form -> form.defaultSuccessUrl("/swagger-ui/index.html", true))
        .httpBasic(httpBasic -> httpBasic.disable());
        return http.build();   
   }

}
    */
