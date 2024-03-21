package chess.domain.piece;

import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Knight extends Piece {
    private static final Set<Integer> MOVE_DIFFERENCES = Set.of(1, 2);

    public Knight(Team team, boolean hasNotMoved) {
        super(team, hasNotMoved);
    }

    @Override
    public Piece move() {
        if (hasNotMoved) {
            return new Knight(team, false);
        }
        return this;
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.KNIGHT);
    }

    @Override
    protected List<Position> findBetweenPositions(Position position, int rowDifference, int columnDifference) {
        return new ArrayList<>();
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        List<Integer> differenceList = List.of(Math.abs(rowDifference), Math.abs(columnDifference));
        return differenceList.containsAll(MOVE_DIFFERENCES);
    }
}
