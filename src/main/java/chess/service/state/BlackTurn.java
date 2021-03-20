package chess.service.state;

import chess.domain.grid.Grid;
import chess.domain.piece.Piece;
import chess.view.OutputView;

public class BlackTurn extends Playing {
    @Override
    GameState move(Grid grid, Piece sourcePiece, Piece targetPiece) {
        validateIfBlack(sourcePiece);
        grid.move(sourcePiece, targetPiece);

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
