package chess.domain;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveStateTest {
    @Test
    @DisplayName("move 수행시 moveCount 증가 확인")
    void moveCount() {
        ChessBoard chessBoard = new ChessBoard(Color.WHITE);
        MoveState moveState = new MoveState("id");
        moveState.move("move a2 a4", chessBoard);
        assertThat(moveState.getMoveCount().getMoveCount()).isEqualTo(1);
        moveState.move("move a2 a4", chessBoard);
        assertThat(moveState.getMoveCount().getMoveCount()).isEqualTo(1);
    }
}
