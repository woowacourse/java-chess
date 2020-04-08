package chess.domain;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveStateTest {
    @Test
    @DisplayName("move 수행시 moveCount 증가 확인")
    void moveCount() {
        ChessBoard chessBoard = new ChessBoard("id", Color.WHITE);
        chessBoard.getMoveState().move("move a2 a4", chessBoard);
        assertThat(chessBoard.getMoveState().getMoveCount().getMoveCount()).isEqualTo(1);
        chessBoard.getMoveState().move("move a2 a4", chessBoard);
        assertThat(chessBoard.getMoveState().getMoveCount().getMoveCount()).isEqualTo(1);
    }
}
