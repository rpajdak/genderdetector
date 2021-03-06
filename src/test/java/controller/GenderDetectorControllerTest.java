package controller;

import com.rpajdak.genderdetector.gender.Gender;
import com.rpajdak.genderdetector.service.GendersService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfig.class})
@WebAppConfiguration
public class GenderDetectorControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private GendersService gendersService;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void when_correct_input_then_return_200() throws Exception {
        //when:
        when(gendersService.getGender("Adela", "first")).thenReturn(Gender.FEMALE);

        //then:
        mockMvc.perform(get("/api/v1/gender/first/Adela"))
                .andExpect(status().isOk());
    }
}