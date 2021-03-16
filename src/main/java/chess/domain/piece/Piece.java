package chess.domain.piece;

public class Piece {

    private PieceCandidates pieceCandidate;
    private PieceColor pieceColor;

    public Piece(PieceCandidates pieceCandidates, PieceColor pieceColor) {
        this.pieceCandidate = pieceCandidates;
        this.pieceColor = pieceColor;
    }

    public boolean isSameColor(Piece piece) {
        return this.pieceColor == piece.getPieceColor();
    }

    public String getSymbol() {
        return pieceCandidate.getName(pieceColor);
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }
}
