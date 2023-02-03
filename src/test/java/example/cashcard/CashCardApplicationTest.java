package example.cashcard;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CashCardApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnACashCardWhenDataIsSaved() {
        // when
        var response = restTemplate.getForEntity("/cashcards/99", String.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        var documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        Double amount = documentContext.read("$.amount");
        assertThat(id).isNotNull();
        assertThat(id).isEqualTo(99);
        assertThat(amount).isNotNull();
        assertThat(amount).isEqualTo(123.45);
    }

    @Test
    void shouldNotReturnCashCardWhenIdIsUnknown() {
        // when
        var response = restTemplate.getForEntity("/cashcards/1000", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }
}
