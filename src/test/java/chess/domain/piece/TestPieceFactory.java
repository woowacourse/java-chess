package chess.domain.piece;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.movable.BlockedMovable;
import chess.domain.piece.movable.Directions;
import chess.domain.piece.movable.PawnMovable;
import chess.domain.piece.movable.UnblockedMovable;
import chess.domain.position.Position;

public class TestPieceFactory {
    public static Piece createKing(Position position, Color color){
        return new Piece(position, PieceType.KING, new UnblockedMovable(Directions.EVERY), color);
    }

    public static Piece createQueen(Position position, Color color){
        return new Piece(position, PieceType.QUEEN, new BlockedMovable(Directions.EVERY), color);
    }

    public static Piece createKnight(Position position, Color color){
        return new Piece(position, PieceType.KNIGHT, new UnblockedMovable(Directions.KNIGHT), color);
    }

    public static Piece createRook(Position position, Color color){
        return new Piece(position, PieceType.ROOK, new BlockedMovable(Directions.LINEAR), color);
    }

    public static Piece createBishop(Position position, Color color){
        return new Piece(position, PieceType.BISHOP, new BlockedMovable(Directions.DIAGONAL), color);
    }

    public static Piece createPawn(Position position, Color color){
        if(color.isWhite()) {
            return new Piece(position, PieceType.KING, new PawnMovable(Directions.WHITEPAWN), color);
        }
        return new Piece(position, PieceType.KING, new PawnMovable(Directions.BLACKPAWN), color);
    }
}
