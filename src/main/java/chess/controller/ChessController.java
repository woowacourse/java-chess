package chess.controller;

import chess.domain.command.InputCommand;
import chess.domain.game.ChessGame;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();

        OutputView.printCommandGuide();

        while (true) {
            InputCommand commands = InputView.inputCommand();

            if (commands == InputCommand.START) {
                chessGame.startGame();
                OutputView.printBoard(chessGame.getBoard());
            }

            if (commands == InputCommand.MOVE) {
                Position fromPosition = Position.from(commands.getFirstPosition());
                Position toPosition = Position.from(commands.getSecondPosition());

                chessGame.movePiece(fromPosition, toPosition);
                OutputView.printBoard(chessGame.getBoard());
            }

            if (commands == InputCommand.STATUS) {
                chessGame.showStatus();
                OutputView.printScore(chessGame.getBoard());
            }

            if (commands == InputCommand.END) {
                chessGame.endGame();
            }
        }
    }
}
