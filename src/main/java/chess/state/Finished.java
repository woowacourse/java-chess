package chess.state;

import chess.ChessBoard;
import chess.game.Score;
import chess.piece.Color;
import chess.position.Position;

public class Finished extends ChessGameState {

    public Finished(ChessBoard chessBoard, Color color) {
        super(chessBoard, color);
    }

    @Override
    public State start() {
        return null;
    }

    @Override
    public State finished() {
        return null;
    }

    @Override
    public State move(Position from, Position to) {
        return null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isGameEnd() {
        return false;
    }

    @Override
    public Score score() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Color winner() {
        throw new UnsupportedOperationException();
    }
}
