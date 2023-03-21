package chess.domain.board;

import chess.domain.piece.PieceType;
import chess.domain.piece.coordinate.Coordinate;
import chess.domain.piece.coordinate.Row;

import java.util.List;

import static chess.domain.piece.PieceType.*;

public enum InitialPieceTypes {
    HERO_PIECE_TYPES(List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK)),
    PAWN_TYPES(List.of(PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN)),
    EMPTY_TYPES(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY));
    
    private final List<PieceType> pieceTypes;
    
    InitialPieceTypes(List<PieceType> pieceTypes) {
        this.pieceTypes = pieceTypes;
    }
    
    public static InitialPieceTypes from(Coordinate coordinate) {
        if (isHeroPiecesRow(coordinate)) {
            return HERO_PIECE_TYPES;
        }
    
        if (isPawnRow(coordinate)) {
            return PAWN_TYPES;
        }
        
        return EMPTY_TYPES;
    }
    
    private static boolean isHeroPiecesRow(Coordinate coordinate) {
        return coordinate.isSameRow(Row.ONE) || coordinate.isSameRow(Row.EIGHT);
    }
    
    private static boolean isPawnRow(Coordinate coordinate) {
        return coordinate.isSameRow(Row.TWO) || coordinate.isSameRow(Row.SEVEN);
    }
    
    public PieceType findPieceTypeByColumn(Coordinate coordinate) {
        int columnIndex = coordinate.columnIndexAtRowPieces();
        return pieceTypes.get(columnIndex);
    }
}
