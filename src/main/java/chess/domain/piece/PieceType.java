package chess.domain.piece;

import chess.domain.piece.coordinate.Coordinate;

import java.util.function.BiFunction;

public enum PieceType {
    KING(King::new),
    QUEEN(Queen::new),
    ROOK(Rook::new),
    KNIGHT(Knight::new),
    BISHOP(Bishop::new),
    PAWN(Pawn::new),
    EMPTY(Empty::new);
    
    private final BiFunction<Team, Coordinate, Piece> pieceMaker;
    
    PieceType(BiFunction<Team, Coordinate, Piece> pieceMaker) {
        this.pieceMaker = pieceMaker;
    }
    
    public Piece makePiece(Team team, Coordinate coordinate) {
        return pieceMaker.apply(team, coordinate);
    }
}
