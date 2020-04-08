package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoveStateTest {
    @Test
    @DisplayName("move 수행시 moveCount 증가 확인")
    void moveCount() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.movePiece(Square.of("a2"), Square.of("a4"));
        assertThat(chessBoard.getMoveState().getMoveCount().getMoveCount()).isEqualTo(1);
        assertThatThrownBy(() -> chessBoard.movePiece(Square.of("a2"), Square.of("a3")))
                .isInstanceOf(UnsupportedOperationException.class);
        assertThat(chessBoard.getMoveState().getMoveCount().getMoveCount()).isEqualTo(1);
    }
}
