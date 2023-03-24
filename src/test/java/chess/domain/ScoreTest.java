package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("Piece 타입에 맞는 Score를 반환한다.")
    void test_pieceMapScore() {

        assertAll(
                () -> assertThat(Score.mapPieceScore(Pawn.class)).isEqualTo(new Score(1)),
                () -> assertThat(Score.mapPieceScore(Knight.class)).isEqualTo(new Score(2.5)),
                () -> assertThat(Score.mapPieceScore(Bishop.class)).isEqualTo(new Score(3)),
                () -> assertThat(Score.mapPieceScore(Rook.class)).isEqualTo(new Score(5)),
                () -> assertThat(Score.mapPieceScore(Queen.class)).isEqualTo(new Score(9))
        );
    }

    @Test
    @DisplayName("Socre를 더하고, 반환해주는 기능 추가")
    void test_sum() {
        final Score score = new Score(5);
        final Score addition = new Score(4);

        final Score sum = score.sum(addition);

        assertThat(sum.getValue()).isEqualTo(9);
    }

    @Test
    @DisplayName("Score에 숫자를 곱하는 기능 추가")
    void test_multiply() {
        final Score score = new Score(10);
        final Score multiply = score.multiply(0.5);

        assertThat(multiply.getValue()).isEqualTo(5);
    }
}
