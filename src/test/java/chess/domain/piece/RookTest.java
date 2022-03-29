package chess.domain.piece;

import chess.domain.piece.Rook;
import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PieceFixture.WHITE_ROOK;
import static chess.domain.PositionFixture.WHITE_SOURCE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RookTest {

    @DisplayName("source와 target이 같은 경우 에러 테스트")
    @Test
    void samePosition() {
        Rook rook = WHITE_ROOK;

        assertThatThrownBy(() -> rook.canMove(WHITE_SOURCE, WHITE_SOURCE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("source와 target이 같은 file이 아닐 경우 에러 테스트")
    @Test
    void notSameFile() {
        Rook rook = WHITE_ROOK;

        assertThatThrownBy(() -> rook.canMove(new Position(File.C, Rank.THREE), new Position(File.B, Rank.TWO)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("source와 target이 같은 rank가 아닐 경우 에러 테스트")
    @Test
    void notSameRank() {
        Rook rook = WHITE_ROOK;

        assertThatThrownBy(() -> rook.canMove(new Position(File.A, Rank.TWO), new Position(File.B, Rank.EIGHT)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
