package domain;

import domain.piece.Piece;
import java.util.List;

public class MovePath {

    private final Path path;
    private final Piece targetPiece;

    public MovePath(Path path, Piece targetPiece) {
        this.path = path;
        this.targetPiece = targetPiece;
    }

    public static MovePath create(List<Piece> pathPieces, Piece targetPiece) {
        return null;
//        return new MovePath(new Path(pathPieces), targetPiece);
    }

    public Piece targetPiece() {
        return targetPiece;
    }

    public boolean notAllPathPiecesEmpty() {
        return true;
//        return path.notAllEmpty();
    }
}
