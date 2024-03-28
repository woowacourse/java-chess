package chess.domain.piece;

import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.abstractPiece.SlidingPiece;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class Queen extends SlidingPiece {

    public Queen(Team team) {
        this(new Character(team, Kind.QUEEN), false);
    }

    public Queen(Team team, boolean isMoved) {
        this(new Character(team, Kind.QUEEN), isMoved);
    }

    private Queen(Character character, boolean hasMoved) {
        super(character, hasMoved);
    }

    @Override
    public Piece move() {
        if (isMoved) {
            return this;
        }
        return new Queen(character, true);
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        return (rowDifference == 0 || columnDifference == 0)
                || Math.abs(rowDifference) == Math.abs(columnDifference);
    }
}
