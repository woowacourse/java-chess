package controller.command;

import dto.ChessBoardDto;
import service.ChessService;
import view.OutputView;

import java.util.List;

public final class NewGame implements Command {

    private final ChessService chessService;

    public NewGame(final ChessService chessService) {
        this.chessService = chessService;
    }

    @Override
    public void playTurn(final List<String> inputs) {
        chessService.initializeBoard();
        chessService.startNewGame();
        final ChessBoardDto chessBoardDto = ChessBoardDto.from(chessService.getChessBoard());

        OutputView.printNotice("새로운 게임을 시작합니다.");
        OutputView.printChessBoard(chessBoardDto);
    }

    @Override
    public boolean isKeepGaming() {
        return true;
    }

}
