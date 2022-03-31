package chess.domain.piece;

import static chess.domain.piece.PieceTeam.WHITE;

import chess.constant.SquareType;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;

public class Pawn extends Piece {

    private static final String EMBLEM = "P";
    private static final double SCORE = 1;

    public Pawn(PieceTeam pieceTeam) {
        super(pieceTeam);
    }

    @Override
    public boolean isMovable(Position source, Position target, SquareType squareType) {
        if (squareType == SquareType.ENEMY) {
            return source.isDiagonal(target) &&
                    source.rankDisplacement(target) == 1 * pieceTeam.direction() &&
                    source.fileDistance(target) == 1;
        }

        if (pieceTeam == WHITE) {
            if (source.isRankOf(Rank.TWO)) {
                return isForward(source, target, 2);
            }

            return isForward(source, target, 1);
        }

        if (source.isRankOf(Rank.SEVEN)) {
            return isForward(target, source, 2);
        }

        return isForward(target, source, 1);
    }

    private boolean isForward(Position source, Position target, int amount) {
        return source.rankDisplacement(target) > 0 && source.rankDisplacement(target) <= amount && source.isSameFile(
                target);
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
