package chess.controller;

import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.Command;
import chess.domain.Point;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameStart();

        ChessGame game = new ChessGame(BoardFactory.createEmptyBoard());
        while (true) {
            try {
                String readCommand = inputView.readCommand();
                Command command = Command.from(readCommand);
                if (command.isEnd()) {
                    break;
                }
                if (Command.START == command) {
                    game = new ChessGame(BoardFactory.createInitialChessBoard());
                }
                if (Command.MOVE == command) {
                    String[] splitCommand = readCommand.split(" ");

                    Point departure = new Point(splitCommand[1]);
                    Point destination = new Point(splitCommand[2]);
                    game.move(departure, destination);
                }
                outputView.printBoard(game.getBoard());
            } catch (Exception e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
