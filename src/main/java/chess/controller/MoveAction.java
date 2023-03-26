package chess.controller;

import chess.dto.ChessRequest;
import chess.dto.MoveHistory;
import chess.service.ChessGameService;
import chess.view.OutputView;

public class MoveAction implements Action {

    @Override
    public GameCommand execute(ChessGameService chessGameService, OutputView outputView, ChessRequest chessRequest) {
        if (chessGameService.isNotStart()) {
            throw new IllegalArgumentException("start를 해주세요");
        }
        MoveHistory moveHistory = MoveHistory.of(chessRequest.getSource(), chessRequest.getTarget());
        chessGameService.move(moveHistory);
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
