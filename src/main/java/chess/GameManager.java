package chess;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.position.Result;
import chess.domain.position.StatusResult;
import chess.domain.board.Color;
import chess.domain.board.Piece;
import chess.domain.state.State;
import java.util.Map;

public final class GameManager {

    private State currentState = State.start(Board.getInitializedInstance());


    public void move(String source, String destination) {
        currentState = currentState.movePiece(Position.of(source), Position.of(destination));
    }

    public StatusResult getStatus() {
        Map<Color, Double> scoreByColor = getScores();
        Result result = getResult();
        return new StatusResult(scoreByColor, result);
    }

    public boolean isFinished() {
        return currentState.isFinished();
    }

    public Map<Color, Double> getScores() {
        return currentState.getScores();
    }

    public Result getResult() {
        return currentState.getResult();
    }

    public Map<Position, Piece> getBoard() {
        return currentState.getBoard();
    }

    public StatusResult stop() {
        if (currentState == null) {
            currentState = State.stop();
        }
        StatusResult status = getStatus();
        currentState = State.stop();
        return status;
    }

    public boolean isWhiteTurn() {
        return currentState.isWhiteTurn();
    }
}
