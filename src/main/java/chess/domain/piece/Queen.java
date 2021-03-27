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
        List<Position> positions = makeRoutes(basePieces, targetPieces);
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
        positions.addAll(makeUpLeftRoutes(basePieces, targetPieces));
        positions.addAll(makeUpRightRoutes(basePieces, targetPieces));
        positions.addAll(makeDownLeftRoutes(basePieces, targetPieces));
        positions.addAll(makeDownRightRoutes(basePieces, targetPieces));
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

    private List<Position> makeDownRightRoutes(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex > 0; rankIndex--) {
            Position nextPosition = Position.valueOf(rankIndex - 1, file++ + 1);
            if (file > 7) {
                break;
            }
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

    private List<Position> makeDownLeftRoutes(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex > 0; rankIndex--) {
            Position nextPosition = Position.valueOf(rankIndex - 1, file-- - 1);
            if (file < 1) {
                break;
            }
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

    private List<Position> makeUpRightRoutes(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex < 8; rankIndex++) {
            Position nextPosition = Position.valueOf(rankIndex + 1, file++ + 1);
            if (file > 7) {
                break;
            }
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

    private List<Position> makeUpLeftRoutes(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex < 8; rankIndex++) {
            Position nextPosition = Position.valueOf(rankIndex + 1, file-- - 1);
            if (file < 1) {
                break;
            }
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
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
