package fixture;

import domain.MovePath;
import domain.Path;
import domain.piece.Empty;
import domain.piece.Piece;
import java.util.Arrays;

public class MovePathFixture {

    public static MovePath noPieces() {
        return new MovePath(new Path(), new Empty());
    }

    public static MovePath hasPathPieces(Piece... pieces) {
        return MovePath.create(Arrays.asList(pieces), new Empty());
    }

    public static MovePath hasTargetPiece(Piece target) {
        return new MovePath(new Path(), target);
    }
}
