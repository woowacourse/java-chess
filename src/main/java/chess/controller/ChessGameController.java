package chess.controller;

import chess.controller.command.CommandHandler;
import chess.controller.command.command.Command;
import chess.dao.ChessGameDao;
import chess.dao.ChessRoomDao;
import chess.domain.game.ChessGame;
import chess.domain.room.ChessRoom;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {

    public void run(final ChessRoom chessRoom) {
        final ChessGame chessGame = ChessGameDao.findById(chessRoom);
        ChessState state = chessRoom.getState();

        OutputView.printStart();
        while (state != ChessState.END) {
            state = play(state, chessGame);
            ChessRoomDao.updateState(chessRoom, state);
        }
    }

    private ChessState play(final ChessState state, final ChessGame chessGame) {
        try {
            Command command = CommandHandler.bind(InputView.readCommand());
            return command.execute(state, chessGame);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return play(state, chessGame);
        }
    }
}
