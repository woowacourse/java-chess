package chess.domain.gamestate;

import chess.domain.ChessGame;
import chess.domain.board.Board;

public class Ready implements State {

    @Override
    public State start() {
        return new Running(new Board());
    }

    @Override
    public State end() {
        return this;
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
