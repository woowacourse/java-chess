package chess.domain.piece;

import chess.domain.piece.direction.*;
import chess.domain.position.File;
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

    private Pawn(final String piece, final Color color, final MoveStrategies moveStrategies, final Position position) {
        super(piece, color, moveStrategies, position);
    }

    public static Pawn from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Pawn(piece, Color.BLACK, new MoveStrategies(new North(), new Northeast(), new Northwest()), position);
        }
        return new Pawn(piece, Color.WHITE, new MoveStrategies(new South(), new Southeast(), new Southwest()), position);
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

    private void checkTarget(final Target target, final List<Position> positions) {
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

    private List<Position> blackPositions(final Pieces basePieces, final Pieces targetPieces, final List<Position> positions) {
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

    private List<Position> whitePositions(final Pieces basePieces, final Pieces targetPieces, final List<Position> positions) {
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