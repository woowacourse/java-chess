package chess.controller;

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
            List<String> commands = InputView.inputCommand();
            String command = commands.get(0);

            if (command.equals("start")) {
                chessGame.startGame();
            }

            if (command.equals("end")) {
                chessGame.endGame();
            }

            if (command.equals("move")) {
                Position fromPosition = Position.from(commands.get(1));
                Position toPosition = Position.from(commands.get(2));

                chessGame.movePiece(fromPosition, toPosition);
            }

            OutputView.printBoard(chessGame.getBoard());
        }
    }
}
