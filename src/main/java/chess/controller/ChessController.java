package chess.controller;

import chess.domain.Board;
import chess.domain.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {
    public void run() {
        OutputView.printStartMessage();

        InputView.inputStartCommand();
        Board board = new Board();
        OutputView.printChessBoard(board.mapPositionToCharacter());

        List<Position> positions;
        while (!(positions = InputView.inputNextCommand()).isEmpty()) {
            board.move(positions.get(0), positions.get(1));
            OutputView.printChessBoard(board.mapPositionToCharacter());
        }
    }
}
