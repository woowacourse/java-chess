package controller.command;

import dto.ChessBoardDto;
import service.ChessService;
import view.OutputView;

import java.util.List;

public final class Move implements Command {

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final ChessService chessService;

    public Move(final ChessService chessService) {
        this.chessService = chessService;
    }

    @Override
    public void playTurn(final List<String> inputs) {
        final String source = inputs.get(SOURCE_INDEX);
        final String target = inputs.get(TARGET_INDEX);

        chessService.move(source, target);
        final ChessBoardDto chessBoardDto = ChessBoardDto.from(chessService.getChessBoard());

        OutputView.printChessBoard(chessBoardDto);
    }

    @Override
    public boolean isKeepGaming() {
        return chessService.isKingAlive();
    }

}
