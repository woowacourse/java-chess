package chess.controller;

import chess.domain.ChessBoard;
import chess.views.InputDto;
import chess.views.InputView;
import chess.views.OutputView;

public class ChessController {
    public void play() {
        OutputView.printInitialGuide();
        InputDto inputDto = InputView.inputCommand();
        Command command = inputDto.getCommend();

        if (!command.equals(Command.START)) {
            throw new IllegalArgumentException("start를 해야 합니다.");
        }

        ChessBoard chessBoard = new ChessBoard();
        OutputView.printChessBoard(chessBoard.getChessBoard());

        do {
            inputDto = InputView.inputCommand();
            command = inputDto.getCommend();

            if (command == Command.MOVE) {
                chessBoard.move(inputDto.getFrom(), inputDto.getTo());
                OutputView.printChessBoard(chessBoard.getChessBoard());
            } else if (command == Command.STATUS) {
                OutputView.printStatus(chessBoard.createResult());
            }

            if (chessBoard.isGameOver()) {
                OutputView.printGameOver();
                return;
            }
        } while (!command.equals(Command.END));
    }
}