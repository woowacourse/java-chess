package chess.domain;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;


class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    @DisplayName("게임에서 기물이동을 잘 하는지 확인")
    void pickEndPiece() {
        assertThatCode(() -> game.move(Position.from("a7"), Position.from("a6")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("점수계산을 잘 하는지 확인")
    void score() {
        final Map<Color, Double> scoreTable = game.score();
        assertThat(scoreTable.get(Color.BLACK)).isEqualTo(38);
        assertThat(scoreTable.get(Color.WHITE)).isEqualTo(38);
    }
}