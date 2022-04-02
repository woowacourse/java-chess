package chess.domain.piece;

import chess.domain.postion.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PieceFixture.WHITE_BISHOP;
import static chess.domain.postion.File.A;
import static chess.domain.postion.File.C;
import static chess.domain.postion.Rank.FIVE;
import static chess.domain.postion.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BishopTest {
    @DisplayName("source와 target이 대각선 방향에 위치해있지 않으면 에러 테스트")
    @Test
    void notSameDiagonal() {
        Bishop bishop = WHITE_BISHOP;

        assertThatThrownBy(() -> bishop.direction(new Position(A, TWO), new Position(C, FIVE), new Nothing()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
