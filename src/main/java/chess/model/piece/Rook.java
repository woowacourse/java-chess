package chess.model.piece;

import chess.vo.MoveType;
import chess.vo.Path;
import chess.vo.PieceColor;

public class Rook extends Piece {

    private static final String EMBLEM = "R";
    private static final double SCORE = 5;

    public Rook(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isMovable(Path path, MoveType moveType) {
        return path.isCross();
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
