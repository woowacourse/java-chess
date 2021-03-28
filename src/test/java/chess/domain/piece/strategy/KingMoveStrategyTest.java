package chess.domain.piece.strategy;

import chess.domain.board.Board;
import chess.domain.board.InitializedBoard;
import chess.domain.board.Position;
import chess.domain.exceptions.OverDistanceException;
import chess.domain.exceptions.SameTeamException;
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

    @BeforeEach
    void setUp() {
        InitializedBoard initializedBoard = new InitializedBoard();
        board = new Board(initializedBoard.board());
        position = Position.of('c', 3);
        piece = new Piece(PieceKind.KING, PieceColor.WHITE);
        board.putPieceAtPosition(position, piece);
    }

    @DisplayName("King 움직임 테스트")
    @Test
    void kingValidMove_void() {
        Position target = Position.of('d', 3);
        board.move(position, target, PieceColor.WHITE);

        Piece pieceOnTarget = board.pieceAtPosition(target);
        assertEquals(pieceOnTarget, piece);
    }

    @DisplayName("King 거리 초과 테스트")
    @Test
    void kingExcessMove_InvalidMoveException() {
        Position target = Position.of('d', 7);

        assertThatThrownBy(() -> board.move(position, target, PieceColor.WHITE))
            .isInstanceOf(OverDistanceException.class);
    }

    @DisplayName("같은 팀인 경우 Exception 발생")
    @Test
    void checkIsNotSameTeam_ExceptionThrown() {
        Position target = Position.of('b', 2);

        assertThatThrownBy(() -> board.move(position, target, PieceColor.WHITE))
            .isInstanceOf(SameTeamException.class);
    }
}