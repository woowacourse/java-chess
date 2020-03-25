package chess.domain.piece;

public class Piece {
    private final PieceType pieceType;
    private final Team team;

    public Piece(final PieceType pieceType, final Team team) {
        this.pieceType = pieceType;
        this.team = team;
    }

    public String toSymbol() {
        return this.team.convert(this.pieceType.getSymbol());
    }
}
