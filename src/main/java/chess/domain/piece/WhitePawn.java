package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.position.Position;
import chess.domain.position.PossibleDestinations;

import java.util.List;

public class WhitePawn extends Pawn {
    private static final int WHITEPAWN_INITIAL_RANK_POSITION = 1;

    public WhitePawn(PieceType pieceType) {
        super(pieceType, TeamColor.WHITE);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Piece targetPiece) {
        final PossibleDestinations possibleDestinations;
        if (source.getRank() == WHITEPAWN_INITIAL_RANK_POSITION) {
            possibleDestinations = PossibleDestinations.of(source, List.of(Direction.UP), PAWN_FIRST_MAX_MOVE_COUNT);
            return possibleDestinations.contains(target) || canAttack(source, target, targetPiece);
        }
        possibleDestinations = PossibleDestinations.of(source, List.of(Direction.UP), PAWN_MAX_MOVE_COUNT);
        return possibleDestinations.contains(target) || canAttack(source, target, targetPiece);

    }
}
