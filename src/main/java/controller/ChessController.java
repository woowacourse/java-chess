package controller;

import domain.board.ChessBoard;
import domain.board.ChessBoardFactory;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import view.Command;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        ChessBoard board = ChessBoardFactory.createInitialChessBoard();
        outputView.printStartingMessage();

        String[] commands = inputView.readCommand().split(" ");
        Command command = Command.findByName(commands[0]);
        while (command.isNotEnd()) {
            if (command.isMove()) {
                Position resource = extracted(commands[1]);
                Position target = extracted(commands[2]);
                board.move(resource, target);
            }
            outputView.printBoard(board);
            commands = inputView.readCommand().split(" ");
            command = Command.findByName(commands[0]);
        }
    }

    private Position extracted(String position) {
        String resourceFile = position.substring(0, 1);
        String resourceRank = position.substring(1, 2);
        File file = File.fromName(resourceFile);
        Rank rank = Rank.fromNumber(Integer.parseInt(resourceRank));
        return new Position(file, rank);
    }
}
