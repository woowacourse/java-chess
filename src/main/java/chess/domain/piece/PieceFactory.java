package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;
import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    public static Map<Position, Piece> createChessPieces() {
        Map<Position, Piece> pieces = new HashMap<>();

        putPiecesExceptPawnOnRank(pieces, PositionY.RANK_1, Color.WHITE);
        putPawnOnRank(pieces, PositionY.RANK_2, Color.WHITE);
        putBlankOnRank(pieces, PositionY.RANK_3);
        putBlankOnRank(pieces, PositionY.RANK_4);
        putBlankOnRank(pieces, PositionY.RANK_5);
        putBlankOnRank(pieces, PositionY.RANK_6);
        putPawnOnRank(pieces, PositionY.RANK_7, Color.BLACK);
        putPiecesExceptPawnOnRank(pieces, PositionY.RANK_8, Color.BLACK);

        return pieces;
    }

    private static void putPiecesExceptPawnOnRank(Map<Position, Piece> pieces, PositionY positionY, Color color) {
        pieces.put(new Position(PositionX.A, positionY), new Rook(color));
        pieces.put(new Position(PositionX.B, positionY), new Knight(color));
        pieces.put(new Position(PositionX.C, positionY), new Bishop(color));
        pieces.put(new Position(PositionX.D, positionY), new Queen(color));
        pieces.put(new Position(PositionX.E, positionY), new King(color));
        pieces.put(new Position(PositionX.F, positionY), new Bishop(color));
        pieces.put(new Position(PositionX.G, positionY), new Knight(color));
        pieces.put(new Position(PositionX.H, positionY), new Rook(color));
    }

    private static void putPawnOnRank(Map<Position, Piece> pieces, PositionY positionY, Color color) {
        for (PositionX positionX : PositionX.values()) {
            Position position = new Position(positionX, positionY);
            pieces.put(position, new Pawn(color));
        }
    }

    private static void putBlankOnRank(Map<Position, Piece> pieces, PositionY positionY) {
        for (PositionX positionX : PositionX.values()) {
            Position position = new Position(positionX, positionY);
            pieces.put(position, new Blank());
        }
    }
}
