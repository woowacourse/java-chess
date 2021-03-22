package chess.domain.gamestate;

import chess.domain.Score;
import chess.domain.Side;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

public class GameSet extends GameState {

    public GameSet(Board board, Side side) {
        super(board, side);
    }

    @Override
    public GameState start() {
        throw new InvalidCommandException();
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
        return Score.from(board());
    }

    @Override
    public Side winner() {
        return board().winner();
    }

    @Override
    public boolean isGameSet() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
