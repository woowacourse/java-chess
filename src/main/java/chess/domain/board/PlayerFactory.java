package chess.domain.board;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class PlayerFactory {
    public static Map<Square, Piece> init(PieceColor color) {
        Map<Square, Piece> pieces = new HashMap<>();

        if (color == PieceColor.WHITE) {
            // Pawn
            pieces.put(new Square(new XPosition("a"), new YPosition("2")), Pawn.whiteCreate());
            pieces.put(new Square(new XPosition("b"), new YPosition("2")), Pawn.whiteCreate());
            pieces.put(new Square(new XPosition("c"), new YPosition("2")), Pawn.whiteCreate());
            pieces.put(new Square(new XPosition("d"), new YPosition("2")), Pawn.whiteCreate());
            pieces.put(new Square(new XPosition("e"), new YPosition("2")), Pawn.whiteCreate());
            pieces.put(new Square(new XPosition("f"), new YPosition("2")), Pawn.whiteCreate());
            pieces.put(new Square(new XPosition("g"), new YPosition("2")), Pawn.whiteCreate());
            pieces.put(new Square(new XPosition("h"), new YPosition("2")), Pawn.whiteCreate());

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

        // black
        // Pawn
        pieces.put(new Square(new XPosition("a"), new YPosition("7")), Pawn.blackCreate());
        pieces.put(new Square(new XPosition("b"), new YPosition("7")), Pawn.blackCreate());
        pieces.put(new Square(new XPosition("c"), new YPosition("7")), Pawn.blackCreate());
        pieces.put(new Square(new XPosition("d"), new YPosition("7")), Pawn.blackCreate());
        pieces.put(new Square(new XPosition("e"), new YPosition("7")), Pawn.blackCreate());
        pieces.put(new Square(new XPosition("f"), new YPosition("7")), Pawn.blackCreate());
        pieces.put(new Square(new XPosition("g"), new YPosition("7")), Pawn.blackCreate());
        pieces.put(new Square(new XPosition("h"), new YPosition("7")), Pawn.blackCreate());

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
