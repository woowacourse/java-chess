package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    public static final Position B5 = new Position(File.B, Rank.FIVE);
    public static final Position B6 = new Position(File.B, Rank.SIX);
    public static final Position D7 = new Position(File.D, Rank.SEVEN);
    public static final Position C6 = new Position(File.C, Rank.SIX);

    @DisplayName("Rank와 File이 1씩 차이날 때 경로에 타겟이 포함된다")
    @Test
    void computePath_rightUp() {
        var king = new King(Color.BLACK);

        var source = B5;
        var target = C6;

        assertThat(king.computePathWithValidate(source, target)).containsExactlyInAnyOrder(C6);
    }

    @DisplayName("Rank가 같을 때 경로에 타겟이 포함된다")
    @Test
    void computePath_up() {
        var king = new King(Color.BLACK);

        var source = B5;
        var target = B6;

        assertThat(king.computePathWithValidate(source, target)).containsExactlyInAnyOrder(B6);
    }

    @DisplayName("인접한 위치가 아니면 예외가 발생한다.")
    @Test
    void computePath_illegalTaget_exception() {
        var king = new King(Color.BLACK);

        var source = B5;
        var target = D7;

        assertThatThrownBy(() -> king.computePathWithValidate(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("흑색 킹은 점수가 0점이다")
    @Test
    void getScore_blackZero() {
        var piece = new King(Color.BLACK);

        assertThat(piece.getScore(Color.BLACK)).isEqualTo(0);
    }

    @DisplayName("백색 킹은 점수가 0점이다")
    @Test
    void getScore_whiteZero() {
        var piece = new King(Color.WHITE);

        assertThat(piece.getScore(Color.WHITE)).isEqualTo(0);
    }

    @DisplayName("다른 색의 킹은 점수가 0점이다")
    @Test
    void getScore_zero() {
        var piece = new King(Color.BLACK);

        assertThat(piece.getScore(Color.WHITE)).isEqualTo(0);
    }
}
