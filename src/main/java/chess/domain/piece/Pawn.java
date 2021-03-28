package chess.domain.piece;

import chess.domain.piece.strategy.OneCellMoveStrategy;
import chess.domain.piece.strategy.PawnMoveStrategy;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.List;

public class Pawn extends Piece {
    private static final String SYMBOL = "Pp";
    private static final double SCORE = 1;

    private Pawn(final String piece, final Position position,
                 final Color color, final OneCellMoveStrategy oneCellMoveStrategy) {
        super(piece, position, color, oneCellMoveStrategy);
    }

    public static Pawn from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Pawn(piece, position, Color.BLACK, new PawnMoveStrategy());
        }
        return new Pawn(piece, position, Color.WHITE, new PawnMoveStrategy());
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
    public void move(Target target, final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = oneCellMoveStrategy.makeRoutes(basePieces, targetPieces, getPosition());
        oneCellMoveStrategy.checkTarget(target, positions);
        this.changePosition(target.getPosition());
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
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}