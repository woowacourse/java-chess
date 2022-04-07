package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.Team;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

class DatabaseChessDAOTest {

    DatabaseChessDAO chessDAO = new DatabaseChessDAO();

    @Test
    void findBoardByGameId() {
        Board board = new Board();
        board.movePiece(Position.from("a2"), Position.from("a4"), Team.WHITE);
        int gameId = chessDAO.saveGame(board, Team.WHITE.name());
        Board boardData = chessDAO.findBoardByGameId(gameId);

        assertThat(boardData.getPiece(Position.from("a4"))).isInstanceOf(Pawn.class);
    }

    @Test
    void updatePiece() {
        Board board = new Board();
        board.movePiece(Position.from("a2"), Position.from("a4"), Team.WHITE);
        int gameId = chessDAO.saveGame(board, Team.WHITE.name());
        chessDAO.updatePiece(board.getPiece(Position.from("a4")), gameId);
        Piece piece = chessDAO.findPieceByPosition(Position.from("a4"), gameId);

        assertThat(piece).isInstanceOf(Pawn.class);
    }

    @Test
    void findTurnByGameId() {
        int gameId = chessDAO.saveGame(new Board(), Team.BLACK.name());
        Team team = chessDAO.findTurnByGameId(gameId);

        assertThat(team).isEqualTo(Team.BLACK);
    }

    @Test
    void updateTurn() {
        int gameId = chessDAO.saveGame(new Board(), Team.BLACK.name());
        chessDAO.updateTurn(Team.WHITE, gameId);
        Team team = chessDAO.findTurnByGameId(gameId);

        assertThat(team).isEqualTo(Team.WHITE);
    }
}