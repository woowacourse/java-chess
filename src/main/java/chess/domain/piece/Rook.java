package chess.domain.piece;

import chess.constant.SquareType;
import chess.domain.board.position.Position;

public class Rook extends Piece {

    private static final String EMBLEM = "R";
    private static final double SCORE = 5;

    public Rook(PieceTeam pieceTeam) {
        super(pieceTeam);
    }

    @Override
    public String getConcreteEmblem() {
        return EMBLEM;
    }

    @Override
    public boolean isMovable(Position source, Position target, SquareType squareType) {
        return source.isCross(target);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
