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

class KingMoveStrategyTest {

    Board board;
    Position position;
    Piece piece;
    KingMoveStrategy kingMoveStrategy;

    @BeforeEach
    void setUp() {
        board = new Board();
        position = Position.of('c', 3);
        piece = new Piece(PieceKind.KING, PieceColor.WHITE);
        kingMoveStrategy = new KingMoveStrategy();
        board.putPieceAtPosition(position, piece);
    }

    @DisplayName("King 움직임 테스트")
    @Test
    void kingValidMove_void() {
        Position target = Position.of('d', 3);
        kingMoveStrategy.move(position, target, board);

        Piece pieceOnTarget = board.checkPieceAtPosition(target);

        assertEquals(pieceOnTarget, piece);
    }

    @DisplayName("King 거리 초과 테스트")
    @Test
    void kingExcessMove_InvalidMoveException() {
        Position target = Position.of('d', 7);

        assertThatThrownBy(() -> kingMoveStrategy.move(position, target, board))
            .isInstanceOf(InvalidMoveException.class)
            .hasMessageContaining(Piece.OVER_DISTANCE_MESSAGE);
    }

    @DisplayName("같은 팀인 경우 Exception 발생")
    @Test
    void checkIsNotSameTeam_ExceptionThrown() {
        Position target = Position.of('b', 2);

        assertThatThrownBy(() -> kingMoveStrategy.move(position, target, board))
            .isInstanceOf(InvalidMoveException.class)
            .hasMessageContaining(Piece.SAME_TEAM_MESSAGE);
    }

    @DisplayName("보드 밖 이동시 Exception 발생")
    @Test
    void checkUnableOutOfBoard_ExceptionThrown() {
        Position source = Position.of('e', 8);
        Position target = Position.of('e', 9);

        assertThatThrownBy(() -> kingMoveStrategy.move(source, target, board))
            .isInstanceOf(InvalidMoveException.class)
            .hasMessageContaining(Piece.OUT_OF_BOUND_MESSAGE);
    }
}