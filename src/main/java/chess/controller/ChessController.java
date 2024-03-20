package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {
    public void play() {
        OutputView.printStartMessage();
        InputView.inputStartCommand();
        Board board = new Board(BoardFactory.generateStartBoard());
        OutputView.printChessBoard(board.mapPositionToCharacter());
        runGame(board);
    }

    private void runGame(Board board) {
        List<Position> positions;
        while (!(positions = InputView.inputNextCommand()).isEmpty()) {
            board.move(positions.get(0), positions.get(1));
            OutputView.printChessBoard(board.mapPositionToCharacter());
        }
    }
}
