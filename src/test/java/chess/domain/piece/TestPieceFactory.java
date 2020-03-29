package chess.domain.piece;

import chess.domain.piece.movable.BlockedMovable;
import chess.domain.piece.movable.Directions;
import chess.domain.piece.movable.PawnMovable;
import chess.domain.piece.movable.UnblockedMovable;
import chess.domain.position.Position;

public class TestPieceFactory {
    // TODO: 2020/03/29 createBy(Type) 으로 하면 메서드 1개로 줄일 수 있음.
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
