package chess.controller;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.view.ConsoleOutputView;
import chess.view.OutputView;

public class ChessController {
    private static OutputView outputView = new ConsoleOutputView();

    public static void run() {
        Board board = new Board();
        BoardDto boardDto = new BoardDto(board.get());
        PositionDto positionDto = new PositionDto(Position.getPositions());
        outputView.printBoard(positionDto.getPositions(), boardDto.get());
    }
}
