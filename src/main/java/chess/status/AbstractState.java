package chess.status;

import chess.game.Board;
import chess.game.MoveCommand;
import chess.piece.Piece;
import chess.piece.detail.Color;
import chess.position.Position;
import chess.view.Command;
import java.util.Map;

public class AbstractState implements State{

    protected Board board;
    protected Color turn;

    AbstractState() {
    }

    AbstractState(final Board board, final Color turn) {
        this.board = board;
        this.turn = turn;
    }

    @Override
    public State turn(final Command command) {
        return null;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void move(final MoveCommand moveCommand) {

    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public Board getBoard() {
        return null;
    }

    @Override
    public boolean isGameEnd() {
        return false;
    }

    @Override
    public Color getTurn() {
        return null;
    }

    @Override
    public Map<Color, Double> getStatus() {
        return null;
    }

    @Override
    public void load(final Map<Position, Piece> board) {

    }
}
