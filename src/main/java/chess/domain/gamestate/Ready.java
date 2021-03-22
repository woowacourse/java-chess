package chess.domain.gamestate;

import chess.domain.Score;
import chess.domain.Side;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

public class Ready extends GameState {
    public Ready(Board board) {
        super(board, Side.NONE);
    }

    @Override
    public GameState start() {
        return new Running(board(), Side.WHITE);
    }

    @Override
    public State move(Position from, Position to) {
        throw new InvalidCommandException();
    }

    @Override
    public State finished() {
        return new Finished(board(), currentTurn());
    }

    @Override
    public Score score() {
        throw new InvalidCommandException();
    }

    @Override
    public Side winner() {
        throw new InvalidCommandException();
    }

    @Override
    public boolean isGameSet() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
