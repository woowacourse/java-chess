package chess.domain.piece;

import chess.domain.piece.strategy.KnightMoveStrategy;
import chess.domain.piece.strategy.OneCellMoveStrategy;
import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.List;

public class Knight extends Piece {
    private static final String SYMBOL = "Nn";
    private static final double SCORE = 2.5;

    private Knight(final String piece, final Position position,
                   final Color color, final OneCellMoveStrategy oneCellMoveStrategy) {
        super(piece, position, color, oneCellMoveStrategy);
    }

    public static Knight from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Knight(piece, position, Color.BLACK, new KnightMoveStrategy());
        }
        return new Knight(piece, position, Color.WHITE, new KnightMoveStrategy());
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
        List<Position> positions = oneCellMoveStrategy.makeRoutes(basePieces, targetPieces, getPosition());
        oneCellMoveStrategy.checkTarget(target, positions);
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
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
