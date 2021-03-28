package chess.domain.piece;

import chess.domain.piece.strategy.KingMoveStrategy;
import chess.domain.piece.strategy.OneCellMoveStrategy;
import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.List;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

public class King extends Piece {
    private static final String SYMBOL = "Kk";
    private static final double SCORE = 0;

    private King(final String piece, final Position position,
                 final Color color, final OneCellMoveStrategy oneCellMoveStrategy) {
        super(piece, position, color, oneCellMoveStrategy);
    }

    public static King from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new King(piece, position, BLACK, new KingMoveStrategy());
        }
        return new King(piece, position, WHITE, new KingMoveStrategy());
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
        return true;
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
