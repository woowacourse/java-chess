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

        Command command;
        do {
            String[] commands = inputView.readCommand().split(" ");
            command = Command.findByName(commands[0]);
            actMoveCommand(board, commands, command);
            outputView.printBoard(board);
        } while (command.isNotEnd());
    }

    private void actMoveCommand(ChessBoard board, String[] commands, Command command) {
        if (command.isMove()) {
            Position source = generatePosition(commands[1]);
            Position target = generatePosition(commands[2]);
            board.movePiece(source, target);
        }
    }

    private Position generatePosition(String position) {
        String sourceFile = position.substring(0, 1);
        String sourceRank = position.substring(1, 2);
        File file = File.fromName(sourceFile);
        Rank rank = Rank.fromNumber(Integer.parseInt(sourceRank));
        return new Position(file, rank);
    }
}
