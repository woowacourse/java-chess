package chess.domain.piece;

import chess.domain.piece.position.Position;
import chess.domain.piece.team.BlackTeam;
import chess.domain.piece.team.TeamStrategy;
import chess.domain.piece.team.WhiteTeam;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceFactory {
    P(Pawn::new),
    R(Rook::new),
    N(Knight::new),
    B(Bishop::new),
    Q(Queen::new),
    K(King::new);

    private static final String TEAM_PIVOT = "a";
    private static final int FIND_TEAM_VALUE = 0;
    private final BiFunction<Position, TeamStrategy, Piece> creator;

    PieceFactory(BiFunction<Position, TeamStrategy, Piece> creator) {
        this.creator = creator;
    }

    public static Piece create(String name, Position position) {
        TeamStrategy team = name.compareTo(TEAM_PIVOT) < FIND_TEAM_VALUE ? new WhiteTeam() : new BlackTeam();
        return Arrays.stream(values())
                .filter(pieceFactory -> pieceFactory.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .creator.apply(position, team);
    }
}
