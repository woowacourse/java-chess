package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BoardTest {
    @Test
    void create() {
//        List<String> arrange = Arrays.asList("RNBKQBKR","PPPPPPPP","........","........","........","........","pppppppp","rnbkqbkr");
        List<String> arrange = Arrays.asList("PPPPPPPP", "PPPPPPPP", "........", "........", "........", "........", "pppppppp", "pppppppp");
        Board board = new Board(arrange);
        Map<Point, Piece> points = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            String[] row = arrange.get(i).split("");
            for (int j = 0; j < 8; j++) {
                points.put(new Point(i, j), PieceType.of(row[j]).create());
            }
        }

    }


}
