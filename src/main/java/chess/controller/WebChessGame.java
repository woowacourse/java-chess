package chess.controller;

import chess.Board;
import chess.DAO.PiecesDAO;
import chess.DAO.TurnDAO;
import chess.DBConnector;
import chess.Scores;
import chess.piece.Team;
import chess.position.Position;
import chess.strategy.NormalInitStrategy;

import java.sql.Connection;
import java.sql.SQLException;

public class WebChessGame {
    private Connection conn = DBConnector.getConnection();
    private PiecesDAO piecesDAO = new PiecesDAO(conn);
    private TurnDAO turnDAO = new TurnDAO(conn);
    private Board board;

    public WebChessGame() throws SQLException {
        this.board = new Board(piecesDAO.loadPieces(), turnDAO.loadTurn());
    }

    public void init() throws SQLException {
        NormalInitStrategy normalInitStrategy = new NormalInitStrategy();
        piecesDAO.savePieces(normalInitStrategy.init());
        turnDAO.saveTurn(Team.WHITE);
        this.board = new Board(piecesDAO.loadPieces(), turnDAO.loadTurn());
    }

    public void play(String source, String target) throws SQLException {
        board.moveIfPossible(Position.of(source), Position.of(target));
        piecesDAO.savePieces(board.getPieces());
        turnDAO.saveTurn(board.getTurn());
    }

    public Board getBoard() {
        return board;
    }
}
