package chess.domain.piece;

import chess.domain.piece.movable.BlockedMovable;
import chess.domain.piece.movable.MovableDirections;
import chess.domain.piece.movable.PawnMovable;
import chess.domain.piece.movable.UnblockedMovable;
import chess.domain.position.Position;

public class TestPieceFactory {
    public static Piece createKing(Position position, Color color){
        return new Piece(position, PieceType.KING, new UnblockedMovable(MovableDirections.EVERY), color);
    }

    public static Piece createQueen(Position position, Color color){
        return new Piece(position, PieceType.QUEEN, new BlockedMovable(MovableDirections.EVERY), color);
    }

    public static Piece createKnight(Position position, Color color){
        return new Piece(position, PieceType.KNIGHT, new UnblockedMovable(MovableDirections.KNIGHT), color);
    }

    public static Piece createRook(Position position, Color color){
        return new Piece(position, PieceType.ROOK, new BlockedMovable(MovableDirections.LINEAR), color);
    }

    public static Piece createBishop(Position position, Color color){
        return new Piece(position, PieceType.BISHOP, new BlockedMovable(MovableDirections.DIAGONAL), color);
    }

    public static Piece createPawn(Position position, Color color){
        if(color.isWhite()) {
            return new Piece(position, PieceType.KING, new PawnMovable(MovableDirections.WHITE_PAWN), color);
        }
        return new Piece(position, PieceType.KING, new PawnMovable(MovableDirections.BLACK_PAWN), color);
    }
}
