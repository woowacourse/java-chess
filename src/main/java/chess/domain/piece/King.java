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
        List<Position> positions = makeRoutes2(basePieces, targetPieces);
        checkTarget(target, positions);
        basePieces.changePiecePosition(this, target);
    }

    private List<Position> makeRoutes2(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        positions.addAll(makeUpRoutes2(basePieces, targetPieces));
        positions.addAll(makeDownRoutes2(basePieces, targetPieces));
        positions.addAll(makeLeftRoutes2(basePieces, targetPieces));
        positions.addAll(makeRightRoutes2(basePieces, targetPieces));
        positions.addAll(makeUpLeftRoutes2(basePieces, targetPieces));
        positions.addAll(makeUpRightRoutes2(basePieces, targetPieces));
        positions.addAll(makeDownLeftRoutes2(basePieces, targetPieces));
        positions.addAll(makeDownRightRoutes2(basePieces, targetPieces));
        return positions;
    }

    private void checkTarget(final Target target, final List<Position> positions) {
        if (!positions.contains(target.getPosition())) {
            throw new IllegalArgumentException(String.format("이동할 수 없는 위치입니다. 입력 값: %s", target.getPosition()));
        }
    }

    private List<Position> makeUpRoutes2(final Pieces basePieces, final Pieces targetPieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank + 1, file);
        Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
        Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
        if (targetPiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!basePiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeDownRoutes2(final Pieces basePieces, final Pieces targetPieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank - 1, file);
        Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
        Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
        if (targetPiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!basePiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeLeftRoutes2(final Pieces basePieces, final Pieces targetPieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank, file - 1);
        Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
        Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
        if (targetPiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!basePiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeRightRoutes2(final Pieces basePieces, final Pieces targetPieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank, file + 1);
        Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
        Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
        if (targetPiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!basePiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeUpLeftRoutes2(final Pieces basePieces, final Pieces targetPieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank + 1, file - 1);
        Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
        Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
        if (targetPiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!basePiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeUpRightRoutes2(final Pieces basePieces, final Pieces targetPieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank + 1, file + 1);
        Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
        Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
        if (targetPiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!basePiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeDownLeftRoutes2(final Pieces basePieces, final Pieces targetPieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank - 1, file - 1);
        Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
        Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
        if (targetPiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!basePiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeDownRightRoutes2(final Pieces basePieces, final Pieces targetPieces) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank - 1, file + 1);
        Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
        Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
        if (targetPiece.isPresent()) {
            return Collections.singletonList(nextPosition);
        }
        if (!basePiece.isPresent()) {
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
