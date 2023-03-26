package controller.command;

import dto.ScoreDto;
import service.ChessService;
import view.OutputView;

import java.util.List;

public final class Status implements Command {

    private final ChessService chessService;

    public Status(final ChessService chessService) {
        this.chessService = chessService;
    }

    @Override
    public void playTurn(final List<String> inputs) {
        final ScoreDto scoreDto = ScoreDto.of(chessService.getWhiteScore(), chessService.getBlackScore());

        OutputView.printNotice("> 중간 결과");
        OutputView.printScore(scoreDto);
    }

    @Override
    public boolean isKeepGaming() {
        return true;
    }

}
