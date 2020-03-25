package chess.domain.chessPiece;

import chess.domain.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movefactory.MoveFactory;
import chess.domain.movefactory.Straight;

public class Pawn extends Piece {
    private final Position startPosition;

    public Pawn(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
        startPosition = position;
    }

    @Override
    public boolean isMovable(Position targetPosition, Piece targetPiece) {
        validSameTeam(targetPiece);
        if (this.teamStrategy instanceof BlackTeam) {
            return blackPawnMovable(targetPosition, targetPiece);
        }
        return whitePawnMovable(targetPosition, targetPiece);

    }

    private boolean whitePawnMovable(Position targetPosition, Piece targetPiece) {
        if (targetPiece == null) {
            if (this.position.equals(startPosition)) {
                return isWhitePawnFirstMove(targetPosition);
            }
            return isWhitePawnMove(targetPosition);
        }
        return isWhitePawnAttack(targetPosition);
    }

    private boolean blackPawnMovable(Position targetPosition, Piece targetPiece) {
        if (targetPiece == null) {
            if (this.position.equals(startPosition)) {
                return MoveFactory.of(position, targetPosition) instanceof Straight;
            }
            return isBlackPawnMove(targetPosition);
        }
        return isBlackPawnAttack(targetPosition);
    }

    private boolean isWhitePawnAttack(Position targetPosition) {
        return Math.abs(position.calculateFileDistance(targetPosition)) == 1 && position.calculateRankDistance(targetPosition) == 1;
    }

    private boolean isWhitePawnMove(Position targetPosition) {
        return position.isSameFile(targetPosition) && position.calculateRankDistance(targetPosition) == 1;
    }

    private boolean isWhitePawnFirstMove(Position targetPosition) {
        return position.isSameFile(targetPosition) &&
                (position.calculateRankDistance(targetPosition) == 2 || position.calculateRankDistance(targetPosition) == 1);
    }

    private boolean isBlackPawnFirstMove(Position targetPosition) {
        return position.isSameFile(targetPosition) &&
                (position.calculateRankDistance(targetPosition) == -2 || position.calculateRankDistance(targetPosition) == -1);
    }

    private boolean isBlackPawnMove(Position targetPosition) {
        return position.isSameFile(targetPosition) && position.calculateRankDistance(targetPosition) == -1;
    }

    private boolean isBlackPawnAttack(Position targetPosition) {
        return Math.abs(position.calculateFileDistance(targetPosition)) == 1 && position.calculateRankDistance(targetPosition) == -1;
    }

    @Override
    public String pieceName() {
        return teamStrategy.pawnName();
    }
}
