package chess.service.state;

import chess.domain.grid.Grid;
import chess.domain.piece.Piece;
import chess.view.OutputView;

public class WhiteTurn extends Playing {

    @Override
    GameState move(Grid grid, Piece sourcePiece, Piece targetPiece) {
        validateIfWhite(sourcePiece);
        grid.move(sourcePiece, targetPiece);

        if (catchedKing(targetPiece)) {
            OutputView.printWinner(!targetPiece.isBlack());
            return new End();
        }
        return new BlackTurn();
    }

    void validateIfWhite(Piece sourcePiece){
        if (sourcePiece.isBlack()) {
            throw new IllegalArgumentException("자신의 말만 옮길 수 있습니다.");
        }
    }
}
