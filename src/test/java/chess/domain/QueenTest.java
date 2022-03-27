package chess.domain;

import chess.domain.piece.Queen;
import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.PieceFixture.whiteQueen;
import static chess.domain.PositionFixture.WHITE_SOURCE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QueenTest {

    @DisplayName("source와 target이 상하좌우, 대각선 방향에 위치해 있지 아닌 경우 에러 테스트")
    @Test
    void notTopBottomRightLeftAndDiagonal() {
        Queen  queen = whiteQueen;

        assertThatThrownBy(() ->queen.canMove(WHITE_SOURCE, new Position(File.H, Rank.THREE)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
