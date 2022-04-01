package chess.turndecider.state;

import chess.domain.piece.PieceTeam;
import chess.domain.piece.Piece;

public class BlackTeam extends Running {

    private final PieceTeam pieceTeam = PieceTeam.BLACK;

    @Override
    public boolean isSameColor(Piece sourcePiece) {
        return sourcePiece.isSameColor(pieceTeam);
    }

    @Override
    public State nextState(boolean isGameFinished) {
        if (isGameFinished) {
            return new Finish();
        }
        return new WhiteTeam();
    }
}
