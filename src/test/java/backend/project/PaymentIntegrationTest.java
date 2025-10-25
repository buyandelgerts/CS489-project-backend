package backend.project;

import backend.project.repository.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class PaymentIntegrationTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private PaymentRepository paymentRepository;
//
//    private String generateJwtToken() throws Exception {
//        String response = mockMvc.perform(post("/api/auth/login")
//        .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"username\":\"user\",\"password\":\"pass123\"}"))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        return new ObjectMapper().readTree(response).get("token").asText();
//    }
//
//    @Test
//    void shouldCreatePaymentSuccessfully() throws Exception {
//        String token = generateJwtToken();
//        System.out.println("token: " + token);
//        mockMvc.perform(post("/payment")
//                .header("Authorization", "Bearer " + token)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"paymentDate\": \"2025-10-22\",\"amount\": 20.0,\"method\": \"Cash\"}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", notNullValue()))
//                .andExpect(jsonPath("$.amount", is(20.0)));
//    }
//
//    @Test
//    void shouldRejectPaymentWithoutToken() throws Exception {
//        mockMvc.perform(post("/payment")
//        .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"amount\": 20}"))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    void shouldReturnBadRequestForInvalidAmount() throws Exception {
//        String token = generateJwtToken();
//        mockMvc.perform(post("/payment")
//                .header("Authorization", "Bearer " + token)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"paymentDate\": \"2025-10-22\",\"amount\": -15,\"method\": \"Cash\"}"))
//                .andExpect(status().isInternalServerError());
//    }
//}
