package refactorChess.domain.state;

import refactorChess.domain.board.ChessBoard;
import refactorChess.domain.board.Position;
import refactorChess.domain.game.Score;
import refactorChess.domain.game.Status;

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

    public Score getScore() {
        throw new UnsupportedOperationException("상태가 Ready인 경우 점수를 호출 할 수 없습니다.");
    }

    @Override
    public Status getStatus() {
        throw new UnsupportedOperationException("상태가 Ready인 경우 게임 상태를 호출 할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
