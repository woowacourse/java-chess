package chess.domain.piece;

import chess.domain.board.*;

import java.util.*;

/**
 * @author heebg
 * @version 1.0 2019-06-27
 */
public class BlackPieceInit implements PieceInit {
    private static int CONVERT_ASCII = 97;
    private static String PAWN_LOCATION = "7";
    private static String OTHER_LOCATION = "8";
    private static int PAWN_LOCATION1 = 7;
    private static int OTHER_LOCATION1= 8;

    private static List<Piece> pieceOrder = Arrays.asList(
            Rook.blackCreate(), Knight.blackCreate(), Bishop.blackCreate(), Queen.blackCreate(),
            King.blackCreate(), Bishop.blackCreate(), Knight.blackCreate(), Rook.blackCreate()
    );
    @Override
    public Map<Square, Piece> create() {
        Map<Square, Piece> pieces = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            pieces.put(new Square(new Position(i + 1), new Position(PAWN_LOCATION1)), Pawn.blackCreate());
            pieces.put(new Square(new Position(i + 1), new Position(OTHER_LOCATION1)), pieceOrder.get(i));

        }

        return pieces;
    }
}
