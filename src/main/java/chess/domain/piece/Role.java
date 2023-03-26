package chess.domain.piece;

import chess.domain.Team;
import java.util.function.Function;

public enum Role { // TODO: 2023/03/24 팩터리로 양방향 의존 끊기

    PAWN(1, Pawn::new),
    ROOK(5, Rook::new),
    KNIGHT(2.5, Knight::new),
    BISHOP(3, Bishop::new),
    QUEEN(9, Queen::new),
    KING(0, King::new),
    BLANK(0, ignore -> Blank.getInstance()),
    ;

    private final double score;
    private final Function<Team, Piece> constructor;

    Role(final double score, final Function<Team, Piece> constructor) {
        this.score = score;
        this.constructor = constructor;
    }

    public Piece createPiece(Team team) {
        return constructor.apply(team);
    }

    public double getScore() {
        return score;
    }
}
