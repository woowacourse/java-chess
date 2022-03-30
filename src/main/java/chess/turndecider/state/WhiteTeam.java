package chess.turndecider.state;

import chess.domain.piece.PieceColor;
import chess.domain.piece.Piece;

public class WhiteTeam extends Running {

    private final PieceColor pieceColor = PieceColor.WHITE;

    @Override
    public boolean isSameColor(Piece sourcePiece) {
        return sourcePiece.isSameColor(pieceColor);
    }

    @Override
    public State nextState(boolean isGameFinished) {
        if (isGameFinished) {
            return new Finish();
        }
        return new BlackTeam();
    }
}
