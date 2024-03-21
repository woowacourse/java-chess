package chess.domain.chessPiece;

import chess.domain.Position;
import chess.domain.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.domain.chessPiece.Role.*;

public class Knight extends Piece {

    public Knight(Team team) {
        super(team);
    }

    @Override
    public List<Position> getRoute(Position source, Position target) {
        List<Position> route = new ArrayList<>();
        validateMovingRule(source, target);
        return Collections.unmodifiableList(route);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        int rowDistance = source.calculateRowDistance(target);
        int colDistance = source.calculateColumnDistance(target);

        if (!(rowDistance == 2 && colDistance == 1 || rowDistance == 1 && colDistance == 2)) {
            throw new IllegalArgumentException("x");
        }
    }

    @Override
    public Role getRole() {
        if (team.isWhite()) {
            return WHITE_KNIGHT;
        }
        return BLACK_KNIGHT;
    }
}
