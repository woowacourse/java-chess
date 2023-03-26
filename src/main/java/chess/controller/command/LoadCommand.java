package chess.controller.command;

import chess.dto.ChessBoardDto;
import chess.dto.MoveDto;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;

public class LoadCommand implements Command {
    @Override
    public void playWithCurrentTurn(List<String> inputCommand, ChessGameService chessGameService) {
        chessGameService.newChessGame();
        chessGameService.load();
    
        OutputView.noticeLoadGame();
        ChessBoardDto chessBoardDto = new ChessBoardDto(chessGameService.chessBoard());
        OutputView.printChessBoard(chessBoardDto);
    }
    
    @Override
    public boolean isRunning() {
        return true;
    }
}
