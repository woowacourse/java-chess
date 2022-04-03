package chess.state;

import chess.game.Board;
import chess.game.MoveCommand;
import chess.piece.Color;

public class Status extends Running {

    Status(final Board board, final Color color) {
        super(board, color);
    }

    @Override
    public void move(final MoveCommand moveCommand) {
        throw new IllegalStateException("움직일 수 없는 상태입니다.");
    }

    @Override
    public boolean canMove() {
        return false;
    }
}
