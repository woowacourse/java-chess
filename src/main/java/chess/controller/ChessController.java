package chess.controller;

import chess.controller.dto.BoardDto;
import chess.controller.dto.PositionDto;
import chess.domain.ChessRunner;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.view.ConsoleInputView;
import chess.view.ConsoleOutputView;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static InputView inputView = new ConsoleInputView();
    private static OutputView outputView = new ConsoleOutputView();

    public static void run() {
        if (inputView.askChessRun()) {
            ChessRunner chessRunner = new ChessRunner();
            printInitialize(chessRunner.getBoard());
            String[] moveSource = inputView.askMove().split(" ");
            String sourcePosition = moveSource[1];
            String targetPosition = moveSource[2];
            chessRunner.update(sourcePosition, targetPosition);
            printInitialize(chessRunner.getBoard());
        }
    }

    private static void printInitialize(Board board) {
        BoardDto boardDto = new BoardDto(board.parse());
        PositionDto positionDto = new PositionDto(Position.getPositions());
        outputView.printBoard(positionDto.getPositions(), boardDto.get());
    }
}
