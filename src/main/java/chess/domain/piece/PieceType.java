package chess.domain.piece;

import chess.domain.piece.strategy.*;
import chess.dto.PieceDto;
import java.util.Arrays;
import java.util.function.Function;

public enum PieceType {
    ROOK("rook", color -> new RealPiece(color, new RookStrategy())),
    BISHOP("bishop", color -> new RealPiece(color, new BishopStrategy())),
    KNIGHT("knight", color -> new RealPiece(color, new KnightStrategy())),
    QUEEN("queen", color -> new RealPiece(color, new QueenStrategy())),
    KING("king", color -> new RealPiece(color, new KingStrategy())),
    WHITE_PAWN("white pawn", color -> new RealPiece(color, new WhitePawnStrategy())),
    BLACK_PAWN("black pawn", color -> new RealPiece(color, new BlackPawnStrategy())),
    MOVED_WHITE_PAWN("moved white pawn", color -> new RealPiece(color, new MovedWhitePawnStrategy())),
    MOVED_BLACK_PAWN("moved black pawn", color -> new RealPiece(color, new MovedBlackPawnStrategy())),
    EMPTY("empty", color -> EmptyPiece.getInstance());

    private final String name;
    private final Function<Color, Piece> pieceFunction;

    PieceType(String name, Function<Color, Piece> pieceFunction) {
        this.name = name;
        this.pieceFunction = pieceFunction;
    }

    public static Piece createPiece(PieceDto pieceDto) {
        PieceType pieceType = find(pieceDto.type());
        Color color = Color.find(pieceDto.color());
        Function<Color, Piece> createFunction = pieceType.pieceFunction;
        return createFunction.apply(color);
    }

    private static PieceType find(String name) {
        return Arrays.stream(PieceType.values())
                .filter(pieceType -> pieceType.name.equals(name))
                .findFirst()
                .orElse(PieceType.EMPTY);
    }
}
