package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void go () {
        Map<String, Object> map = new HashMap<>();
        map.put("hi", "there");
        System.out.println(map.get("hihi"));
    }

}