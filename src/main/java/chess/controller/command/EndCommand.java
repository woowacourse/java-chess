package chess.controller.command;

import chess.dto.ChessBoardDto;
import chess.service.ChessGameService;
import chess.view.OutputView;

import java.util.List;

public class EndCommand implements Command {
    @Override
    public void playWithCurrentTurn(List<String> inputCommand, ChessGameService chessGameService) {
        OutputView.println("> 최종 결과");
        ChessBoardDto chessBoardDto = new ChessBoardDto(chessGameService.chessBoard());
        OutputView.printChessBoard(chessBoardDto);
    }
    
    @Override
    public boolean isRunning() {
        return false;
    }
}
