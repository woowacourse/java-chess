package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;

abstract class Finished extends Ready {

    Finished(Board board) {
        super(board);
    }

    @Override
    public State movePiece(Position source, Position destination) {
        throw new UnsupportedOperationException("게임이 종료되어 말을 움직일 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
