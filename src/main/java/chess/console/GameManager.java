package chess.console;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Result;
import chess.domain.board.StatusResult;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.state.State;
import java.util.Map;

public final class GameManager {

    private State currentState;

    public Map<Position, Piece> start() {
        currentState = State.start(Board.getInitializedInstance());
        return currentState.getBoard();
    }

    public void move(Position source, Position destination) {
        checkInitiated();
        currentState = currentState.movePiece(source, destination);
    }

    public StatusResult getStatus() {
        checkInitiated();
        Map<Color, Double> scoreByColor = currentState.getScore();
        Result result = currentState.getResult();
        return new StatusResult(scoreByColor, result);
    }

    public boolean isFinished() {
        checkInitiated();
        return currentState.isFinished();
    }

    public Map<Position, Piece> getBoard() {
        return currentState.getBoard();
    }

    private void checkInitiated() {
        throw new IllegalStateException("start가 호출되지 않았습니다.");
    }
}
