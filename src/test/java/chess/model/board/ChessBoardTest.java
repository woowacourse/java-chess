package chess.model.board;

import static chess.helper.PositionFixture.A6;
import static chess.helper.PositionFixture.A7;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.piece.Camp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    private final ChessBoard chessBoard = ChessBoardFactory.create();
    
    @Test
    @DisplayName("move()에서 이동시키고자 하는 기물이 자신의 기물이 아니라면 예외가 발생한다.")
    void move_givenNotAllyPiece_thenFail() {
        // when, then
        assertThatThrownBy(() -> chessBoard.move(A7, A6, Camp.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임을 진행하는 플레이어의 기물이 아닙니다.");
    }
}
