package controller;

import dao.ChessGameDao;
import domain.ChessGame;
import domain.state.Ready;
import domain.state.State;
import java.util.List;
import view.InputView;
import view.OutputView;

public class MainController {

    public static final int EMPTY_ROOM = 0;
    private final ChessGameDao chessGameDao;

    public MainController(ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public void run() {
        InputView.printStartMessage();
        ChessGame chessGame = chessGameDao.select();
        State state = new Ready(chessGame);

        while (!state.isEnd()) {
            state = play(state);
            chessGameDao.save(chessGame);
        }
    }

    public State play(State state) {
        try {
            List<String> input = InputView.readCommand();
            return state.run(input);
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printError(e);
            return state;
        }
    }
}
