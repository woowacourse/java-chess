package chess.domain.game;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.game.state.GameState;
import chess.domain.game.state.Ready;
import chess.domain.piece.Color;
import java.util.Arrays;
import java.util.List;

public class ChessGame {

    private GameState state;

    public ChessGame() {
        state = new Ready();
    }

    public void initBoard() {
        state = state.initBoard();
    }

    public void movePiece(String fromPosition, String toPosition) {
        state = state.movePiece(Position.valueOf(fromPosition),
            Position.valueOf(toPosition));
    }

    public void endGame() {
        state = state.end();
    }

    public boolean isFinish() {
        return state.isFinish();
    }

    public double calculateScore(Color color) {
        return state.calculateScore(color);
    }

    public Color judgeWinner() {
        return state.judgeWinner();
    }

    public Board getBoard() {
        return state.getBoard();
    }
}
