package chess.controller;

import chess.domain.Command;
import chess.domain.board.Board;
import chess.domain.board.BoardCreator;
import chess.domain.square.Square;
import chess.domain.Turn;
import chess.view.InputView;
import chess.view.OutputView;

public class GameManager {

    private final InputView inputView;
    private final OutputView outputView;

    public GameManager(InputView inputView, OutputView outputView) {
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
