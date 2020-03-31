package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.position.Position;
import chess.views.InputDto;
import chess.views.InputView;
import chess.views.OutputView;

public class ChessController {
    private ChessBoard chessBoard;

    public void play() {
        OutputView.printInitialGuide();
        InputDto inputDto = InputView.getCommand();
        Command command = inputDto.getCommand();
        start(command);

        do {
            inputDto = InputView.getCommand();
            command = inputDto.getCommand();

            if (command == Command.MOVE) {
                move(inputDto.getFrom(), inputDto.getTo());
            } else if (command == Command.STATUS) {
                status();
            }
        } while(!chessBoard.isGameOver() && command != Command.END);
    }

    private void start(Command command) {
        if (command != Command.START) {
            throw new IllegalArgumentException("start를 해야 합니다.");
        }
        chessBoard = new ChessBoard();
        OutputView.printChessBoard(chessBoard.getChessBoard());
    }

    private void move(Position from, Position to) {
        chessBoard.move(from, to);
        OutputView.printChessBoard(chessBoard.getChessBoard());
    }

    private void status() {
        OutputView.printStatus(chessBoard.createResult());
    }
}