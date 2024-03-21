package controller;

import domain.ChessBoard;
import dto.RouteDto;
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
            if (chessCommand == ChessCommand.MOVE) {
                final var source = InputView.inputChessPoint();
                final var destination = InputView.inputChessPoint();
                chessBoard.move(new RouteDto(source, destination));
            }
            OutputView.printBoard(BoardMapper.toDto(chessBoard));
        }
    }

}
