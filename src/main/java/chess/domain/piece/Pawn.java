package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Pawn extends Piece {
    private static final String SYMBOL = "Pp";
    private static final double SCORE = 1;

    private boolean isFirst = true;

    private Pawn(final String piece, final Position position, final Color color) {
        super(piece, position, color);
    }

    public static Pawn from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Pawn(piece, position, Color.BLACK);
        }
        return new Pawn(piece, position, Color.WHITE);
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
        List<Position> positions = makeRoutes(basePieces, targetPieces);
        checkTarget(target, positions);
        basePieces.changePiecePosition(this, target);
    }

    @Override
    public double score() {
        //todo
        return SCORE;
    }

    private void checkTarget(Target target, List<Position> positions) {
        if (!positions.contains(target.getPosition())) {
            throw new IllegalArgumentException(String.format("이동할 수 없는 위치입니다. 입력 값: %s", target.getPosition()));
        }
    }

    private List<Position> makeRoutes(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        if (isBlack()) {
            return blackPositions(basePieces, targetPieces, positions);
        }
        return whitePositions(basePieces, targetPieces, positions);
    }

    private List<Position> blackPositions(Pieces basePieces, Pieces targetPieces, List<Position> positions) {
        if (isFirst) {
            positions.addAll(makeFirstDownRoutes(basePieces, targetPieces));
            positions.addAll(makeDownRightRoutes(basePieces, targetPieces));
            positions.addAll(makeDownLeftRoutes(basePieces, targetPieces));
            this.isFirst = false;
            return positions;
        }
        positions.addAll(makeAfterFirstDownRoutes(basePieces, targetPieces));
        positions.addAll(makeDownRightRoutes(basePieces, targetPieces));
        positions.addAll(makeDownLeftRoutes(basePieces, targetPieces));
        return positions;
    }

    private List<Position> makeDownRightRoutes(final Pieces basePieces, final Pieces targetPieces) {
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

    private List<Position> makeDownLeftRoutes(final Pieces basePieces, final Pieces targetPieces) {
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

    private List<Position> makeFirstDownRoutes(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue() - 1;
        int file = position.getFile().getValue();
        for (int index = 0; index < 2; index++) {
            Position nextPosition = Position.valueOf(rank - index, file);
            Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
            Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
            if (targetPiece.isPresent()) {
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

    private List<Position> makeAfterFirstDownRoutes(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue() - 1;
        int file = position.getFile().getValue();
        for (int index = 0; index < 1; index++) {
            Position nextPosition = Position.valueOf(rank - index, file);
            Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
            Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
            if (targetPiece.isPresent()) {
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

    private List<Position> whitePositions(Pieces basePieces, Pieces targetPieces, List<Position> positions) {
        if (isFirst) {
            positions.addAll(makeFirstUpRoutes(basePieces, targetPieces));
            positions.addAll(makeUpRightRoutes(basePieces, targetPieces));
            positions.addAll(makeUpLeftRoutes(basePieces, targetPieces));
            this.isFirst = false;
            return positions;
        }
        positions.addAll(makeAfterFirstUpRoutes(basePieces, targetPieces));
        positions.addAll(makeUpRightRoutes(basePieces, targetPieces));
        positions.addAll(makeUpLeftRoutes(basePieces, targetPieces));
        return positions;
    }

    private List<Position> makeFirstUpRoutes(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue() + 1;
        int file = position.getFile().getValue();
        for (int index = 0; index < 2; index++) {
            Position nextPosition = Position.valueOf(rank + index, file);
            Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
            Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
            if (targetPiece.isPresent()) {
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

    private List<Position> makeUpLeftRoutes(final Pieces basePieces, final Pieces targetPieces) {
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

    private List<Position> makeAfterFirstUpRoutes(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue() + 1;
        int file = position.getFile().getValue();
        for (int index = 0; index < 1; index++) {
            Position nextPosition = Position.valueOf(rank + index, file);
            Optional<Piece> basePiece = basePieces.findPiece(nextPosition);
            Optional<Piece> targetPiece = targetPieces.findPiece(nextPosition);
            if (targetPiece.isPresent()) {
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