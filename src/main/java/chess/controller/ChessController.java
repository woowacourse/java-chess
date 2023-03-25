package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.ResumeCommand;
import chess.controller.command.StartCommand;
import chess.domain.ChessGame;
import chess.view.InputView;

import java.sql.SQLException;

public class ChessController {
    private Command command;

    public ChessController() {
        this.command = new StartCommand(this);
    }

    public void run() throws SQLException {
        ChessGame chessGame = new ChessGame();
        boolean isRoom = chessGame.isRoom(InputView.printInputRoom());
        InputView.printStartChess();
        if (isRoom) {
            this.command = new ResumeCommand(this);
            InputView.printResume();
        }
        InputView.endCommand();
        boolean keepPlaying = catchException(chessGame);
        while (keepPlaying) {
            keepPlaying = catchException(chessGame);
        }
    }

    private boolean catchException(ChessGame chessGame) {
        try {
            return command.operate(chessGame);
        } catch (IllegalArgumentException errorMassage) {
            System.out.println(errorMassage);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public void setCommend(Command command) {
        this.command = command;
    }
}
