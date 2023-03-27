package chess.controller.command;

import chess.domain.ChessGame;
import chess.domain.square.Square;
import chess.dto.BoardDto;
import chess.view.OutputView;
import java.util.List;

public class MoveCommand implements Command {
    private static final int SOURCE_SQUARE_INDEX = 0;
    private static final int TARGET_SQUARE_INDEX = 1;

    private final List<String> parameters;

    public MoveCommand(final List<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute(final ChessGame chessGame, final OutputView outputView) {
        Square sourceSquare = convertSquare(parameters.get(SOURCE_SQUARE_INDEX));
        Square targetSquare = convertSquare(parameters.get(TARGET_SQUARE_INDEX));
        chessGame.move(sourceSquare, targetSquare);
        BoardDto boardDto = BoardDto.from(chessGame.getBoard());
        outputView.printBoard(boardDto);
    }

    private Square convertSquare(final String square) {
        return Square.from(square);
    }
}
