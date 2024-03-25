package domain.route;

import domain.board.Board;
import domain.piece.Piece;
import domain.piece.Side;
import domain.square.Square;
import java.util.Objects;

public class Route {

    private final Pieces pathPieces;
    private final Piece targetPiece;

    public Route(Pieces pathPieces, Piece targetPiece) {
        this.pathPieces = pathPieces;
        this.targetPiece = targetPiece;
    }

    public static Route create(Square source, Square target, Board board) {
        Path path = Path.of(source, target);
        Pieces pathPieces = board.findPieces(path);
        Piece targetPiece = board.findPiece(target);
        return new Route(pathPieces, targetPiece);
    }

    public Piece targetPiece() {
        return targetPiece;
    }

    public boolean notAllPathPiecesEmpty() {
        return pathPieces.notAllEmpty();
    }

    public boolean isOpponentTargetPiece(Side side) {
        return targetPiece.isNotEmpty() && targetPiece.isNotSame(side);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Route route = (Route) o;
        return Objects.equals(pathPieces, route.pathPieces) && Objects.equals(targetPiece,
                route.targetPiece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathPieces, targetPiece);
    }

    public boolean isTargetPieceEmpty() {
        return targetPiece.isEmpty();
    }
}
