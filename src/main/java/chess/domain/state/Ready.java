package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.game.Score;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Ready extends ChessGameState {

    public Ready(ChessBoard chessBoard) {
        super(chessBoard, Color.WHITE);
    }

    @Override
    public State start() {
        return new Running(chessBoard(), currentColor());
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
        return false;
    }

    @Override
    public Score score() {
        throw new UnsupportedOperationException(UNSUPPORTED_SCORE);
    }

    @Override
    public Color winner() {
        throw new UnsupportedOperationException(UNSUPPORTED_WINNER);
    }
}
