package chess.service;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.Point;
import chess.domain.pieces.Piece;
import chess.dto.PieceDto;
import chess.utils.DBUtil;
import com.google.gson.Gson;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameService {
    private static final String DATABASE_NAME = "chess";

    private DataSource dataSource = DBUtil.getDataSource(DATABASE_NAME);
    private PieceDao pieceDao = PieceDao.getInstance(dataSource);
    private GameDao gameDao = GameDao.getInstance(dataSource);

    private static ChessGameService chessGameService;

    public static ChessGameService getInstance() {
        if (chessGameService == null) {
            chessGameService = new ChessGameService();
        }
        return chessGameService;
    }

    public List<Integer> findAllId() {
        try {
            return gameDao.findAllId();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void createNewGame() {
        try {
            gameDao.createNewGame();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int findMaxId() {
        try {
            return gameDao.findMaxId();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void add(int gameId, PieceDto pieceDto) {
        try {
            pieceDao.add(gameId, pieceDto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PieceDto> findPieceById(int gameId) {
        try {
            return pieceDao.findPieceById(gameId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean findTurnByGameId(int gameId) {
        try {
            return gameDao.findTurnByGameId(gameId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void move(int gameId, Point start, Point end) {
        try {
            pieceDao.deletePieceByPosition(gameId, end);
            pieceDao.updatePosition(gameId, start, end);
            pieceDao.insertBlank(gameId, start);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void toggleTurnById(int gameId) {
        try {
            gameDao.toggleTurnById(gameId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String convertBoard(Map<Point, Piece> board) {
        Map<String, String> convertedBoard = new HashMap<>();
        for (Point point : board.keySet()) {
            convertedBoard.put(point.convertPosition(), board.get(point).getSymbol());
        }
        Gson gson = new Gson();
        return gson.toJson(convertedBoard);
    }
}
