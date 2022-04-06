package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.Team;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

class ChessDAOTest {

    ChessDAO chessDAO = new ChessDAO();

    @Test
    void saveBoard() {
        int boardId = chessDAO.saveBoard(new Board(), Team.WHITE.name());
        System.out.println(boardId);
        assertThat(boardId).isNotEqualTo(0);
    }

    @Test
    void findBoardByBoardId() {
        Board board = new Board();
        board.movePiece(Position.from("a2"), Position.from("a4"), Team.WHITE);
        int boardId = chessDAO.saveBoard(board, Team.WHITE.name());
        Board boardData = chessDAO.findBoardByBoardId(boardId);

        assertThat(boardData.getPiece(Position.from("a4"))).isInstanceOf(Pawn.class);
    }

    @Test
    void updatePiece() {
        Board board = new Board();
        board.movePiece(Position.from("a2"), Position.from("a4"), Team.WHITE);
        int boardId = chessDAO.saveBoard(board, Team.WHITE.name());
        chessDAO.updatePiece(board.getPiece(Position.from("a4")), boardId);
        Piece piece = chessDAO.findPieceByPosition(Position.from("a4"), boardId);

        assertThat(piece).isInstanceOf(Pawn.class);
    }
}