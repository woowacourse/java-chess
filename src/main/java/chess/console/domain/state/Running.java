package chess.console.domain.state;

import chess.console.domain.board.Board;
import chess.console.domain.board.Position;

abstract class Running extends Ready {

    Running(Board board) {
        super(board);
    }

    public abstract State movePiece(Position source, Position destination);

    @Override
    public boolean isFinished() {
        return false;
    }
}
