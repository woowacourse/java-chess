package chess.domain.pieces;

import static chess.domain.Pattern.UP_1;
import static chess.domain.Pattern.DOWN_1;
import static chess.domain.Pattern.LEFT_1;
import static chess.domain.Pattern.RIGHT_1;

import chess.domain.Pattern;
import chess.domain.Team;
import java.util.List;

public class King extends Piece {

    private final List<Pattern> patterns = List.of(UP_1, DOWN_1, LEFT_1, RIGHT_1);

    public King(final Team team) {
        super(team);
        validateTeam(team);
    }

    @Override
    public boolean hasPattern(final Pattern pattern) {
        return patterns.contains(pattern);
    }
}
