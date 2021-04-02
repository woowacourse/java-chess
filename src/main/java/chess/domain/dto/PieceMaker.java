package chess.domain.dto;

import chess.domain.piece.*;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;

import java.util.Arrays;

public enum PieceMaker {
    KING(King::new),
    QUEEN(Queen::new),
    BISHOP(Bishop::new),
    ROOK(Rook::new),
    KNIGHT(Knight::new),
    PAWN(Pawn::new),
    BLANK(((teamColor, position, state) -> Blank.INSTANCE));

    private PieceFunction<TeamColor, Position, State, Piece> expression;

    PieceMaker(PieceFunction<TeamColor, Position, State, Piece> expression) {
        this.expression = expression;
    }

    public static Piece getInstance(String pieceType, TeamColor teamColor, Position position, State state) {
        return Arrays.stream(PieceMaker.values())
                .filter(piece -> piece.name().equals(pieceType))
                .map(piece -> piece.expression.apply(teamColor, position, state))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당되는 피스가 없어요"));
    }
}
