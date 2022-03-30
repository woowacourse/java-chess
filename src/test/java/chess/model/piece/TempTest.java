package chess.model.piece;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TempTest {

    @Test
    void name() {
        List<Integer> a = List.of(1, 2, 3);
        a.stream().map(i -> List.of(i*2, i*3)).forEach(System.out::println);
    }
}
