package chess;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.Camp;
import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.domain.gamestate.Score;
import chess.domain.piece.Piece;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import spark.Request;

public class WebGameController {
    private final ChessGame chessGame;
    private final GameDao gameDao;
    private final BoardDao boardDao;

    public WebGameController() {
        this.chessGame = new ChessGame();
        this.gameDao = new GameDao();
        this.boardDao = new BoardDao();
    }

    public Map<String, Object> modelReady() {
        Map<String, Object> model = new HashMap<>();
        model.put("ready", true);
        return model;
    }

    public void start() {
        chessGame.start();
    }

    public Map<String, Object> modelPlayingBoard() {
        Map<Position, Piece> board = chessGame.getBoard().getSquares();
        Map<String, Object> model = board.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toString(), Entry::getValue));
        model.put("started", true);
        model.put("ready", false);
        return model;
    }

    public void load() throws SQLException {
        chessGame.load(boardDao.load(), gameDao.isWhiteTurn());
    }

    public void move(Request req) {
        Map<String, String> positions = Arrays.stream(req.body().split("&"))
                .collect(Collectors.toMap(
                        data -> data.substring(0, data.indexOf("=")),
                        data -> data.substring(data.indexOf("=") + 1)
                ));
        chessGame.move(Position.of(positions.get("source")), Position.of(positions.get("target")));
    }

    public Map<String, Object> modelStatus() {
        Map<Camp, Score> scores = chessGame.getScores();
        return scores.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().toString(), Map.Entry::getValue));
    }

    public Map<Camp, Score> status() {
        return chessGame.getScores();
    }

    public void save() throws SQLException {
        gameDao.save();
        boardDao.save(chessGame.getBoard().getSquares());
    }

    public Map<String, Object> end() {
        chessGame.end();
        return modelResult();
    }

    private Map<String, Object> modelResult() {
        Map<String, Object> model = new HashMap<>();
        Camp winner = chessGame.getWinner();
        model.put("winner", winner);
        if (winner == Camp.NONE) {
            model.put("tie", true);
        }
        model.put("started", false);
        model.put("ready", true);
        return model;
    }

    public boolean isGameFinished() {
        return this.chessGame.isFinished();
    }

    public Map<Position, Piece> getBoard() {
        return chessGame.getBoard().getSquares();
    }
}
