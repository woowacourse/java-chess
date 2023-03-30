package chess.controller.state;

import chess.controller.BoardResponse;
import chess.model.Scores;
import chess.model.piece.PieceColor;
import chess.model.position.Position;
import chess.service.ChessService;
import chess.view.OutputView;

public class Playing extends ProgressState {

    public Playing(final ChessService chessService) {
        super(chessService);
    }

    @Override
    public void executeAndSave(final Position source, final Position target) {
        chessService.move(source, target);
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public boolean hasGame() {
        return chessService.hasGame();
    }

    @Override
    public PieceColor findCurrentPlayer() {
        return chessService.findCurrentPlayer();
    }

    @Override
    public final Scores calculateScores() {
        throw new UnsupportedOperationException("지원하지 않는 기능 입니다.");
    }

    @Override
    public void printScores(final OutputView outputView) {
        return;
    }

    @Override
    public void printBoardStatus(final OutputView outputView) {
        outputView.printCurrentPlayer(findCurrentPlayer());
        outputView.printChessBoard(new BoardResponse(getBoard()));
    }
}
