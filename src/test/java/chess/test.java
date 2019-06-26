package chess;

import chess.domain.pieces.Color;
import chess.domain.pieces.Type;
import org.junit.jupiter.api.Test;

public class test {
    @Test
    void name() {
        String a = String.format("%s%s", Color.WHITE.getInitial(), Type.KING.getInitial());
        String b = String.format("%c%d", 1 + 'a', 2);
        System.out.println(a + b);
    }
}
