package chess.domain.state;

import chess.domain.board.Board;
import chess.view.Command;

public class End implements State{

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State progress(Command command, Board board) {
        if(!command.isEnd()){
            throw new IllegalArgumentException("이미 게임이 종료되었습니다");
        }
        return this;
    }
}
