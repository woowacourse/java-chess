package chess.model.board.move;

import static chess.helper.PositionFixture.A1;
import static chess.helper.PositionFixture.D5;
import static chess.helper.PositionFixture.D6;
import static chess.helper.PositionFixture.D7;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.helper.ChessBoardPieceMovingHelper;
import chess.model.board.ChessBoard;
import chess.model.board.ChessBoardFactory;
import chess.model.piece.Camp;
import chess.model.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardMoveInitialPawnTest {

    private final Position source = D7;
    private ChessBoard chessBoard;

    @BeforeEach
    void beforeEach() {
        chessBoard = ChessBoardFactory.create();
    }

    @Test
    @DisplayName("검은색 폰은 2칸 전진할 때 경로에 기물이 있다면 이동할 수 없다.")
    void move_givenInvalidStraightWayPoint_thenFail() {
        // given
        ChessBoardPieceMovingHelper.move(chessBoard, A1, D6);

        // when, then
        assertThatThrownBy(() -> chessBoard.move(source, D5, Camp.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 경로로 이동할 수 없습니다.");
    }
}
