package domain;

import domain.piece.Piece;
import java.util.Objects;

public class MovePath {

    private final Pieces pathPieces;
    private final Piece targetPiece;

    public MovePath(Pieces pathPieces, Piece targetPiece) {
        this.pathPieces = pathPieces;
        this.targetPiece = targetPiece;
    }

    public static MovePath create(Position source, Position target, ChessBoard chessBoard) {
        Path path = Path.of(source, target);
        Pieces pathPieces = chessBoard.findPieces(path);
        Piece targetPiece = chessBoard.findPiece(target);
        return new MovePath(pathPieces, targetPiece);
    }

    public Piece targetPiece() {
        return targetPiece;
    }

    public boolean notAllPathPiecesEmpty() {
        return pathPieces.notAllEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovePath movePath = (MovePath) o;
        return Objects.equals(pathPieces, movePath.pathPieces) && Objects.equals(targetPiece,
                movePath.targetPiece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathPieces, targetPiece);
    }
}
