package domain.state;

import domain.ChessGame;
import java.util.List;
import view.OutputView;

public class Status extends Running {

    public Status(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public State run(List<String> input) {
        OutputView.printStatus(chessGame);
        return new Running(chessGame);
    }
}
