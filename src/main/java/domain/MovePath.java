package domain;

import domain.piece.Piece;
import java.util.List;

public class MovePath {

    private final PathPieces pathPieces;
    private final Piece targetPiece;

    public MovePath(PathPieces pathPieces, Piece targetPiece) {
        this.pathPieces = pathPieces;
        this.targetPiece = targetPiece;
    }

    public static MovePath create(List<Piece> pathPieces, Piece targetPiece) {
        return new MovePath(new PathPieces(pathPieces), targetPiece);
    }

    public boolean notAllPathPiecesEmpty() {
        return pathPieces.notAllEmpty();
    }

    public Piece targetPiece() {
        return targetPiece;
    }
}
