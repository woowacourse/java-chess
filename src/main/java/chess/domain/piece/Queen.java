package chess.domain.piece;

import chess.domain.Team;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.piece.strategy.QueenStrategy;
import chess.domain.position.Position;

import java.util.Map;

public class Queen extends Piece {
    public static final String WHITE_QUEEN = "\u2655";
    public static final String BLACK_QUEEN = "\u265b";

    private static final MovingStrategy strategy = new QueenStrategy();

    public Queen(Team team, Position position) {
        super(team, position);
    }

    public static Queen of(Team team, Position position) {
        return new Queen(team, position);
    }

    @Override
    public String toString() {
        if (team.equals(Team.WHITE)) {
            return WHITE_QUEEN;
        }
        return BLACK_QUEEN;
    }

    @Override
    public Piece move(Position from, Position to, Map<Position, Team> dto) {
        strategy.validateMove(from, to, dto);
        return this;
    }
}
