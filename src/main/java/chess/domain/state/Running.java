package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;

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
