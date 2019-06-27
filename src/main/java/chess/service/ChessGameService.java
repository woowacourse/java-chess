package chess.service;

import chess.dao.ChessJdbcConnector;
import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.*;
import chess.domain.pieces.Piece;
import chess.dto.PieceDto;
import com.google.gson.Gson;
import org.javatuples.Pair;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameService {
    private static final String DATABASE_NAME = "chess";

    private DataSource dataSource = ChessJdbcConnector.getDataSource(DATABASE_NAME);
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
        return gameDao.findAllId();
    }

    public Pair<Game, Integer> createNewGame() {
        Game game = new Game(BoardFactory.init());
        gameDao.createNewGame();
        int gameId = findMaxId();
        List<PieceDto> pieceDtos = game.toDto();
        for (PieceDto pieceDto : pieceDtos) {
            add(gameId, pieceDto);
        }
        return Pair.with(game, gameId);
    }

    private int findMaxId() {
        return gameDao.findMaxId();
    }

    public void add(int gameId, PieceDto pieceDto) {
        pieceDao.add(gameId, pieceDto);
    }

    public List<PieceDto> findPieceById(int gameId) {
        return pieceDao.findPieceById(gameId);
    }

    public boolean findTurnByGameId(int gameId) {
        return gameDao.findTurnByGameId(gameId);
    }

    public Game move(int gameId, Point start, Point end) {
        Game game = getGame(gameId);
        game.move(start, end);
        pieceDao.deletePieceByPosition(gameId, end);
        pieceDao.updatePosition(gameId, start, end);
        pieceDao.insertBlank(gameId, start);
        toggleTurnById(game, gameId);
        return game;
    }

    private Game getGame(int gameId) {
        List<PieceDto> pieceDtos = findPieceById(gameId);
        Map<Point, Piece> board = new HashMap<>();
        pieceDtos.forEach(dto -> board.put(new Point(dto.getX(), dto.getY()),
                PieceFactory.of(dto.getName(), dto.isTeam() ? Team.WHITE : Team.BLACK)));
        Team turn = findTurnByGameId(gameId) ? Team.WHITE : Team.BLACK;
        return new Game(board, turn);
    }

    private void toggleTurnById(Game game, int gameId) {
        game.changeTurn();
        gameDao.toggleTurnById(gameId);
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
