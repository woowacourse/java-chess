package chess.service.state;

import chess.domain.piece.Piece;
import chess.service.ChessService;
import chess.view.OutputView;

public class BlackTurn extends Playing {
    @Override
    GameState move(ChessService chessService, Piece sourcePiece, Piece targetPiece) {
        validateIfBlack(sourcePiece);
        chessService.move(sourcePiece, targetPiece);

        if (catchedKing(targetPiece)) {
            OutputView.printWinner(targetPiece.isBlack());
            return new End();
        }
        return new WhiteTurn();
    }

    void validateIfBlack(Piece sourcePiece){
        if (!sourcePiece.isBlack()) {
            throw new IllegalArgumentException("자신의 말만 옮길 수 있습니다.");
        }
    }
}
