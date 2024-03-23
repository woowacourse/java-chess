package chess.domain.piece;

public class Empty extends AbstractPiece {

    public Empty() {
        super(PieceType.EMPTY, Team.EMPTY);
    }
}
