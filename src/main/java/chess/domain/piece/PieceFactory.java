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

        for (PositionY positionY : PositionY.values()) {
            for (PositionX positionX : PositionX.values()) {
                Position position = new Position(positionX, positionY);
                pieces.put(position, new Blank());
            }
        }

        for (PositionX positionX : PositionX.values()) {
            Position position = new Position(positionX, PositionY.RANK_2);
            pieces.replace(position, new Pawn(Color.WHITE));
        }

        for (PositionX positionX : PositionX.values()) {
            Position position = new Position(positionX, PositionY.RANK_7);
            pieces.replace(position, new Pawn(Color.BLACK));
        }

        pieces.replace(new Position(PositionX.A, PositionY.RANK_1), new Rook(Color.WHITE));
        pieces.replace(new Position(PositionX.B, PositionY.RANK_1), new Knight(Color.WHITE));
        pieces.replace(new Position(PositionX.C, PositionY.RANK_1), new Bishop(Color.WHITE));
        pieces.replace(new Position(PositionX.D, PositionY.RANK_1), new Queen(Color.WHITE));
        pieces.replace(new Position(PositionX.E, PositionY.RANK_1), new King(Color.WHITE));
        pieces.replace(new Position(PositionX.F, PositionY.RANK_1), new Bishop(Color.WHITE));
        pieces.replace(new Position(PositionX.G, PositionY.RANK_1), new Knight(Color.WHITE));
        pieces.replace(new Position(PositionX.H, PositionY.RANK_1), new Rook(Color.WHITE));

        pieces.replace(new Position(PositionX.A, PositionY.RANK_8), new Rook(Color.BLACK));
        pieces.replace(new Position(PositionX.B, PositionY.RANK_8), new Knight(Color.BLACK));
        pieces.replace(new Position(PositionX.C, PositionY.RANK_8), new Bishop(Color.BLACK));
        pieces.replace(new Position(PositionX.D, PositionY.RANK_8), new Queen(Color.BLACK));
        pieces.replace(new Position(PositionX.E, PositionY.RANK_8), new King(Color.BLACK));
        pieces.replace(new Position(PositionX.F, PositionY.RANK_8), new Bishop(Color.BLACK));
        pieces.replace(new Position(PositionX.G, PositionY.RANK_8), new Knight(Color.BLACK));
        pieces.replace(new Position(PositionX.H, PositionY.RANK_8), new Rook(Color.BLACK));

        return pieces;
    }
}
