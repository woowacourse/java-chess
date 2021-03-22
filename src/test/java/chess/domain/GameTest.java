package chess.domain;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;


class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        Board board = BoardFactory.create();
        game = new Game(board);
    }


    @Test
    void pickEndPiece() {
        assertThatCode(() -> game.move(Position.from("a7"), Position.from("a6")))
                .doesNotThrowAnyException();
    }

    @Test
    void score() {
        Map<Color, Double> scoreTable = game.score();
        assertThat(scoreTable.get(Color.BLACK)).isEqualTo(38);
        assertThat(scoreTable.get(Color.WHITE)).isEqualTo(38);
    }
}