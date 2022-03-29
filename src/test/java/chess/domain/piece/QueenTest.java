package chess.domain.piece;

import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PieceFixture.WHITE_QUEEN;
import static chess.domain.postion.File.A;
import static chess.domain.postion.File.H;
import static chess.domain.postion.Rank.THREE;
import static chess.domain.postion.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QueenTest {

    @DisplayName("source와 target이 상하좌우, 대각선 방향에 위치해 있지 아닌 경우 에러 테스트")
    @Test
    void notTopBottomRightLeftAndDiagonal() {
        Queen queen = WHITE_QUEEN;

        assertThatThrownBy(() -> queen.canMove(new Position(A, TWO), new Position(H, THREE)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
