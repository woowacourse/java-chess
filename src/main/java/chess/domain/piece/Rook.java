package chess.domain.piece;

import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.abstractPiece.SlidingPiece;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public class Rook extends SlidingPiece {
    public Rook(Team team) {
        this(new Character(team, Kind.ROOK), false);
    }

    public Rook(Team team, boolean isMoved) {
        this(new Character(team, Kind.ROOK), isMoved);
    }

    private Rook(Character character, boolean hasMoved) {
        super(character, hasMoved);
    }

    @Override
    public Piece move() {
        if (isMoved) {
            return this;
        }
        return new Rook(character, true);
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        return rowDifference == 0 || columnDifference == 0;
    }

}
