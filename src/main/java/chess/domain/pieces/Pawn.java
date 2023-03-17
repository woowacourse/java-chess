package chess.domain.pieces;

import static chess.domain.Pattern.DOWN_1;
import static chess.domain.Pattern.PAWN_DOWN_2;
import static chess.domain.Pattern.PAWN_UP_2;
import static chess.domain.Pattern.UP_1;
import static chess.domain.Team.BLACK;

import chess.domain.Pattern;
import chess.domain.Team;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private final List<Pattern> patterns;

    public Pawn(final Team team) {
        super(team);
        validateTeam(team);
        this.patterns = initPatterns(team);
    }

    private List<Pattern> initPatterns(final Team team) {
        if (team == BLACK) {
            return new ArrayList<>(List.of(DOWN_1, PAWN_DOWN_2));
        }
        return new ArrayList<>(List.of(UP_1, PAWN_UP_2));
    }

    public void removePawn2Pattern() {
        if (hasPattern(PAWN_DOWN_2)) {
            patterns.remove(PAWN_DOWN_2);
        }
    }

    @Override
    public boolean hasPattern(final Pattern pattern) {
        return this.patterns.contains(pattern);
    }
}
