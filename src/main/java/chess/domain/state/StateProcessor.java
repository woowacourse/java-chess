package chess.domain.state;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.CommandDto;

import java.util.Map;

public final class StateProcessor {
    private State state;

    private StateProcessor(final State state) {
        this.state = state;
    }

    public static StateProcessor create() {
        final State state = new Ready(ChessGame.create());

        return new StateProcessor(state);
    }

    public Map<Position, Piece> getBoard() {
        return state.chessGame
                .getBoard();
    }

    public void changeState(State state) {
        this.state = state;
    }

    public boolean isNotEnd() {
        return !state.isEnd();
    }

    public boolean isGameEnd() {
        return state.isGameEnd();
    }

    public boolean isNotGameEnd() {
        return !state.isGameEnd();
    }

    public State move(final CommandDto commandDto) {
        return state.move(commandDto.getSource(), commandDto.getTarget());
    }

    public State start() {
        return state.start();
    }

    public State end() {
        return state.end();
    }

    public State identity() {
        return state;
    }

    public double status(Color color) {
        return state.calculateScore(color);
    }

    public Color getColor() {
        return state.getColor();
    }
}
