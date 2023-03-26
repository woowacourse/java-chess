package chess.dao;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PieceMapper {
    static Map<PieceType, Function<Color, Piece>> pieceMapper = new HashMap<>();

    static {
        pieceMapper.put(PieceType.PAWN, Pawn::from);
        pieceMapper.put(PieceType.ROOK, Rook::from);
        pieceMapper.put(PieceType.KNIGHT, Knight::from);
        pieceMapper.put(PieceType.BISHOP, Bishop::from);
        pieceMapper.put(PieceType.QUEEN, Queen::from);
        pieceMapper.put(PieceType.KING, King::from);
        pieceMapper.put(PieceType.EMPTY, color -> Empty.create());
    }

    public static Piece apply(PieceType pieceType, Color color) {
        return pieceMapper.get(pieceType).apply(color);
    }
}
