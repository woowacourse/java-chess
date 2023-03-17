package chess.domain.pieces;

import chess.domain.Pattern;
import chess.domain.Team;
import java.util.List;

public class Queen extends Piece {

    private final List<Pattern> patterns = List.of(Pattern.CROSS, Pattern.DIAGONAL);

    public Queen(final Team team) {
        super(team);
        validateTeam(team);
    }

    @Override
    public boolean hasPattern(final Pattern pattern) {
        return patterns.contains(pattern);
    }
}
