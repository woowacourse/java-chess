package controller.command;

import dto.ChessBoardDto;
import service.ChessService;
import view.OutputView;

import java.util.List;

public final class LoadGame implements Command {

    private final ChessService chessService;

    public LoadGame(final ChessService chessService) {
        this.chessService = chessService;
    }

    @Override
    public void playTurn(final List<String> inputs) {
        chessService.initializeBoard();
        chessService.startLoadGame();
        final ChessBoardDto chessBoardDto = ChessBoardDto.from(chessService.getChessBoard());

        OutputView.printNotice("불러온 게임을 시작합니다.");
        OutputView.printChessBoard(chessBoardDto);
    }

    @Override
    public boolean isKeepGaming() {
        return true;
    }

}
