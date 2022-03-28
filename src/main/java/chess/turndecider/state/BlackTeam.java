package chess.turndecider.state;

import chess.domain.piece.constant.PieceColor;
import chess.domain.piece.Piece;

public class BlackTeam extends Running {

    private final PieceColor pieceColor = PieceColor.BLACK;

    @Override
    public boolean isSameColor(Piece sourcePiece) {
        return sourcePiece.isSameColor(pieceColor);
    }

    @Override
    public State nextState(boolean isGameFinished) {
        if (isGameFinished) {
            return new Finish();
        }
        return new WhiteTeam();
    }
}
