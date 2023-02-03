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

    @Test
    void cashCardDeserializationTest() throws IOException {
        // given
        var cashCardJson = """
                {
                    "id": 99,
                    "amount": 123.45
                }
                """;

        // when
        var cashCardObjectContent = json.parse(cashCardJson);
        var cashCard = json.parseObject(cashCardJson);

        // then
        assertThat(cashCardObjectContent).isEqualTo(new CashCard(99L, 123.45));
        assertThat(cashCard.id()).isEqualTo(99L);
        assertThat(cashCard.amount()).isEqualTo(123.45);
    }
}
