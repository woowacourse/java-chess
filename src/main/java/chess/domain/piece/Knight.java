package chess.domain.piece;

import chess.constant.SquareType;
import chess.domain.board.position.Position;

public class Knight extends Piece {

    private static final String EMBLEM = "N";
    private static final double SCORE = 2.5f;

    public Knight(PieceTeam pieceTeam) {
        super(pieceTeam);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public boolean isMovable(Position source, Position target, SquareType squareType) {
        return source.rankDistance(target) == 2 && source.fileDistance(target) == 1 ||
            source.rankDistance(target) == 1 && source.fileDistance(target) == 2;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
