package chess.domain.piece.position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PiecePositionFixture {

    public static List<PiecePosition> piecePositions(final String... positions) {
        return Arrays.stream(positions).map(PiecePosition::of).collect(Collectors.toList());
    }
}
