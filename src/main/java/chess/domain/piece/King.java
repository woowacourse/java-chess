package chess.domain.piece;

import chess.domain.Team;
import chess.domain.piece.strategy.KingStrategy;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Position;

import java.util.Map;

public class King extends Piece {
    public static final String WHITE_KING = "\u2654";
    public static final String BLACK_KING = "\u265a";

    private static final MovingStrategy strategy = new KingStrategy();

    public King(Team team, Position position) {
        super(team, position);
    }

    public static King of(Team team, Position position) {
        return new King(team, position);
    }

    @Override
    public String toString() {
        if (team.equals(Team.WHITE)) {
            return WHITE_KING;
        }
        return BLACK_KING;
    }

    @Override
    public Piece move(Position from, Position to, Map<Position, Team> dto) {
        return null;
    }
}