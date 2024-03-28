package chess.domain.piece;

import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.abstractPiece.UnslidingPiece;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Knight extends UnslidingPiece {
    private static final Set<Integer> MOVE_DIFFERENCES = Set.of(1, 2);

    public Knight(Team team) {
        this(new Character(team, Kind.KNIGHT), false);
    }

    public Knight(Team team, boolean isMoved) {
        this(new Character(team, Kind.KNIGHT), isMoved);
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
}
