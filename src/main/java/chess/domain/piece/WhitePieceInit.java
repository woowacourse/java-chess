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
public class WhitePieceInit implements PieceInit {
    @Override
    public Map<Square, Piece> create() {
        Map<Square, Piece> pieces = new HashMap<>();

        // Pawn
        for (char i = 'a'; i <= 'h'; i++) {
            pieces.put(new Square(new XPosition(String.valueOf(i)), new YPosition("2")), Pawn.whiteCreate());
        }

        // Knight
        pieces.put(new Square(new XPosition("b"), new YPosition("1")), Knight.whiteCreate());
        pieces.put(new Square(new XPosition("g"), new YPosition("1")), Knight.whiteCreate());

        // Rook
        pieces.put(new Square(new XPosition("a"), new YPosition("1")), Rook.whiteCreate());
        pieces.put(new Square(new XPosition("h"), new YPosition("1")), Rook.whiteCreate());

        // BishopPath
        pieces.put(new Square(new XPosition("c"), new YPosition("1")), Bishop.whiteCreate());
        pieces.put(new Square(new XPosition("f"), new YPosition("1")), Bishop.whiteCreate());

        //Queen
        pieces.put(new Square(new XPosition("d"), new YPosition("1")), Queen.whiteCreate());

        //King
        pieces.put(new Square(new XPosition("e"), new YPosition("1")), King.whiteCreate());

        return pieces;
    }
}
