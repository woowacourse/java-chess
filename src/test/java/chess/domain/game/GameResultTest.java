package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    @Test
    @DisplayName("GameResult에서 점수계산을 잘 하는지 확인")
    void score() {
        final Pieces pieces = new Pieces();
        pieces.init();
        final GameResult gameResult = new GameResult(pieces);

        final Map<Color, Double> scoreTable = gameResult.getScore();

        assertThat(scoreTable.get(Color.BLACK)).isEqualTo(38);
        assertThat(scoreTable.get(Color.WHITE)).isEqualTo(38);
    }

    @Test
    @DisplayName("같은 열에 폰이 있을 때 0.5점으로 계산되는지 확인")
    void pawnCounting() {
        final Pieces pieces = new Pieces(new Pawn(Color.BLACK, Position.from("d1")),
                new Pawn(Color.BLACK, Position.from("d2")),
                new Pawn(Color.BLACK, Position.from("d3")));
        final GameResult gameResult = new GameResult(pieces);
        assertThat(gameResult.score(Color.BLACK)).isEqualTo(1.5);
    }
}
