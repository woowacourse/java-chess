package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.strategy.StrategyCommand;
import chess.dao.ChessGameDao;
import chess.domain.game.ChessGame;
import chess.domain.room.ChessRoom;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final ChessGame chessGame;
    private ChessState state;

    public ChessController(final ChessRoom chessRoom) {
        this.chessGame = ChessGameDao.findById(chessRoom.getGameId());
        this.state = ChessState.INIT;
    }

    public void run() {
        OutputView.printStart();
        while (state != ChessState.END) {
            state = play();
        }
    }

    private ChessState play() {
        try {
            StrategyCommand command = Command.bind(InputView.read());
            return command.execute(state, chessGame);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return play();
        }
    }
}
