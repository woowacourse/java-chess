package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.chesspieces.Piece;
import chess.domain.chesspieces.Square;
import chess.domain.position.Positions;
import chess.views.InputView;
import chess.views.OutputView;

public class ChessController {
    public void play() {
        start();
        ChessBoard chessBoard = new ChessBoard();
        OutputView.printChessBoard(chessBoard.getChessBoard());
        while (true) {
            String input = InputView.inputCommand();
            Command command = Command.of(input.split(" ")[0]);
            // move a2 a4 3칸만 입력했는지 검증
            if (command.equals(Command.MOVE)) {
                chessBoard.move(Positions.of(input.split(" ")[1]), Positions.of(input.split(" ")[2]));
            }
            if (command.equals(Command.END)) {
                return;
            }
            OutputView.printChessBoard(chessBoard.getChessBoard());
        }
    }

    public void start() {
        do {
            OutputView.printInitialGuide();
        } while (!Command.of(InputView.inputCommand()).equals(Command.START));
    }
}
