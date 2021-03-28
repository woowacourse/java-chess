package chess.domain.piece;

import chess.domain.piece.strategy.MultipleCellMoveStrategy;
import chess.domain.piece.strategy.RookMoveStrategy;
import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.List;

public class Rook extends Piece {
    private static final String SYMBOL = "Rr";
    private static final double SCORE = 5;
    private static final int RANGE = 8;

    private Rook(final String piece, final Position position,
                 final Color color, final MultipleCellMoveStrategy multipleCellMoveStrategy) {
        super(piece, position, color, multipleCellMoveStrategy);
    }

    public static Rook from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Rook(piece, position, Color.BLACK, new RookMoveStrategy());
        }
        return new Rook(piece, position, Color.WHITE, new RookMoveStrategy());
    }

    private static void validate(final String piece) {
        if (!SYMBOL.contains(piece)) {
            throw new IllegalArgumentException(String.format("옳지 않은 기물입니다! 입력 값: %s", piece));
        }
        if (piece.length() > 1) {
            throw new IllegalArgumentException(String.format("옳지 않은 기물입니다! 입력 값: %s", piece));
        }
    }

    @Override
    public void move(final Target target, final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = multipleCellMoveStrategy.makeLinearRoutes(
                basePieces, targetPieces, getPosition(), RANGE);
        multipleCellMoveStrategy.checkTarget(target, positions);
        this.changePosition(target.getPosition());
    }

    @Override
    public double score(final List<Piece> pieces) {
        return SCORE;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
