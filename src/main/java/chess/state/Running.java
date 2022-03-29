package chess.state;

import chess.ChessBoard;
import chess.game.Score;
import chess.piece.Color;
import chess.position.Position;

public class Running extends ChessGameState {

    protected Running(ChessBoard chessBoard, Color color) {
        super(chessBoard, color);
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException(UNSUPPORTED_START);
    }

    @Override
    public State finished() {
        return new Finished(chessBoard(), currentColor());
    }

    @Override
    public State move(Position from, Position to) {
        chessBoard().move(from, to, currentColor());
        if (chessBoard().isGameEnd()) {
            return new GameEnd(chessBoard(), currentColor());
        }
        return new Running(chessBoard(), changeTurn());
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
        return Score.from(chessBoard());
    }

    @Override
    public Color winner() {
        throw new UnsupportedOperationException(UNSUPPORTED_WINNER);
    }
}
