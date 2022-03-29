package chess.domain.piece;

import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PieceFixture.WHITE_BISHOP;
import static chess.domain.PositionFixture.WHITE_SOURCE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BishopTest {
    @DisplayName("source와 target이 대각선 방향에 위치해있지 않으면 에러 테스트")
    @Test
    void notSameDiagonal() {
        Bishop bishop = WHITE_BISHOP;

        assertThatThrownBy(() -> bishop.canMove(WHITE_SOURCE, new Position(File.C, Rank.FIVE)))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
