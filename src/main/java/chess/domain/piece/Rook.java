package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Rook extends Piece {
    private static final String SYMBOL = "Rr";
    private static final double SCORE = 5;

    private Rook(final String piece, final Position position, final Color color) {
        super(piece, position, color);
    }

    public static Rook from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Rook(piece, position, Color.BLACK);
        }
        return new Rook(piece, position, Color.WHITE);
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
        List<Position> positions = makeRoutes(basePieces, targetPieces);
        checkTarget(target, positions);
        basePieces.changePiecePosition(this, target);
    }

    @Override
    public double score() {
        return SCORE;
    }

    private void checkTarget(final Target target, final List<Position> positions) {
        if (!positions.contains(target.getPosition())) {
            throw new IllegalArgumentException(String.format("이동할 수 없는 위치입니다. 입력 값: %s", target.getPosition()));
        }
    }

    private List<Position> makeRoutes(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        positions.addAll(makeUpRoutes(basePieces, targetPieces));
        positions.addAll(makeDownRoutes(basePieces, targetPieces));
        positions.addAll(makeLeftRoutes(basePieces, targetPieces));
        positions.addAll(makeRightRoutes(basePieces, targetPieces));
        return positions;
    }

    private List<Position> makeRightRoutes(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int index = file; index < 8; index++) {
            Position nextPosition = Position.valueOf(rank, index + 1);
            Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
            Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
            if (targetPiece.isPresent()) {
                positions.add(nextPosition);
                break;
            }
            if (!basePiece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeLeftRoutes(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int index = file; index > 1; index--) {
            Position nextPosition = Position.valueOf(rank, index - 1);
            Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
            Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
            if (targetPiece.isPresent()) {
                positions.add(nextPosition);
                break;
            }
            if (!basePiece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeDownRoutes(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int index = rank; index > 1; index--) {
            Position nextPosition = Position.valueOf(index - 1, file);
            Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
            Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
            if (targetPiece.isPresent()) {
                positions.add(nextPosition);
                break;
            }
            if (!basePiece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeUpRoutes(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int index = rank; index < 8; index++) {
            Position nextPosition = Position.valueOf(index + 1, file);
            Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
            Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
            if (targetPiece.isPresent()) {
                positions.add(nextPosition);
                break;
            }
            if (!basePiece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            break;
        }
        return positions;
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
