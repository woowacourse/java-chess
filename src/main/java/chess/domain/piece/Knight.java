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


    public Knight(Team team) {
        this(team, false);
    }

    private Knight(Team team, boolean hasMoved) {
        super(team, hasMoved);
    }

    @Override
    public Piece move() {
        if (hasMoved) {
            return this;
        }
        return new Knight(team, true);
    }

    @Override
    public Character findCharacter() {
        return Character.findCharacter(team, Kind.KNIGHT);
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        List<Integer> differenceList = List.of(Math.abs(rowDifference), Math.abs(columnDifference));
        return differenceList.containsAll(MOVE_DIFFERENCES);
    }

    @Override
    protected List<Position> findBetweenPositions(Position position, int rowDifference, int columnDifference) {
        return new ArrayList<>();
    }
}
