package example.cashcard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class CashCardJsonTest {

    @Autowired
    private JacksonTester<CashCard> json;

    @Test
    void cashCardSerializationTest() throws IOException {
        // given
        var cashCard = new CashCard(99L, 123.45);

        // when
        var jsonContent = json.write(cashCard);

        // then
        assertThat(jsonContent).isStrictlyEqualToJson("expected.json");
        assertThat(jsonContent).hasJsonPathNumberValue("@.id");
        assertThat(jsonContent).extractingJsonPathNumberValue("@.id").isEqualTo(99);
        assertThat(jsonContent).hasJsonPathNumberValue("@.amount");
        assertThat(jsonContent).extractingJsonPathNumberValue("@.amount").isEqualTo(123.45);
    }
}
