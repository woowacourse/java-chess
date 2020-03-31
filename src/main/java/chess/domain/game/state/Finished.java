package chess.domain.game.state;

import chess.domain.game.Board;
import chess.domain.game.Score;
import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class Finished implements State {
    private Board board;

    public Finished(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State move(Position source, Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Board board() {
        return board;
    }

    @Override
    public Score score(Color color) {
        return Score.calculate(board.findPiecesByColor(color));
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
