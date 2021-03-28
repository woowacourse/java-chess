package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.InitializedBoard;
import chess.domain.board.Position;
import chess.domain.exceptions.SameTeamException;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KnightMoveStrategyTest {

    Board board;
    Position position;
    Piece piece;

    @BeforeEach
    void setUp() {
        InitializedBoard initializedBoard = new InitializedBoard();
        board = new Board(initializedBoard.board());
        position = Position.of('c', 3);
        piece = new Piece(PieceKind.KNIGHT, PieceColor.WHITE);
        board.putPieceAtPosition(position, piece);
    }

    @DisplayName("Knight 움직임 테스트 - 이동 가능한 위치로 이동")
    @Test
    void knightValidMove_withoutJump() {
        Position target = Position.of('e', 4);
        board.move(position, target, PieceColor.WHITE);

        Piece pieceOnTarget = board.pieceAtPosition(target);

        assertEquals(pieceOnTarget, piece);
    }

    @DisplayName("Knight 움직임 테스트 - 이동 가능한 위치로 Jump해서 이동")
    @Test
    void queenValidMove_diagonalMove() {
        Position source = Position.of('g', 1);
        Position target = Position.of('f', 3);
        board.move(source, target, PieceColor.WHITE);

        Piece pieceOnTarget = board.pieceAtPosition(target);

        assertEquals(pieceOnTarget, piece);
    }

    @DisplayName("Knight 움직임 테스트 - 이동 가능하지 않은 위치로 이동")
    @Test
    void knightValidMove_lineMove() {
        Position target = Position.of('e', 4);
        board.move(position, target, PieceColor.WHITE);

        Piece pieceOnTarget = board.pieceAtPosition(target);

        assertEquals(pieceOnTarget, piece);
    }

    @DisplayName("같은 팀인 경우 Exception 발생")
    @Test
    void checkIsNotSameTeam_ExceptionThrown() {
        Position target = Position.of('d', 1);

        assertThatThrownBy(() -> board.move(position, target, PieceColor.WHITE))
            .isInstanceOf(SameTeamException.class);
    }
}