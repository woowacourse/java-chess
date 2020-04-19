package chess.controller;

import chess.domain.ChessRunner;
import chess.domain.position.Position;
import chess.dto.BoardDTO;
import chess.dto.PositionDTO;
import chess.view.ConsoleOutputView;
import chess.view.OutputView;

import java.util.Map;

public abstract class GameController {
    protected final OutputView outputView;

    public GameController() {
        this.outputView = new ConsoleOutputView();
    }

    protected void printBoard(final Map<String, String> board) {
        BoardDTO boardDto = new BoardDTO(board);
        PositionDTO positionDto = new PositionDTO(Position.getPositions());
        this.outputView.printBoard(positionDto.getPositions(), boardDto.get());
    }

    public abstract void execute(ChessRunner chessRunner, String input);
}
