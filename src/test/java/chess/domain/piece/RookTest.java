package chess.domain.piece;

import chess.domain.postion.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PieceFixture.WHITE_ROOK;
import static chess.domain.postion.File.*;
import static chess.domain.postion.Rank.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RookTest {

    @DisplayName("source와 target이 같은 경우 에러 테스트")
    @Test
    void samePosition() {
        Rook rook = WHITE_ROOK;

        assertThatThrownBy(() -> rook.direction(new Position(A, TWO), new Position(A, TWO), new Nothing()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("source와 target이 같은 file이 아닐 경우 에러 테스트")
    @Test
    void notSameFile() {
        Rook rook = WHITE_ROOK;

        assertThatThrownBy(() -> rook.direction(new Position(C, THREE), new Position(B, TWO), new Nothing()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("source와 target이 같은 rank가 아닐 경우 에러 테스트")
    @Test
    void notSameRank() {
        Rook rook = WHITE_ROOK;

        assertThatThrownBy(() -> rook.direction(new Position(A, TWO), new Position(B, EIGHT), new Nothing()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
