package chess.domain.piece;

import chess.domain.piece.direction.MoveStrategies;
import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Queen extends Piece {
    private static final String SYMBOL = "Qq";
    private static final double SCORE = 9;

    private boolean isFirst = true;

    private Queen(final String piece, final Color color, final Position position) {
        super(piece, color, MoveStrategies.everyMoveStrategies(), position);
    }

    public static Queen from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Queen(piece, Color.BLACK, position);
        }
        return new Queen(piece, Color.WHITE, position);
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
        List<Position> positions = possiblePositions(basePieces, targetPieces);
        checkTarget(target, positions);
        basePieces.changePiecePosition(this, target);
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
    public boolean isKnight() {
        return false;
    }

    @Override
    public boolean isFirstMove() {
        return isFirst;
    }

    private void checkTarget(final Target target, final List<Position> positions) {
        if (!positions.contains(target.getPosition())) {
            throw new IllegalArgumentException(String.format("이동할 수 없는 위치입니다. 입력 값: %s", target.getPosition()));
        }
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
