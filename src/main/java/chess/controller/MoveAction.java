package chess.controller;

import chess.dto.ChessRequest;
import chess.dto.MoveDto;
import chess.service.ChessGameService;
import chess.view.OutputView;

public class MoveAction implements Action {

    @Override
    public GameCommand execute(ChessGameService chessGameService, OutputView outputView, ChessRequest chessRequest) {
        if (chessGameService.isNotStart()) {
            throw new IllegalArgumentException("start를 해주세요");
        }
        MoveDto moveDto = MoveDto.of(chessRequest.getSource(), chessRequest.getTarget());
        chessGameService.move(moveDto);
        outputView.printBoard(chessGameService.getGameResult());
        return checkGameEnd(chessGameService, outputView);
    }

    private GameCommand checkGameEnd(ChessGameService chessGameService, OutputView outputView) {
        if (chessGameService.isGameEnd()) {
            outputView.printStatus(chessGameService.getGameResult());
            outputView.printWinner(chessGameService.getGameResult());
            outputView.printEnd();
            return GameCommand.END;
        }
        return GameCommand.MOVE;
    }
}
