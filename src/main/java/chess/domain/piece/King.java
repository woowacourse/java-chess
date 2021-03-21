package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class King extends Piece {
    private static final String SYMBOL = "Kk";

    private King(final String piece, final Position position, final Color color) {
        super(piece, position, color);
    }

    public static King from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new King(piece, position, Color.BLACK);
        }
        return new King(piece, position, Color.WHITE);
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
    public void move(final Target target, final ChessBoard chessBoard) {
    }

    @Override
    public void move2(final Target target, final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = makeRoutes2(basePieces);
        checkTarget(target, positions);
        basePieces.changePiecePosition(this, target);
    }

    private List<Position> makeRoutes2(final Pieces pieces) {
        List<Position> positions = new ArrayList<>();
        positions.addAll(makeUpRoutes2(pieces));
        positions.addAll(makeDownRoutes2(pieces));
        positions.addAll(makeLeftRoutes2(pieces));
        positions.addAll(makeRightRoutes2(pieces));
        positions.addAll(makeUpLeftRoutes2(pieces));
        positions.addAll(makeUpRightRoutes2(pieces));
        positions.addAll(makeDownLeftRoutes2(pieces));
        positions.addAll(makeDownRightRoutes2(pieces));
        return positions;
    }

    private void checkTarget(Target target, List<Position> positions) {
        if (!positions.contains(target.getPosition())) {
            throw new IllegalArgumentException(String.format("이동할 수 없는 위치입니다. 입력 값: %s", target.getPosition()));
        }
    }

    private List<Position> makeUpRoutes2(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank + 1, file);
        Optional<Piece> piece = pieces.findPiece(nextPosition);
        if (!piece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!piece.get().isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeDownRoutes2(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank - 1, file);
        Optional<Piece> piece = pieces.findPiece(nextPosition);
        if (!piece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!piece.get().isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeLeftRoutes2(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank, file - 1);
        Optional<Piece> piece = pieces.findPiece(nextPosition);
        if (!piece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!piece.get().isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeRightRoutes2(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank, file + 1);
        Optional<Piece> piece = pieces.findPiece(nextPosition);
        if (!piece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!piece.get().isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeUpLeftRoutes2(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank + 1, file - 1);
        Optional<Piece> piece = pieces.findPiece(nextPosition);
        if (!piece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!piece.get().isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeUpRightRoutes2(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank + 1, file + 1);
        Optional<Piece> piece = pieces.findPiece(nextPosition);
        if (!piece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!piece.get().isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeDownLeftRoutes2(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank - 1, file - 1);
        Optional<Piece> piece = pieces.findPiece(nextPosition);
        if (!piece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!piece.get().isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeDownRightRoutes2(final Pieces pieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank - 1, file + 1);
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
