package chess.service;

import chess.Board;
import chess.dao.PiecesDao;
import chess.dao.TurnDao;
import chess.piece.Team;
import chess.position.Position;
import chess.strategy.NormalInitStrategy;

import java.sql.SQLException;

public class ChessGameService {
    private PiecesDao piecesDAO;
    private TurnDao turnDAO;

    public ChessGameService(PiecesDao piecesDAO, TurnDao turnDAO) {
        this.piecesDAO = piecesDAO;
        this.turnDAO = turnDAO;
    }

    public void init() throws SQLException {
        NormalInitStrategy normalInitStrategy = new NormalInitStrategy();
        piecesDAO.save(normalInitStrategy.init());
        turnDAO.save(Team.WHITE);
    }

    public void play(String source, String target) throws SQLException {
        Board board = new Board(piecesDAO.load(), turnDAO.load());
        board.moveIfPossible(Position.of(source), Position.of(target));
        piecesDAO.save(board.getPieces());
        turnDAO.save(board.getTurn());
    }

    public boolean isEmpty() throws SQLException {
        return piecesDAO.load() == null || turnDAO.load() == null;
    }

    public boolean isFinished() throws SQLException {
        Board board = new Board(piecesDAO.load(), turnDAO.load());
        return board.isFinished();
    }

    public boolean isTurnWhite() throws SQLException {
        Board board = new Board(piecesDAO.load(), turnDAO.load());
        return board.getTurn() == Team.WHITE;
    }

    public Board getBoard() throws SQLException {
        return new Board(piecesDAO.load(), turnDAO.load());
    }
}
