package chess.domain.state;

import chess.domain.board.Board;
import chess.view.Command;

public class Ready implements State{

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State progress(Command command, Board board) {
        if (command.isStart()) {
            return new WhiteTurn();
        }
        if (command.isMove()) {
            throw new IllegalArgumentException("start 입력 후 move를 입력하실 수 있습니다.");
        }
        return new End();
    }
}
