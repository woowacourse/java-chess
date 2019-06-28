package model.game;

import model.Position;
import model.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static model.piece.PieceColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class GameTest {

    @Test
    @DisplayName("초기 보드에서 현재 점수를 알려준다.")
    void getCurrentScore() {
        Game game = new Game();

        assertThat(game.getCurrentScore(WHITE)).isEqualTo(38.0, offset(0.000001));
    }

    @Test
    @DisplayName("두 폰이 같은 줄에 있을 때 현재 점수를 알려준다.")
    void getCurrentScoreWhenPawnIsSameLine() {
        Game game = new Game();

        Piece pawn = game.board().getPieceAt(Position.of("a2"));
        pawn.moveTo(Position.of("b3"));

        assertThat(game.getCurrentScore(WHITE)).isEqualTo(37.0, offset(0.000001));
    }
}