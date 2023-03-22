package chess.domain.piece;

import chess.domain.Team;

public class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(PieceType.EMPTY, Team.EMPTY);
    }

}
