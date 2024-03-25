package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Knight extends Piece {
    private static final Set<Integer> MOVE_DIFFERENCES = Set.of(1, 2);

    public Knight(Team team) {
        this(new Character(team, Kind.KNIGHT), false);
    }

    private Knight(Character character, boolean hasMoved) {
        super(character, hasMoved);
    }

    @Override
    public Piece move() {
        if (isMoved) {
            return this;
        }
        return new Knight(character, true);
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        Set<Integer> differenceSet = Stream.of(Math.abs(rowDifference), Math.abs(columnDifference))
                .collect(Collectors.toSet());
        return differenceSet.containsAll(MOVE_DIFFERENCES);
    }

    @Override
    public List<Position> findBetweenPositions(Movement movement) {
        return new ArrayList<>();
    }
}
