package chess;

import chess.controller.ChessController;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.domain.game.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.game.Command;
import chess.views.InputView;
import chess.views.OutputView;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        RequestDto requestDto;

        OutputView.printInitialGuide();
        do {
            requestDto = InputView.getCommand();
            ResponseDto responseDto = chessController.getResponseDto(requestDto);
            OutputView.printChessBoard(responseDto.getChessBoard().getChessBoard());
            OutputView.printStatus(responseDto.getResult());
        } while (requestDto.getCommend() != Command.END);
    }
}