package chess.domain;

import chess.domain.piece.Rook;
import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.PieceFixture.whiteRook;
import static chess.domain.PositionFixture.WHITE_SOURCE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RookTest {

    @DisplayName("source와 target이 같은 경우 에러 테스트")
    @Test
    void samePosition() {
        Rook rook = whiteRook;

        assertThatThrownBy(() -> rook.canMove(WHITE_SOURCE, WHITE_SOURCE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("source와 target이 같은 file이 아닐 경우 에러 테스트")
    @Test
    void sameFile() {
        Rook rook = whiteRook;

        assertThatThrownBy(() -> rook.canMove(WHITE_SOURCE, new Position(File.A, Rank.EIGHT)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("source와 target이 같은 rank가 아닐 경우 에러 테스트")
    @Test
    void sameRank() {
        Rook rook = whiteRook;

        assertThatThrownBy(() -> rook.canMove(WHITE_SOURCE, new Position(File.E, Rank.TWO)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
