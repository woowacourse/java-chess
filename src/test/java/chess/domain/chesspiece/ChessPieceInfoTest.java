package chess.domain.chesspiece;

import chess.domain.move.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.chesspiece.ChessPieceInfo.PAWN;
import static chess.domain.move.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ChessPieceInfoTest {
    @Test
    @DisplayName("getName 테스트")
    void getName() {
        ChessPieceInfo pawnInfo = PAWN;
        assertThat(pawnInfo.getName()).isEqualTo("p");
    }

    @Test
    @DisplayName("getScore 테스트")
    void getScore() {
        ChessPieceInfo pawnInfo = PAWN;
        assertThat(pawnInfo.getPoint()).isEqualTo(1);
    }

    @Test
    @DisplayName("getMoveDirections 테스트")
    void getMoveDirections() {
        ChessPieceInfo pawnInfo = PAWN;
        assertThat(pawnInfo.getMoveDirections()).isEqualTo(new Direction[]{UP, LEFT_UP, RIGHT_UP});
    }
}
