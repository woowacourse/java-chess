package chess.service;

import chess.domain.Board;
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

    public void start() {
        chessGame = chessGame.initBoard();
    }

    public void move(String from, String to) {
        chessGame = chessGame.movePiece(Position.valueOf(from), Position.valueOf(to));
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

    public Board getBoard() {
        return chessGame.getBoard();
    }
}
