package controller;

import domain.board.Board;
import domain.board.BoardCreator;
import domain.Command;
import domain.square.Square;
import domain.Turn;
import view.InputView;
import view.OutputView;

public class ChessManager {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Command command = inputView.readFirstCommand();
        if (command.isEnd()) {
            return;
        }
        Board board = initialize();
        play(board);
    }

    private Board initialize() {
        Board board = new Board(new BoardCreator());
        outputView.printBoard(board);
        return board;
    }

    private void play(Board board) {
        Turn turn = new Turn();
        while (inputView.readNextCommand() == Command.MOVE) {
            Square current = inputView.readPosition();
            Square target = inputView.readPosition();
            inputView.readNextLine();

            turn.check(board, current);
            board.move(current, target);
            outputView.printBoard(board);
            turn.end();
        }
    }
}
