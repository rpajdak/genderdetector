package controller;

import com.rpajdak.genderdetector.controller.GenderDetectorController;
import com.rpajdak.genderdetector.service.GendersService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
public class AppTestConfig {

    @Bean
    public GendersService namesService() {
        return Mockito.mock(GendersService.class);
    }

    @Bean
    public GenderDetectorController genderDetectorController() {
        return new GenderDetectorController(namesService());
    }

}
