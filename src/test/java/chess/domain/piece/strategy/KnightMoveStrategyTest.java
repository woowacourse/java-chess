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
import static org.junit.jupiter.api.Assertions.*;

class KnightMoveStrategyTest {

    Board board;
    Position position;
    Piece piece;
    KnightMoveStrategy knightMoveStrategy;

    @BeforeEach
    void setUp() {
        board = new Board();
        position = Position.of('c', 3);
        piece = new Piece(PieceKind.KNIGHT, PieceColor.WHITE);
        knightMoveStrategy = new KnightMoveStrategy();
        board.putPieceAtPosition(position, piece);
    }

    @DisplayName("Knight 움직임 테스트 - 이동 가능한 위치로 이동")
    @Test
    void knightValidMove_withoutJump() {
        Position target = Position.of('e', 4);
        knightMoveStrategy.move(position, target, board);

        Piece pieceOnTarget = board.checkPieceAtPosition(target);

        assertEquals(pieceOnTarget, piece);
    }

    @DisplayName("Knight 움직임 테스트 - 이동 가능한 위치로 Jump해서 이동")
    @Test
    void queenValidMove_diagonalMove() {
        Position source =Position.of('g', 1);
        Position target = Position.of('f', 3);
        knightMoveStrategy.move(source, target, board);

        Piece pieceOnTarget = board.checkPieceAtPosition(target);

        assertEquals(pieceOnTarget, piece);
    }

    @DisplayName("Knight 움직임 테스트 - 이동 가능하지 않은 위치로 이동")
    @Test
    void knightValidMove_lineMove() {
        Position target = Position.of('e', 4);
        knightMoveStrategy.move(position, target, board);

        Piece pieceOnTarget = board.checkPieceAtPosition(target);

        assertEquals(pieceOnTarget, piece);
    }

    @DisplayName("같은 팀인 경우 Exception 발생")
    @Test
    void checkIsNotSameTeam_ExceptionThrown() {
        Position target = Position.of('d', 1);

        assertThatThrownBy(() -> knightMoveStrategy.move(position, target, board))
            .isInstanceOf(InvalidMoveException.class)
            .hasMessageContaining(Piece.SAME_TEAM_MESSAGE);
    }

    @DisplayName("보드 밖 이동시 Exception 발생")
    @Test
    void checkUnableOutOfBoard_ExceptionThrown() {
        Position source = Position.of('b', 8);
        Position target = Position.of('c', 10);

        assertThatThrownBy(() -> knightMoveStrategy.move(source, target, board))
            .isInstanceOf(InvalidMoveException.class)
            .hasMessageContaining(Piece.OUT_OF_BOUND_MESSAGE);
    }

}