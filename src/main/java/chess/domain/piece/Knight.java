package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Knight extends Piece {
    private static final String SYMBOL = "Nn";
    private static final double SCORE = 2.5;

    private Knight(final String piece, final Position position, final Color color) {
        super(piece, position, color);
    }

    public static Knight from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Knight(piece, position, Color.BLACK);
        }
        return new Knight(piece, position, Color.WHITE);
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
        List<Position> positions = makeRoutes(basePieces);
        checkTarget(target, positions);
        basePieces.changePiecePosition(this, target);
    }

    @Override
    public double score(final List<Piece> pieces) {
        return SCORE;
    }

    private void checkTarget(Target target, List<Position> positions) {
        if (!positions.contains(target.getPosition())) {
            throw new IllegalArgumentException(String.format("이동할 수 없는 위치입니다. 입력 값: %s", target.getPosition()));
        }
    }

    private List<Position> makeRoutes(final Pieces pieces) {
        List<Position> positions = new ArrayList<>();
        positions.addAll(makeUpLeftRoutes(pieces));
        positions.addAll(makeUpRightRoutes(pieces));
        positions.addAll(makeDownRightRoutes(pieces));
        positions.addAll(makeDownLeftRoutes(pieces));
        positions.addAll(makeLeftUpRoutes(pieces));
        positions.addAll(makeLeftDownRoutes(pieces));
        positions.addAll(makeRightUpRoutes(pieces));
        positions.addAll(makeRightDownRoutes(pieces));
        return positions;
    }

    private List<Position> makeUpLeftRoutes(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue() + 2;
        int file = position.getFile().getValue() - 1;
        if (rank > 8 || file < 1) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        Optional<Piece> piece = pieces.findPiece(nextPosition);
        if (!piece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!piece.get().isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeUpRightRoutes(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue() + 2;
        int file = position.getFile().getValue() + 1;
        if (rank > 8 || file > 8) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        Optional<Piece> piece = pieces.findPiece(nextPosition);
        if (!piece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!piece.get().isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeDownRightRoutes(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue() - 2;
        int file = position.getFile().getValue() + 1;
        if (rank < 1 || file > 8) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        Optional<Piece> piece = pieces.findPiece(nextPosition);
        if (!piece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!piece.get().isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeDownLeftRoutes(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue() - 2;
        int file = position.getFile().getValue() - 1;
        if (rank < 1 || file < 1) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        Optional<Piece> piece = pieces.findPiece(nextPosition);
        if (!piece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!piece.get().isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeLeftUpRoutes(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue() + 1;
        int file = position.getFile().getValue() - 2;
        if (rank > 8 || file < 1) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        Optional<Piece> piece = pieces.findPiece(nextPosition);
        if (!piece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!piece.get().isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeLeftDownRoutes(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue() - 1;
        int file = position.getFile().getValue() - 2;
        if (rank < 1 || file < 1) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        Optional<Piece> piece = pieces.findPiece(nextPosition);
        if (!piece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!piece.get().isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }


    private List<Position> makeRightUpRoutes(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue() + 1;
        int file = position.getFile().getValue() + 2;
        if (rank > 8 || file > 8) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        Optional<Piece> piece = pieces.findPiece(nextPosition);
        if (!piece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!piece.get().isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeRightDownRoutes(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue() - 1;
        int file = position.getFile().getValue() + 2;
        if (rank < 1 || file > 8) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        Optional<Piece> piece = pieces.findPiece(nextPosition);
        if (!piece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!piece.get().isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
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
