package chess.domain.pieces;

import chess.domain.Pattern;
import chess.domain.Team;
import java.util.List;

public class Rook extends Piece {

    private final List<Pattern> patterns = List.of(Pattern.CROSS);

    public Rook(final Team team) {
        super(team);
        validateTeam(team);
    }

    @Override
    public boolean hasPattern(Pattern pattern) {
        return patterns.contains(pattern);
    }
}
