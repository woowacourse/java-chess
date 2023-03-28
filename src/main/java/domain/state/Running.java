package domain.state;

import domain.ChessGame;
import java.util.List;
import view.InputView;

public class Running implements State {

    protected final ChessGame chessGame;

    public Running(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State run(List<String> input) {
        Command command = Command.of(input.get(InputView.COMMAND_INDEX));
        if (command.isEnd()) {
            return new End();
        }
        if (command.isMove()) {
            return new Move(chessGame).run(input);
        }
        if (command.isStatus()) {
            return new Status(chessGame).run(input);
        }
        throw new IllegalStateException("실행할 수 없는 명령어입니다.");
    }
}
