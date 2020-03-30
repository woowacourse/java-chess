package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.position.Positions;
import chess.views.InputView;
import chess.views.OutputView;

import java.util.Map;

public class ChessController {
    public void play() {
        OutputView.printInitialGuide();

        Map<String, String> inputs = InputView.inputCommand();
        Command command = Command.of(inputs.get("command"));

        if (!command.equals(Command.START)) {
            throw new IllegalArgumentException("start를 해야 합니다.");
        }

        ChessBoard chessBoard = new ChessBoard();
        OutputView.printChessBoard(chessBoard.getChessBoard());

        do{
            inputs = InputView.inputCommand();
            command = Command.of(inputs.get("command"));

            if (command == Command.MOVE) {
                chessBoard.move(Positions.of(inputs.get("from")), Positions.of(inputs.get("to")));
                OutputView.printChessBoard(chessBoard.getChessBoard());
            }
            else if (command == Command.STATUS) {
                OutputView.printStatus(chessBoard.createResult());
            }

            if (chessBoard.isGameOver()) {
                OutputView.printGameOver();
                return;
            }
        }while (!command.equals(Command.END));
    }
}