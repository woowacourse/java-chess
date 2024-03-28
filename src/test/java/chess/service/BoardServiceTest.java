package chess.service;

import chess.db.ConnectionManager;
import chess.model.board.Board;
import chess.model.piece.Color;
import chess.model.piece.King;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardServiceTest {
    private static final ConnectionManager CONNECTION_MANAGER = ConnectionManager.getTest();

    private final BoardService boardService = new BoardService(CONNECTION_MANAGER);

    @BeforeEach
    void initPieceAndTurn() {
        try (Connection connection = CONNECTION_MANAGER.getConnection()) {
            PreparedStatement truncatePieceStatement = connection.prepareStatement("TRUNCATE TABLE pieces");
            PreparedStatement truncateTurnStatement = connection.prepareStatement("TRUNCATE TABLE turns");
            truncatePieceStatement.executeUpdate();
            truncateTurnStatement.executeUpdate();
        } catch (final SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    void 저장된_보드가_없는_것을_올바르게_판단한다() {
        assertThat(boardService.isBoardExist()).isFalse();
    }

    @Test
    void 저장된_보드가_있는_것을_올바르게_판단한다() {
        Board board = new Board(Map.of(
                Position.of(1, 2), King.from(Color.WHITE),
                Position.of(3, 4), King.from(Color.BLACK)
        ), Color.WHITE);
        boardService.saveBoard(board);
        assertThat(boardService.isBoardExist()).isTrue();
    }

    @Test
    void 승패가_없는_보드를_올바르게_저장한다() {
        Map<Position, Piece> piecePosition = Map.of(
                Position.of(1, 2), King.from(Color.WHITE),
                Position.of(3, 4), King.from(Color.BLACK),
                Position.of(5, 6), Queen.from(Color.BLACK)
        );
        Color currentColor = Color.BLACK;
        Board expectedBoard = new Board(piecePosition, currentColor);
        boardService.saveBoard(expectedBoard);
        Board board = boardService.getBoard();
        assertThat(board.getSquares()).isEqualTo(piecePosition);
        assertThat(board.getCurrentColor()).isEqualTo(currentColor);
    }

    @Test
    void 승패가_있으면_보드를_저장하지_않는다() {
        Map<Position, Piece> piecePosition = Map.of(
                Position.of(1, 2), King.from(Color.WHITE),
                Position.of(5, 6), Queen.from(Color.BLACK)
        );
        Color currentColor = Color.BLACK;
        Board endedBoard = new Board(piecePosition, currentColor);
        boardService.saveBoard(endedBoard);
        assertThat(boardService.isBoardExist()).isFalse();
    }

    @Test
    void 저장된_piece가_없을_때_조회하면_예외가_발생한다() {
        assertThatThrownBy(boardService::getBoard)
                .isInstanceOf(IllegalStateException.class);
    }
}
