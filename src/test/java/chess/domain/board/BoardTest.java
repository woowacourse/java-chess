package chess.domain.board;

import chess.domain.piece.Pawn;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Test
    void move(){
        Board board = BoardFactory.create(basicArrange);
        board.move(Point.of(1,1),Point.of(1,2));
        assertThat(board.get(Point.of(1, 2))
                .orElseThrow(IllegalArgumentException::new))
                .isInstanceOf(Pawn.class);
    }

    @Test
    void move2(){
        Board board = BoardFactory.create(basicArrange);
        board.move(Point.of(1,1),Point.of(1,2));
        assertThat(board.get(Point.of(1, 1))).isEqualTo(Optional.empty());
    }
}
