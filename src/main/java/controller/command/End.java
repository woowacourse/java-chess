package controller.command;

import dto.ChessBoardDto;
import dto.ScoreDto;
import service.ChessService;
import view.OutputView;

import java.util.List;

public final class End implements Command {

    private final ChessService chessService;

    public End(final ChessService chessService) {
        this.chessService = chessService;
    }

    @Override
    public void playTurn(final List<String> inputs) {
        final ChessBoardDto chessBoardDto = ChessBoardDto.from(chessService.getChessBoard());
        final ScoreDto scoreDto = ScoreDto.of(chessService.getWhiteScore(), chessService.getBlackScore());

        OutputView.printNotice("> 최종 결과");
        OutputView.printChessBoard(chessBoardDto);
        OutputView.printScore(scoreDto);
    }

    @Override
    public boolean isKeepGaming() {
        return false;
    }

}
