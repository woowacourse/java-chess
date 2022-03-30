package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.game.Score;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Finished extends ChessGameState {

    protected Finished(ChessBoard chessBoard, Color color) {
        super(chessBoard, color);
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException(UNSUPPORTED_START);
    }

    @Override
    public State finished() {
        throw new UnsupportedOperationException("finished를 지원하지 않습니다.");
    }

    @Override
    public State move(Position from, Position to) {
        throw new UnsupportedOperationException(UNSUPPORTED_MOVE);
    }

    @Override
    public boolean isFinished() {
        return true;
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
