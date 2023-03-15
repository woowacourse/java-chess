package chess.controller;

import static chess.controller.ChessExecuteCommand.START;

import chess.controller.dto.ChessBoardDto;
import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public void run() {
        outputView.printStartMessage();
        final Board board = Board.generate();
        final ChessExecuteCommand chessExecuteCommand = ChessExecuteCommand.from(inputView.readChessExecuteCommand());
        if (chessExecuteCommand == START) {
            outputView.printChessBoard(ChessBoardDto.from(board));
        }
    }
}
