package chess.domain.piece;

import chess.domain.piece.direction.*;
import chess.domain.position.File;
import chess.domain.position.Position;

import java.util.List;

public class Pawn extends Piece {
    private static final String SYMBOL = "Pp";
    private static final double SCORE = 1;

    private Pawn(final String piece, final Color color, final MoveStrategies moveStrategies, final Position position) {
        super(piece, color, moveStrategies, position);
    }

    public static Pawn from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Pawn(piece, Color.BLACK, new MoveStrategies(new South(), new Southeast(), new Southwest()), position);
        }
        return new Pawn(piece, Color.WHITE, new MoveStrategies(new North(), new Northeast(), new Northwest()), position);
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
    public double score(final List<Piece> pieces) {
        File currentFile = this.getPosition().getFile();
        boolean isSameFile = pieces.stream()
                .filter(Piece::isPawn)
                .filter(piece -> !this.equals(piece))
                .anyMatch(piece -> currentFile.isSameFile(piece.getFile()));
        if (isSameFile) {
            return SCORE / 2;
        }
        return SCORE;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKnight() {
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