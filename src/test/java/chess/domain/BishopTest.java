package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Rook;
import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.PieceFixture.whiteBishop;
import static chess.domain.PositionFixture.WHITE_SOURCE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BishopTest {
    @DisplayName("source와 target이 대각선 방향에 위치해있지 않으면 에러 테스트")
    @Test
    void notSameDiagonal() {
        Bishop bishop = whiteBishop;

        assertThatThrownBy(() -> bishop.canMove(WHITE_SOURCE, new Position(File.C, Rank.FIVE)))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
