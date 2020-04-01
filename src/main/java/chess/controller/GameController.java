package chess.controller;

import chess.controller.dto.BoardDto;
import chess.controller.dto.PositionDto;
import chess.domain.ChessRunner;
import chess.domain.position.Position;
import chess.view.ConsoleOutputView;
import chess.view.OutputView;

import java.util.Map;

public abstract class GameController {
    protected final OutputView outputView;

    public GameController() {
        this.outputView = new ConsoleOutputView();
    }

    protected void printBoard(final Map<String, String> board) {
        BoardDto boardDto = new BoardDto(board);
        PositionDto positionDto = new PositionDto(Position.getPositions());
        this.outputView.printBoard(positionDto.getPositions(), boardDto.get());
    }

    public abstract void execute(ChessRunner chessRunner, String input);
}
