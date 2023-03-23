package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.PossibleDestinations;

import java.util.List;
import java.util.Objects;

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
            return (possibleDestinations.contains(target) && Objects.isNull(targetPiece)) || canAttack(source, target, targetPiece);
        }
        possibleDestinations = PossibleDestinations.of(source, List.of(Direction.UP), PAWN_MAX_MOVE_COUNT);
        return (possibleDestinations.contains(target) && Objects.isNull(targetPiece)) || canAttack(source, target, targetPiece);
    }

    @Override
    boolean canAttack(Position source, Position target, Piece piece) {
        return source.isUpsideDiagonalPosition(target) && !Objects.isNull(piece) && !piece.isSameTeam(teamColor);
    }
}
