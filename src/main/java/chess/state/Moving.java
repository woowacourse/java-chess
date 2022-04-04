package chess.state;

import chess.game.Board;
import chess.game.MoveCommand;
import chess.game.Position;
import chess.piece.Color;

public class Moving extends Running {

    public Moving(final Board board, final Color color) {
        super(board, color);
    }

    @Override
    public void move(final MoveCommand moveCommand) {
        final Position from = moveCommand.getFrom();
        final Position to = moveCommand.getTo();
        getBoard().movePiece(from, to, getColor());
    }

    @Override
    public boolean canMove() {
        return true;
    }

}
