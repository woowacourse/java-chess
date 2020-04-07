package chess;

import chess.controller.ChessController;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        InputView consoleInputView = new InputView();
        OutputView consoleOutputView = new OutputView();
        ChessController chessController = new ChessController();

        consoleOutputView.printInitialMessage();
//        while (true) {
//            RequestDto requestDto = consoleInputView.inputRequest();
//            ResponseDto responseDto = chessController.run(requestDto);
//            if (Objects.isNull(responseDto)) {
//                consoleOutputView.printInitialMessage();
//            } else {
//                consoleOutputView.printResponse(responseDto);
//            }
//        }
    }
}
