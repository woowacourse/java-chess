package chess.domain.gamestate;

import chess.domain.Score;
import chess.domain.Side;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

public final class Running extends GameState {

    public Running(Board board, Side side) {
        super(board, side);
    }

    @Override
    public GameState start() {
        throw new InvalidCommandException();
    }

    @Override
    public GameState move(Position from, Position to) {
        board().move(from, to, currentTurn());
        if (board().isGameSet()) {
            return new GameSet(board(), Side.NONE);
        }
        return new Running(board(), changeTurn());
    }

    @Override
    public State finished() {
        return new Finished(board(), currentTurn());
    }

    @Override
    public Score score() {
        return Score.from(board());
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
