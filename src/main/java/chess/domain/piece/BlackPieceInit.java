package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;

import java.util.*;

/**
 * @author heebg
 * @version 1.0 2019-06-27
 */
public class BlackPieceInit implements PieceInit {
    private static int CONVERT_ASCII = 97;
    private static String PAWN_LOCATION = "7";
    private static String OTHER_LOCATION = "8";

    private static List<Piece> pieceOrder = Arrays.asList(
            Rook.blackCreate(), Knight.blackCreate(), Bishop.blackCreate(), Queen.blackCreate(),
            King.blackCreate(), Bishop.blackCreate(), Knight.blackCreate(), Rook.blackCreate()
    );

    @Override
    public Map<Square, Piece> create() {
        Map<Square, Piece> pieces = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            pieces.put(new Square(new XPosition(Character.toString(i + CONVERT_ASCII)), new YPosition(PAWN_LOCATION)), Pawn.blackCreate());
            pieces.put(new Square(new XPosition(Character.toString(i + CONVERT_ASCII)), new YPosition(OTHER_LOCATION)), pieceOrder.get(i));
        }

        return pieces;
    }
}
