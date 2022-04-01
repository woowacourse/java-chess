package chess.domain.piece;

import chess.constant.TargetType;
import chess.domain.board.position.Position;

public class Pawn extends Piece {

    private static final String EMBLEM = "P";
    private static final double SCORE = 1;

    public Pawn(PieceTeam pieceTeam) {
        super(pieceTeam);
    }

    @Override
    public boolean isMovable(Position from, Position to, TargetType targetType) {
        if (targetType.isEnemy()) {
            return from.isDiagonal(to) &&
                    to.rankDisplacement(from) == pieceTeam.direction() &&
                    to.fileDistance(from) == 1;
        }

        if (isPawnAtStartLine(from)) {
            return isForward(from, to) && from.rankDistance(to) <= 2;
        }

        return isForward(from, to) && from.rankDistance(to) <= 1;
    }

    private boolean isPawnAtStartLine(Position from) {
        return from.isRankOf(pieceTeam.firstRank());
    }

    private boolean isForward(Position from, Position to) {
        return pieceTeam.direction() * to.rankDisplacement(from)  > 0 &&
                from.isSameFile(to);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String toString() {
        return "Pawn{}";
    }
}
