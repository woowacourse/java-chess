package controller;

import domain.ChessBoard;
import util.BoardMapper;
import view.ChessCommand;
import view.InputView;
import view.OutputView;

public class ChessController {

    public void start() {
        ChessBoard chessBoard = null;
        while (true) {
            ChessCommand chessCommand = InputView.inputChessCommand();
            if (chessCommand == ChessCommand.END) {
                break;
            }
            if (chessCommand == ChessCommand.START) {
                chessBoard = ChessBoard.createDefaultBoard();
            }
            OutputView.printBoard(BoardMapper.toDto(chessBoard));
        }
    }
}
