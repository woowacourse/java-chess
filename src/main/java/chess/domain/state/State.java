package chess.domain.state;

import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.Position;

public abstract class State {
    protected final ChessGame chessGame;

    protected State(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public abstract boolean isEnd();

    public abstract boolean isGameEnd();

    public abstract State move(Position source, Position target);

    public abstract State start();

    public abstract State end();

    public abstract double calculateScore(Color color);

    public abstract Color getColor();
}
