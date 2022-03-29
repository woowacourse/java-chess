package chess.console.state;

import static chess.console.view.InputView.MENU_INDEX;

import chess.domain.board.Board;

public class Running implements State{

    private final Board board;

    public Running(Board board) {
        this.board = board;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State run(String[] inputs) {
        Command command = Command.of(inputs[MENU_INDEX]);
        if(command.isEnd()) {
            return new End();
        }
        if(command.isStatus()) {
            return new Status(board).run(inputs);
        }
        if(command.isMove()) {
            return new Move(board).run(inputs);
        }
        throw new IllegalStateException("실행이 불가능한 명령어 입니다.");
    }
}
