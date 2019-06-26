package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;

import java.util.HashMap;
import java.util.Map;

/**
 * @author heebg
 * @version 1.0 2019-06-27
 */
public class BlackPieceInit implements PieceInit {
    @Override
    public Map<Square, Piece> create() {
        Map<Square, Piece> pieces = new HashMap<>();

        // Pawn
        for (char i = 'a'; i <= 'h'; i++) {
            pieces.put(new Square(new XPosition(String.valueOf(i)), new YPosition("7")), Pawn.blackCreate());
        }

        // Knight
        pieces.put(new Square(new XPosition("b"), new YPosition("8")), Knight.blackCreate());
        pieces.put(new Square(new XPosition("g"), new YPosition("8")), Knight.blackCreate());

        // Rook
        pieces.put(new Square(new XPosition("a"), new YPosition("8")), Rook.blackCreate());
        pieces.put(new Square(new XPosition("h"), new YPosition("8")), Rook.blackCreate());

        // BishopPath
        pieces.put(new Square(new XPosition("c"), new YPosition("8")), Bishop.blackCreate());
        pieces.put(new Square(new XPosition("f"), new YPosition("8")), Bishop.blackCreate());

        //Queen
        pieces.put(new Square(new XPosition("d"), new YPosition("8")), Queen.blackCreate());

        //King
        pieces.put(new Square(new XPosition("e"), new YPosition("8")), King.blackCreate());

        return pieces;
    }
}
