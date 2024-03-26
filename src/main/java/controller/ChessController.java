package controller;

import domain.ChessBoard;
import dto.RouteDto;
import java.util.Objects;
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
                pieceMove(Objects.requireNonNull(chessBoard));
            }
            OutputView.printChessBoard(chessBoard.toDto());
        }
    }

    private void pieceMove(ChessBoard chessBoard) {
        final var source = InputView.inputChessPoint();
        final var destination = InputView.inputChessPoint();
        chessBoard.move(new RouteDto(source, destination));
    }

}
