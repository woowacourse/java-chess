package chess.status;

import chess.game.Board;
import chess.game.MoveCommand;
import chess.game.Position;
import chess.piece.Color;

public class Move extends Running {

    Move(final Board board, final Color color) {
        super(board, color);
    }

    @Override
    public void move(final MoveCommand moveCommand) {
        final Position from = moveCommand.getFrom();
        final Position to = moveCommand.getTo();
        getBoard().movePiece(from, to, getColor());
        reversColor();
    }

    @Override
    public boolean canMove() {
        return true;
    }

}
