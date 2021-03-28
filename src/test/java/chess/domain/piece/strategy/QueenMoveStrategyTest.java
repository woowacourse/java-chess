package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.InitializedBoard;
import chess.domain.board.Position;
import chess.domain.exceptions.InvalidMoveException;
import chess.domain.exceptions.SameTeamException;
import chess.domain.exceptions.UnableCrossException;
import chess.domain.exceptions.UnableMoveTypeException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QueenMoveStrategyTest {

    Board board;
    Position position;
    Piece piece;

    @BeforeEach
    void setUp() {
        InitializedBoard initializedBoard = new InitializedBoard();
        board = new Board(initializedBoard.board());
        position = Position.of('c', 3);
        piece = new Piece(PieceKind.QUEEN, PieceColor.WHITE);
        board.putPieceAtPosition(position, piece);
    }

    @DisplayName("Queen 움직임 테스트 - 좌표 그대로 반환")
    @Test
    void stayMove_ExceptionThrown() {
        Position target = Position.of('c', 3);
        assertThatThrownBy(() -> board.move(position, target, PieceColor.WHITE))
            .isInstanceOf(InvalidMoveException.class)
            .hasMessageContaining("지금 위치와 이동하려는 위치가 같습니다.");
    }

    @DisplayName("Queen 움직임 테스트 - 유효한 대각선 위치로 이동")
    @Test
    void queenValidMove_diagonalMove() {
        Position target = Position.of('d', 4);
        board.move(position, target, PieceColor.WHITE);

        Piece pieceOnTarget = board.pieceAtPosition(target);

        assertEquals(pieceOnTarget, piece);
    }


    @DisplayName("Queen 움직임 테스트 - 유효한 직선 위치로 이동")
    @Test
    void queenValidMove_lineMove() {
        Position target = Position.of('c', 7);
        board.move(position, target, PieceColor.WHITE);

        Piece pieceOnTarget = board.pieceAtPosition(target);

        assertEquals(pieceOnTarget, piece);
    }

    @DisplayName("Queen 움직임 테스트 - 유효한 위치로 이동")
    @Test
    void queenInvalidMove_ExceptionThrown() {
        Position target = Position.of('f', 5);

        assertThatThrownBy(() -> board.move(position, target, PieceColor.WHITE))
            .isInstanceOf(UnableMoveTypeException.class);
    }

    @DisplayName("같은 팀인 경우 Exception 발생")
    @Test
    void checkIsNotSameTeam_ExceptionThrown() {
        Position target = Position.of('b', 2);

        assertThatThrownBy(() -> board.move(position, target, PieceColor.WHITE))
            .isInstanceOf(SameTeamException.class);
    }

    @DisplayName("경로상 CLEAR 상태가 아닐 시에 Exception 발생")
    @Test
    void checkUnableCROSSPIECE_ExceptionThrown() {
        Position target = Position.of('c', 8);

        assertThatThrownBy(() -> board.move(position, target, PieceColor.WHITE))
            .isInstanceOf(UnableCrossException.class);
    }
}