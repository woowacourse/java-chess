package chess.controller.command;

import chess.dto.ScoreDto;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;

public class StatusCommand implements Command {
    @Override
    public void playWithCurrentTurn(List<String> inputCommand, ChessGameService chessGameService) {
        if (chessGameService.isChessBoardNotInitialized()) {
            throw new IllegalArgumentException("첫 시작은 start 또는 end만 가능합니다.");
        }
    
        OutputView.noticeIntermediateResult();
        ScoreDto scoreDto = new ScoreDto(chessGameService.chessBoard());
        OutputView.printScore(scoreDto);
        OutputView.printWinnerTeam(scoreDto);
    }
    
    @Override
    public boolean isRunning() {
        return true;
    }
}
