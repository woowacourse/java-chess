package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.movefactory.Direction;
import chess.domain.movefactory.MoveType;

public class Pawn extends Piece {
    private final Position startPosition;

    public Pawn(Position position, TeamStrategy teamStrategy) {
        super(position, teamStrategy);
        startPosition = position;
    }

    public boolean isMovable(MoveType moveType, Piece targetPiece) {
        if (this.teamStrategy instanceof BlackTeam) {
            return blackPawnMovable(moveType, targetPiece);
        }
        return whitePawnMovable(moveType, targetPiece);
    }

    private boolean whitePawnMovable(MoveType moveType, Piece targetPiece) {
        if (targetPiece != null) {
            return moveType.getDirection() == Direction.DOWN_RIGHT || moveType.getDirection() == Direction.DOWN_LEFT && moveType.getCount() == 1;
        }
        if (this.position.isPawnStartLine(this)) {
            System.out.println("start:" + startPosition);
            return moveType.getDirection() == Direction.DOWN && moveType.getCount() <= 2;
        }
        return moveType.getDirection() == Direction.DOWN && moveType.getCount() == 1;
    }

    private boolean blackPawnMovable(MoveType moveType, Piece targetPiece) {
        if (targetPiece != null) {
            return moveType.getDirection() == Direction.UP_RIGHT || moveType.getDirection() == Direction.UP_LEFT && moveType.getCount() == 1;
        }
        if (this.position.isPawnStartLine(this)) {
            System.out.println("start:" + startPosition);
            return moveType.getDirection() == Direction.UP && moveType.getCount() <= 2;
        }
        return moveType.getDirection() == Direction.UP && moveType.getCount() == 1;
    }


    @Override
    public boolean isMovable(MoveType moveType) {
        throw new IllegalArgumentException("지원하지 않는 메소드 입니다");
    }


    @Override
    public String pieceName() {
        return teamStrategy.pawnName();
    }

    public boolean isBlackTeam() {
        return teamStrategy instanceof BlackTeam;
    }
}
