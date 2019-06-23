package chess.domain.piece.pieceinfo;

import chess.domain.piece.piecefigure.*;
import chess.exception.NotFoundPiecesException;

import java.util.Arrays;
import java.util.function.Function;

public enum PieceType {
    KING("k", 0, King::of),
    QUEEN("q", 9, Queen::of),
    ROOK("r",5, Rook::of),
    BISHOP("b", 3, Bishop::of),
    KNIGHT("n", 2.5, Knight::of),
    PAWN("p", 1, teamType -> (teamType == TeamType.WHITE) ? WhitePawn.of() : BlackPawn.of() ),
    BLANK(".", 0, teamType -> Blank.of());

    private final String type;
    private final double point;
    private Function<TeamType, Piece> pieceFunction;

    PieceType(final String type, final double point, Function<TeamType, Piece> pieceFunction) {
        this.type = type;
        this.point = point;
        this.pieceFunction = pieceFunction;
    }

    public String getType() {
        return type;
    }

    public double getPoint() {
        return point;
    }

    public static Piece getPiece(String type, TeamType teamType) {
        return Arrays.stream(values())
                .filter(piece -> piece.getType().equals(type.toLowerCase()))
                .findFirst()
                .orElseThrow(NotFoundPiecesException::new)
                .pieceFunction.apply(teamType);
    }
}
