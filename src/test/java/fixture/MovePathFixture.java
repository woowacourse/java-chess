package fixture;

import domain.MovePath;
import domain.Pieces;
import domain.piece.Empty;
import domain.piece.Piece;
import java.util.Arrays;
import java.util.List;

public class MovePathFixture {

    public static MovePath noPieces() {
        return new MovePath(new Pieces(), new Empty());
    }

    public static MovePath hasPathPieces(Piece... pieces) {
        List<Piece> pathPieces = (List<Piece>) Arrays.asList(pieces);
        return new MovePath(new Pieces(pathPieces), new Empty());
    }

    public static MovePath hasTargetPiece(Piece target) {
        return new MovePath(new Pieces(), target);
    }
}
