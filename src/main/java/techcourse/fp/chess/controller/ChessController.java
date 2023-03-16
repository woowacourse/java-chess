package techcourse.fp.chess.controller;

import techcourse.fp.chess.domain.Board;
import techcourse.fp.chess.domain.BoardFactory;
import techcourse.fp.chess.dto.PieceDto;
import techcourse.fp.chess.view.InputView;
import techcourse.fp.chess.view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Board board = BoardFactory.generate();

        outputView.printInitialMessage();
        final String rawCommand = inputView.readInitCommand();
        final Command initCommand = Command.createInitMessage(rawCommand);
        if (initCommand.isStart()) {
            outputView.printBoard(PieceDto.create(board.getBoard()));
        }

    }
}
