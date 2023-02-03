package example.cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    @GetMapping("/{id}")
    public ResponseEntity<CashCard> findById(@PathVariable Long id) {
        if (id != 99) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new CashCard(id, 123.45));
    }
}
