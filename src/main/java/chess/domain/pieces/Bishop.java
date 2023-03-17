package chess.domain.pieces;

import static chess.domain.Pattern.DIAGONAL;

import chess.domain.Pattern;
import chess.domain.Team;
import java.util.List;

public class Bishop extends Piece {

    private final List<Pattern> patterns = List.of(DIAGONAL);

    public Bishop(final Team team) {
        super(team);
        validateTeam(team);
    }

    @Override
    public boolean hasPattern(final Pattern pattern) {
        return patterns.contains(pattern);
    }
}
