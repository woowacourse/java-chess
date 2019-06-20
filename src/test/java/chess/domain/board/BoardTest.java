package chess.domain.board;

import chess.domain.piece.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BoardTest {
    private List<String> basicArrange;
    @BeforeEach
    void setUp() {
        basicArrange = Arrays.asList(
                "RNBKQBKR",
                "PPPPPPPP",
                "........",
                "........",
                "........",
                "........",
                "pppppppp",
                "rnbkqbkr");
    }

    @Test
    void create() {
        Board board = BoardFactory.create(basicArrange);

        assertThat(board.get(Point.of(0, 0))
                .orElseThrow(IllegalArgumentException::new))
                .isInstanceOf(Rook.class);
    }
}
