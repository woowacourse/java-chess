package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.game.Status;

public class Ready implements State {

    @Override
    public State start() {
        return new Running(ChessBoard.create(), Turn.WHITE);
    }

    @Override
    public State end() {
        return new Finished(ChessBoard.create());
    }

    @Override
    public State move(Position from, Position to) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ChessBoard getChessBoard() {
        throw new UnsupportedOperationException("상태가 Ready인 경우 체스판을 호출 할 수 없습니다.");
    }

    @Override
    public Status getStatus() {
        throw new UnsupportedOperationException("상태가 Ready인 경우 상태를 호출 할 수 없습니다.");

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
