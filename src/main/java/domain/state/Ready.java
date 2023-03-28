package domain.state;

import domain.ChessGame;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Ready implements State {

    private final ChessGame chessGame;

    public Ready(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State run(List<String> inputs) {
        Command command = Command.of(inputs.get(InputView.COMMAND_INDEX));

        if (command.isEnd()) {
            return new End();
        }

        if (!command.isStart()) {
            throw new IllegalArgumentException("게임이 시작하지 않았습니다.");
        }
        OutputView.printChessBoard(chessGame);
        return new Running(chessGame);
    }
}
