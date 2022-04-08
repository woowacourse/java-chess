package chess.dto;

import chess.domain.chesspiece.Bishop;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.chesspiece.King;
import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Pawn;
import chess.domain.chesspiece.Queen;
import chess.domain.chesspiece.Rook;
import java.util.Arrays;
import java.util.function.Function;

public enum ChessPieceMapper {

    KING("king", King::from),
    QUEEN("queen", Queen::from),
    BISHOP("bishop", Bishop::from),
    ROOK("rook", Rook::from),
    KNIGHT("knight", Knight::from),
    PAWN("pawn", Pawn::from),
    ;

    private final String pieceType;
    private final Function<Color, ChessPiece> toChessPiece;

    ChessPieceMapper(final String pieceType, final Function<Color, ChessPiece> toChessPiece) {
        this.pieceType = pieceType;
        this.toChessPiece = toChessPiece;
    }

    public static ChessPiece toChessPiece(final String pieceType, final String color) {
        return Arrays.stream(values())
                .filter(it -> it.pieceType.equals(pieceType))
                .map(it -> it.toChessPiece.apply(Color.from(color)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("기물을 찾을 수 없습니다."));
    }

    public static String toPieceType(final ChessPiece chessPiece) {
        return Arrays.stream(values())
                .filter(it -> it.toChessPiece.apply(chessPiece.color()).equals(chessPiece))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 기물입니다."))
                .pieceType;
    }
}
