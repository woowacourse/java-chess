package chess.state;

import chess.ChessBoard;
import chess.game.Score;
import chess.piece.Color;
import chess.position.Position;

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
        throw new UnsupportedOperationException("[ERROR] move를 지원하지 않습니다.");
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
