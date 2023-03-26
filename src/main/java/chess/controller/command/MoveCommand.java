package chess.controller.command;

import chess.dto.ChessBoardDto;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;

public class MoveCommand implements Command {
    @Override
    public void playWithCurrentTurn(List<String> inputCommand, ChessGameService chessGameService) {
        if (chessGameService.isNotInitialized()) {
            throw new IllegalArgumentException("첫 시작은 start 또는 end만 가능합니다.");
        }
        chessGameService.move(inputCommand);
    
        ChessBoardDto chessBoardDto = new ChessBoardDto(chessGameService.chessBoard());
        OutputView.printChessBoard(chessBoardDto);
    }
    
    @Override
    public boolean isRunning() {
        return true;
    }
}
