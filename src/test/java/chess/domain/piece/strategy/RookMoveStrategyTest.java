package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RookMoveStrategyTest {

    Board board;
    Position position;
    Piece piece;
    //RookMoveStrategy rookMoveStrategy;

    @BeforeEach
    void setUp() {
        board = new Board();
        position = Position.of('c', 3);
        piece = new Piece(PieceKind.ROOK, PieceColor.WHITE);
        board.putPieceAtPosition(position, piece);
    }

    @DisplayName("Rook 움직임 테스트 - 유효한 직선 위치로 이동")
    @Test
    void rookValidMove_void() {
        Position target = Position.of('c', 7);
        board.move(position, target);

        Piece pieceOnTarget = board.pieceAtPosition(target);

        assertEquals(pieceOnTarget, piece);
    }

    @DisplayName("Rook 움직임 테스트 - 유효하지 않는 위치로 이동")
    @Test
    void rookInvalidMove_ExceptionThrown() {
        Position target = Position.of('f', 5);

        assertThatThrownBy(() -> board.move(position, target))
            .isInstanceOf(InvalidMoveException.class)
            .hasMessageContaining(Piece.UNABLE_MOVE_TYPE_MESSAGE);
    }

    @DisplayName("같은 팀인 경우 Exception 발생")
    @Test
    void checkIsNotSameTeam_ExceptionThrown() {
        Position target = Position.of('c', 2);

        assertThatThrownBy(() -> board.move(position, target))
            .isInstanceOf(InvalidMoveException.class)
            .hasMessageContaining(Piece.SAME_TEAM_MESSAGE);
    }

    @DisplayName("경로상 CLEAR 상태가 아닐 시에 Exception 발생")
    @Test
    void checkUnableCROSSPIECE_ExceptionThrown() {
        Position target = Position.of('c', 8);

        assertThatThrownBy(() -> board.move(position, target))
            .isInstanceOf(InvalidMoveException.class)
            .hasMessageContaining(Piece.UNABLE_CROSS_MESSAGE);
    }

}