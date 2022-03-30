package chess.domain.piece;

import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PieceFixture.WHITE_QUEEN;
import static chess.domain.postion.File.*;
import static chess.domain.postion.Rank.*;
import static chess.domain.postion.Rank.ONE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class QueenTest {

    @DisplayName("source와 target이 상하좌우, 대각선 방향에 위치해 있지 않은 경우 에러 테스트")
    @Test
    void notTopBottomRightLeftAndDiagonal() {
        Queen queen = WHITE_QUEEN;

        assertThatThrownBy(() -> queen.canMove(new Position(A, TWO), new Position(H, THREE)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("퀸이 대각선으로 이동하는 경우 테스트")
    @Test
    void diagonal() {
        Queen queen = WHITE_QUEEN;

        assertDoesNotThrow(() -> queen.canMove(new Position(H, FOUR), new Position(E, ONE)));
    }
}
