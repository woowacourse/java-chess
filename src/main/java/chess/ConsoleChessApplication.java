package chess;

import chess.controller.ChessController;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        InputView consoleInputView = new InputView();
        OutputView consoleOutputView = new OutputView();
        ChessController chessController = new ChessController();

        consoleOutputView.printInitialMessage();
        while (true) {
            RequestDto requestDto = consoleInputView.inputRequest();
            ResponseDto responseDto = chessController.run(requestDto);
            consoleOutputView.printResponse(responseDto);
        }
    }
}
