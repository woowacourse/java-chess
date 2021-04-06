package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.exception.PieceNotFoundException;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceForm {
    BISHOP("bishop", Bishop::new),
    ROOK("rook", Rook::new),
    PAWN("pawn", Pawn::new),
    KNIGHT("knight", Knight::new),
    QUEEN("queen", Queen::new),
    KING("king", King::new);

    private final String name;
    private final BiFunction<TeamColor, Position, Piece> function;

    PieceForm(String name, BiFunction<TeamColor, Position, Piece> function) {
        this.name = name;
        this.function = function;
    }

    private static PieceForm pieceForm(String name) {
        return Arrays.stream(values())
            .filter(piece -> piece.name.equals(name))
            .findAny()
            .orElseThrow(PieceNotFoundException::new);
    }

    public static Piece create(String name, TeamColor teamColor, Position position) {
        return pieceForm(name)
            .function
            .apply(teamColor, position);
    }
}
