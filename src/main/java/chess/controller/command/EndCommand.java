package chess.controller.command;

import chess.dto.ScoreDto;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;

public class EndCommand implements Command {
    @Override
    public void playWithCurrentTurn(List<String> inputCommand, ChessGameService chessGameService) {
        OutputView.noticeFinalResult();
        
        ScoreDto scoreDto = new ScoreDto(chessGameService.chessBoard());
        if (chessGameService.isKingDied()) {
            OutputView.printWinnerTeam(scoreDto);
            return;
        }
    
        OutputView.printScore(scoreDto);
        OutputView.printWinnerTeam(scoreDto);
    }
    
    @Override
    public boolean isRunning() {
        return false;
    }
}
