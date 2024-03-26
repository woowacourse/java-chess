package chess.domain.route;

import chess.domain.board.Board;
import chess.domain.piece.Side;
import chess.domain.position.Position;
import chess.domain.piece.Piece;
import java.util.Objects;

public class Route {

    private final Pieces pathPieces;
    private final Piece targetPiece;

    public Route(Pieces pathPieces, Piece targetPiece) {
        this.pathPieces = pathPieces;
        this.targetPiece = targetPiece;
    }

    public static Route create(Position source, Position target, Board board) {
        Path path = Path.of(source, target);
        Pieces pathPieces = board.findPieces(path);
        Piece targetPiece = board.findPiece(target);
        return new Route(pathPieces, targetPiece);
    }

    public boolean notAllPathPiecesEmpty() {
        return pathPieces.notAllEmpty();
    }

    public boolean isTargetPieceEmpty() {
        return targetPiece.isEmpty();
    }

    public boolean isOpponentTargetPiece(Side side) {
        return targetPiece.isOpponentSide(side);
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
}
