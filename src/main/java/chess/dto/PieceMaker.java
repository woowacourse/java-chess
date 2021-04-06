package chess.dto;

import chess.domain.piece.*;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceMaker {
    KING(King::new),
    QUEEN(Queen::new),
    BISHOP(Bishop::new),
    ROOK(Rook::new),
    KNIGHT(Knight::new),
    PAWN(Pawn::new),
    BLANK(((teamColor, state) -> Blank.INSTANCE));

    private BiFunction<TeamColor, State, Piece> expression;

    PieceMaker(BiFunction<TeamColor, State, Piece> expression) {
        this.expression = expression;
    }

    public static Piece getInstance(String pieceType, TeamColor teamColor, State state) {
        return Arrays.stream(PieceMaker.values())
                .filter(piece -> piece.name().equals(pieceType))
                .map(piece -> piece.expression.apply(teamColor, state))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당되는 피스가 없어요"));
    }
}
