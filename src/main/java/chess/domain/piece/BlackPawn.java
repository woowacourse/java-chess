package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.position.Position;
import chess.domain.position.PossibleDestinations;

import java.util.List;

public class BlackPawn extends Pawn {
    private static final int BLACKPAWN_INITIAL_RANK_POSITION = 6;

    public BlackPawn(PieceType pieceType) {
        super(pieceType, TeamColor.BLACK);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Piece targetPiece) {
        final PossibleDestinations possibleDestinations;
        if (source.getRank() == BLACKPAWN_INITIAL_RANK_POSITION) {
            possibleDestinations = PossibleDestinations.of(source, List.of(Direction.DOWN), PAWN_FIRST_MAX_MOVE_COUNT);
            return possibleDestinations.contains(target) || canAttack(source, target, targetPiece);
        }
        possibleDestinations = PossibleDestinations.of(source, List.of(Direction.DOWN), PAWN_MAX_MOVE_COUNT);
        return possibleDestinations.contains(target) || canAttack(source, target, targetPiece);
    }
}
