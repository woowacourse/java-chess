package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.game.Score;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class GameEnd extends ChessGameState {

    protected GameEnd(ChessBoard chessBoard, Color color) {
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
        throw new UnsupportedOperationException(UNSUPPORTED_MOVE);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isGameEnd() {
        return true;
    }

    @Override
    public Score score() {
        throw new UnsupportedOperationException(UNSUPPORTED_SCORE);
    }

    @Override
    public Color winner() {
        return chessBoard().getWinner();
    }
}
