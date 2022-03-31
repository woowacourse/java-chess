package chess.domain.piece;

import chess.constant.SquareType;
import chess.domain.board.position.Position;

public class Pawn extends Piece {

    private static final String EMBLEM = "P";
    private static final double SCORE = 1;

    public Pawn(PieceTeam pieceTeam) {
        super(pieceTeam);
    }

    @Override
    public boolean isMovable(Position from, Position to, SquareType squareType) {
        if (squareType == SquareType.ENEMY) {
            return from.isDiagonal(to) &&
                    from.rankDisplacement(to) == pieceTeam.direction() &&
                    from.fileDistance(to) == 1;
        }

        if (from.isRankOf(pieceTeam.firstRank())) {
            return isForward(from, to) && from.rankDistance(to) <= 2;
        }

        return isForward(from, to) && from.rankDistance(to) <= 1;
    }

    private boolean isForward(Position source, Position target) {
        return pieceTeam.direction() * source.rankDisplacement(target)  > 0 &&
                source.isSameFile(target);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
