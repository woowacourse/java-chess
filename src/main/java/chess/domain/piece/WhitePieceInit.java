package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heebg
 * @version 1.0 2019-06-27
 */
public class WhitePieceInit implements PieceInit {
    private static int CONVERT_ASCII = 97;
    private static String PAWN_LOCATION = "2";
    private static String OTHER_LOCATION = "1";

    private static List<Piece> pieceOrder = Arrays.asList(
            Rook.whiteCreate(), Knight.whiteCreate(), Bishop.whiteCreate(), Queen.whiteCreate(),
            King.whiteCreate(), Bishop.whiteCreate(), Knight.whiteCreate(), Rook.whiteCreate()
    );
    @Override
    public Map<Square, Piece> create() {
        Map<Square, Piece> pieces = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            pieces.put(new Square(new XPosition(Character.toString(i + CONVERT_ASCII)), new YPosition(PAWN_LOCATION)), Pawn.whiteCreate());
            pieces.put(new Square(new XPosition(Character.toString(i + CONVERT_ASCII)), new YPosition(OTHER_LOCATION)), pieceOrder.get(i));

        }

        return pieces;
    }
}
