package chess.service;

import chess.dao.BoardDao;
import chess.dao.TurnDao;
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
    private static final int ADD_ROUND = 1;
    private static final String WHITE_TEAM = "WHITE";
    private static final String BLAKC_TEAM = "BLACK";

    private BoardDao boardDao;
    private TurnDao turnDao;

    public BoardService() {
        this.boardDao = new BoardDao();
        this.turnDao = new TurnDao();
    }

    public void initialize() {
        Board board = new Board(new ChessInitializer(), PlayerType.WHITE);
        int round = boardDao.recentRound();
        turnDao.addFirstTurn(round + ADD_ROUND);
        boardDao.initialize(board.convertToDto(round + ADD_ROUND));
    }

    public String currentTeam() {
        int round = boardDao.recentRound();
        return turnDao.selectCurrentTurn(round);
    }

    public List<BoardDto> getChesses() {
        int round = boardDao.recentRound();
        return boardDao.findChessesByRound(round);
    }

    public boolean move(String inputSource, String inputDestination) throws SQLException {
        Point source = PointConverter.convertToPoint(inputSource);
        Point destination = PointConverter.convertToPoint(inputDestination);
        int round = boardDao.recentRound();

        BoardLoader boardLoader = new BoardLoader();
        boardLoader.convertBoardDtoToMap(getChesses());
        Board board = new Board(boardLoader, PlayerType.valueOf(turnDao.selectCurrentTurn(round)));

        if (board.executeMovement(source, destination)) {
            return true;
        }
        boardDao.remove(round, destination.toString());
        boardDao.update(round, source.toString(), destination.toString());
        turnDao.updateCurrentTurn(round);

        return false;
    }

    public Map<String, Double> calculateScore() {
        int round = boardDao.recentRound();

        BoardLoader boardLoader = new BoardLoader();
        boardLoader.convertBoardDtoToMap(getChesses());
        Board board = new Board(boardLoader, PlayerType.valueOf(turnDao.selectCurrentTurn(round)));

        Map<String, Double> scores = new HashMap<>();
        scores.put(WHITE_TEAM, board.calculateScore(PlayerType.WHITE));
        scores.put(BLAKC_TEAM, board.calculateScore(PlayerType.BLACK));

        return scores;
    }
}
