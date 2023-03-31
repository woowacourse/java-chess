package chess.domain.board;

import chess.domain.piece.PieceType;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.board.coordinate.Row;

import java.util.Collections;
import java.util.List;

import static chess.domain.piece.PieceType.*;

public enum InitialPieceTypes {
    HERO_PIECE_TYPES(List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK)),
    WHITE_PAWN_TYPES(List.of(WHITE_START_PAWN, WHITE_START_PAWN, WHITE_START_PAWN, WHITE_START_PAWN, WHITE_START_PAWN, WHITE_START_PAWN, WHITE_START_PAWN, WHITE_START_PAWN)),
    BLACK_PAWN_TYPES(List.of(BLACK_START_PAWN, BLACK_START_PAWN, BLACK_START_PAWN, BLACK_START_PAWN, BLACK_START_PAWN, BLACK_START_PAWN, BLACK_START_PAWN, BLACK_START_PAWN)),
    EMPTY_TYPES(Collections.emptyList());
    
    private final List<PieceType> pieceTypes;
    
    InitialPieceTypes(List<PieceType> pieceTypes) {
        this.pieceTypes = pieceTypes;
    }
    
    public static InitialPieceTypes from(Coordinate coordinate) {
        if (isHeroPiecesRow(coordinate)) {
            return HERO_PIECE_TYPES;
        }
    
        if (isWhitePawnRow(coordinate)) {
            return WHITE_PAWN_TYPES;
        }
    
        if (isBlackPawnRow(coordinate)) {
            return BLACK_PAWN_TYPES;
        }
        
        return EMPTY_TYPES;
    }
    
    private static boolean isHeroPiecesRow(Coordinate coordinate) {
        return coordinate.isSameRow(Row.ONE) || coordinate.isSameRow(Row.EIGHT);
    }
    
    private static boolean isBlackPawnRow(Coordinate coordinate) {
        return coordinate.isSameRow(Row.SEVEN);
    }
    
    private static boolean isWhitePawnRow(Coordinate coordinate) {
        return coordinate.isSameRow(Row.TWO);
    }
    
    public PieceType findPieceTypeByColumn(Coordinate coordinate) {
        int columnIndex = coordinate.columnIndex();
        return pieceTypes.get(columnIndex);
    }
}
