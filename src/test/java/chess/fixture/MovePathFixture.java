package chess.fixture;

import chess.domain.route.Route;
import chess.domain.route.Pieces;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.List;

public class MovePathFixture {

    public static Route noPieces() {
        return new Route(new Pieces(), new Empty());
    }

    public static Route hasPathPieces(Piece... pieces) {
        List<Piece> pathPieces = Arrays.asList(pieces);
        return new Route(new Pieces(pathPieces), new Empty());
    }

    public static Route hasTargetPiece(Piece target) {
        return new Route(new Pieces(), target);
    }
}
