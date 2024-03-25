package fixture;

import domain.route.Route;
import domain.route.Pieces;
import domain.piece.Empty;
import domain.piece.Piece;
import java.util.Arrays;
import java.util.List;

public class MovePathFixture {

    public static Route noPieces() {
        return new Route(new Pieces(), new Empty());
    }

    public static Route hasPathPieces(Piece... pieces) {
        List<Piece> pathPieces = (List<Piece>) Arrays.asList(pieces);
        return new Route(new Pieces(pathPieces), new Empty());
    }

    public static Route hasTargetPiece(Piece target) {
        return new Route(new Pieces(), target);
    }
}
