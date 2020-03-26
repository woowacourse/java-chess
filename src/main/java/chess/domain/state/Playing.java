package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Position;

public class Playing implements State {
    private Board board;
    private Turn turn;

    public Playing(Board board, Turn turn) {
        this.board = board;
        this.turn = turn;
        board.initialize();
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State end() {
        return new Finished();
    }

    @Override
    public State move(Position source, Position target) {
        board.move(source, target, turn.getColor());
        turn = turn.next();
        return this;
    }

    @Override
    public Board board() {
        return board;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
