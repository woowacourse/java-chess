package chess.controller.command;

import chess.dto.ChessBoardDto;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;

public class StartCommand implements Command {
    
    @Override
    public void playWithCurrentTurn(List<String> inputCommand, ChessGameService chessGameService) {
        chessGameService.newChessGame();
    
        OutputView.noticeNewGame();
        ChessBoardDto chessBoardDto = new ChessBoardDto(chessGameService.chessBoard());
        OutputView.printChessBoard(chessBoardDto);
    }
    
    @Override
    public boolean isRunning() {
        return true;
    }
}
