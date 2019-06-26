package chess.service;

import chess.dao.BoardDao;
import chess.dao.TurnDao;
import chess.db.DBManager;
import chess.dto.BoardDto;
import chess.model.PlayerType;
import chess.model.Point;
import chess.model.board.Board;
import chess.model.board.BoardLoader;
import chess.model.board.ChessInitializer;
import chess.util.PointConverter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
    private BoardDao boardDao;
    private TurnDao turnDao;

    public BoardService() {
        this.boardDao = new BoardDao(DBManager.getConnection());
        this.turnDao = new TurnDao(DBManager.getConnection());
    }

    public void initialize() throws SQLException {
        Board board = new Board(new ChessInitializer(), PlayerType.WHITE);
        int round = boardDao.recentRound();
        turnDao.addFirstTurn(round + 1);
        boardDao.initialize(board.convertToDto(round + 1));
    }

    public String currentTeam() throws SQLException {
        int round = boardDao.recentRound();
        return turnDao.selectCurrentTurn(round);
    }

    public List<BoardDto> getChesses() throws SQLException {
        int round = boardDao.recentRound();
        return boardDao.findChessesByRound(round);
    }

    public boolean move(String inputSource, String inputDestination) throws SQLException {
        Point source = PointConverter.convertToPoint(inputSource);
        Point destination = PointConverter.convertToPoint(inputDestination);
        int round = boardDao.recentRound();

        BoardLoader boardLoader = new BoardLoader();
        boardLoader.convertBoardDto(getChesses());
        Board board = new Board(boardLoader, PlayerType.valueOf(turnDao.selectCurrentTurn(round)));

        if (board.executeMovement(source, destination)) {
            return true;
        }
        boardDao.remove(round, destination.toString());
        boardDao.update(round, source.toString(), destination.toString());

        turnDao.updateCurrentTurn(round);

        return false;
    }

    public Map<String, Double> calculateScore() throws SQLException {
        int round = boardDao.recentRound();

        BoardLoader boardLoader = new BoardLoader();
        boardLoader.convertBoardDto(getChesses());
        Board board = new Board(boardLoader, PlayerType.valueOf(turnDao.selectCurrentTurn(round)));

        Map<String, Double> scores = new HashMap<>();
        scores.put("WHITE", board.calculateScore(PlayerType.WHITE));
        scores.put("BLACK", board.calculateScore(PlayerType.BLACK));
        return scores;
    }
}
