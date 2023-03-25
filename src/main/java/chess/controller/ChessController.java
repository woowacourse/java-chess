package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.ResumeCommand;
import chess.controller.command.StartCommand;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

import java.sql.SQLException;

public class ChessController {
    private Command command;

    public ChessController() {
        this.command = new StartCommand(this);
    }

    public void run() throws SQLException {
        ChessGame chessGame = new ChessGame();
        createRoom(chessGame);
        boolean keepPlaying = catchException(chessGame);
        while (keepPlaying) {
            keepPlaying = catchException(chessGame);
        }
    }

    private void createRoom(ChessGame chessGame) throws SQLException {
        boolean isRoom = catchRoomException(chessGame);
        InputView.printStartChess();
        if (isRoom) {
            this.command = new ResumeCommand(this);
            InputView.printResume();
        }
        InputView.endCommand();
    }

    private boolean catchRoomException(ChessGame chessGame) throws SQLException {
        try {
            return chessGame.isRoom(InputView.printInputRoom());
        } catch (IllegalArgumentException errorMessage) {
            OutputView.printError(errorMessage);
            return catchRoomException(chessGame);
        }
    }

    private boolean catchException(ChessGame chessGame) throws SQLException {
        try {
            return command.operate(chessGame);
        } catch (IllegalArgumentException errorMassage) {
            OutputView.printError(errorMassage);
        }
        return true;
    }

    public void setCommend(Command command) {
        this.command = command;
    }
}
