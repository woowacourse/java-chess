package chess.service;

import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.domain.Position;
import chess.domain.game.state.ChessGame;
import chess.domain.game.state.Ready;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class ChessService {

    private static final String TERMINATE_KEY = "end";
    private static final String TERMINATE_MESSAGE = "게임이 종료되었습니다.";

    private ChessGame chessGame;
    private final ChessGameDao chessGameDao;
    private final BoardDao boardDao;

    public ChessService() {
        chessGameDao = new ChessGameDao();
        boardDao = new BoardDao();
        chessGame = new Ready();
    }

    public Map<String, Object> ready() {
        chessGameDao.save(chessGame);
        int gameId = chessGameDao.findRecentGame();
        for (String position : Position.values()) {
            boardDao.save(position, gameId);
        }
        return chessGame.getBoard().toMap();
    }

    public Map<String, Object> start() {
        chessGame = chessGame.initBoard();
        Map<String, Object> board = chessGame.getBoard().toMap();
        int gameId = chessGameDao.findRecentGame();
        for (String position : board.keySet()) {
            Piece piece = (Piece) board.get(position);
            boardDao.update(position, piece.getPiece(), piece.getColor().toString(), gameId);
        }
        chessGameDao.update(gameId, chessGame);
        return board;
    }

    public Map<String, Object> move(String from, String to) {
        chessGame = chessGame.movePiece(Position.valueOf(from), Position.valueOf(to));
        Map<String, Object> board = chessGame.getBoard().toMap();
        int gameId = chessGameDao.findRecentGame();
        boardDao.update(from, null, null, gameId);
        Piece piece = (Piece) board.get(to);
        boardDao.update(to, piece.getPiece(), piece.getColor().toString(), gameId);
        chessGameDao.update(gameId, chessGame);
        return board;
    }

    public Map<String, Double> showStatus() {
        Map<String, Double> scoreStatus = new HashMap<>();
        scoreStatus.put(Color.WHITE.name(), chessGame.calculateScore(Color.WHITE));
        scoreStatus.put(Color.BLACK.name(), chessGame.calculateScore(Color.BLACK));
        return scoreStatus;
    }

    public Map<String, String> terminate() {
        Map<String, String> terminateMessage = new HashMap<>();
        chessGame = chessGame.end();
        int gameId = chessGameDao.findRecentGame();
        chessGameDao.update(gameId, chessGame);
        terminateMessage.put(TERMINATE_KEY, TERMINATE_MESSAGE);
        return terminateMessage;
    }

    public Map<String, Object> showBoard() {
        return chessGame.getBoard().toMap();
    }
}
