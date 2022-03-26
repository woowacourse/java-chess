package chess.domain;

import chess.domain.piece.Team;
import chess.domain.state.Ready;
import chess.domain.state.State;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    private final ChessBoard chessBoard;
    private State state;

    public ChessGame() {
        state = new Ready();
        chessBoard = new ChessBoard();
    }

    private void start() {
        state = state.start();
    }

    private void stop() {
        state = state.stop();
    }

    public void progress(Command command) {
        if (command.isStart()) {
            start();
            return;
        }

        if (command.isEnd()) {
            stop();
            return;
        }

        state = state.changeTurn(command, chessBoard);
    }

    private double getBlackScore() {
        return state.calculateBlackScore(chessBoard);
    }

    private double getWhiteScore() {
        return state.calculateWhiteScore(chessBoard);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Map<Team, Double> calculateResult() {
        Map<Team, Double> scores = new HashMap<>();
        double blackScore = getBlackScore();
        double whiteScore = getWhiteScore();

        scores.put(Team.WHITE, whiteScore);
        scores.put(Team.BLACK, blackScore);

        return scores;
    }

    public boolean isEnd() {
        return state.isEnd();
    }
}
