package chess;

import chess.controller.command.Command;
import chess.domain.ChessManager;
import chess.view.ConsoleInputView;
import chess.view.InputView;

public class ConsoleChessApplication {

    public static void main(String[] args) {
        InputView inputView = new ConsoleInputView();
        ChessManager chessManager = new ChessManager();

        Command startCommand = inputView.askStartCommand().get();
        startCommand.apply(chessManager, startCommand.toString());

        while (chessManager.isPlaying()) {
            String continueCommand = inputView.askContinueCommand().get();
            Command c = Command.of(continueCommand.split(" ")[0]);
            c.apply(chessManager, continueCommand);
        }
    }
}