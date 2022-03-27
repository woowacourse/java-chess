package chess.domain.gamestate;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.Position;

public class Ready implements State {

    @Override
    public State start() {
        return new Running(new Board());
    }

    @Override
    public State move(Position beforePosition, Position afterPosition) {
        throw new IllegalStateException("게임이 진행중이 아닐때는 기물을 이동할 수 없습니다.");
    }

    @Override
    public State end() {
        return this;
    }

    @Override
    public double statusOfBlack() {
        throw new IllegalStateException("게임이 진행중이 아닐때는 상태를 확인할 수 없습니다.");
    }

    @Override
    public double statusOfWhite() {
        throw new IllegalStateException("게임이 진행중이 아닐때는 상태를 확인할 수 없습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException("체스판이 아직 준비되지 않았습니다.");
    }
}
