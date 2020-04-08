package chess.controller;

import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private ChessService chessService = new ChessService();

    public void run() {
        OutputView.printInitialMessage();
        while (!chessService.isEnd(0)) {
            RequestDto requestDto = InputView.inputRequest();
            ResponseDto responseDto = chessService.run(requestDto);
            OutputView.printResponse(responseDto);
            OutputView.printErrorMessage(responseDto.getMessage());
        }
    }
}
