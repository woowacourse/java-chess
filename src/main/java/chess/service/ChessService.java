package chess.service;

import chess.domain.Position;
import chess.domain.game.state.ChessGame;
import chess.domain.game.state.Ready;
import chess.domain.piece.Color;
import java.util.HashMap;
import java.util.Map;

public class ChessService {

    private static final String TERMINATE_KEY = "end";
    private static final String TERMINATE_MESSAGE = "게임이 종료되었습니다.";

    private ChessGame chessGame;

    public ChessService() {
        chessGame = new Ready();
    }

    public Map<String, Object> start() {
        chessGame = chessGame.initBoard();
        return chessGame.getBoard().toMap();
    }

    public Map<String, Object> move(String from, String to) {
        chessGame = chessGame.movePiece(Position.valueOf(from), Position.valueOf(to));
        return chessGame.getBoard().toMap();
    }

    public Map<String, Double> showStatus() {
        Map<String, Double> scoreStatus = new HashMap<>();
        scoreStatus.put(Color.WHITE.name(), chessGame.calculateScore(Color.WHITE));
        scoreStatus.put(Color.BLACK.name(), chessGame.calculateScore(Color.BLACK));
        return scoreStatus;
    }

    public Map<String, String> terminate() {
        Map<String, String> terminateMessage = new HashMap<>();
        chessGame.end();
        terminateMessage.put(TERMINATE_KEY, TERMINATE_MESSAGE);
        return terminateMessage;
    }

    public Map<String, Object> showBoard() {
        return chessGame.getBoard().toMap();
    }
}
