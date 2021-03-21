package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Queen extends Piece {
    private static final String SYMBOL = "Qq";

    private Queen(final String piece, final Position position, final Color color) {
        super(piece, position, color);
    }

    public static Queen from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Queen(piece, position, Color.BLACK);
        }
        return new Queen(piece, position, Color.WHITE);
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

    private void checkTarget(final Target target, final List<Position> positions) {
        if (!positions.contains(target.getPosition())) {
            throw new IllegalArgumentException(String.format("이동할 수 없는 위치입니다. 입력 값: %s", target.getPosition()));
        }
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

    private List<Position> makeRightRoutes2(final Pieces pieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int index = file; index < 8; index++) {
            Position nextPosition = Position.valueOf(rank, index + 1);
            Optional<Piece> piece = pieces.findPiece(nextPosition);
            if (!piece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            if (!piece.get().isSameColor(this)) {
                positions.add(nextPosition);
                break;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeLeftRoutes2(final Pieces pieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int index = file; index > 1; index--) {
            Position nextPosition = Position.valueOf(rank, index - 1);
            Optional<Piece> piece = pieces.findPiece(nextPosition);
            if (!piece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            if (!piece.get().isSameColor(this)) {
                positions.add(nextPosition);
                break;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeDownRoutes2(final Pieces pieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int index = rank; index > 1; index--) {
            Position nextPosition = Position.valueOf(index - 1, file);
            Optional<Piece> piece = pieces.findPiece(nextPosition);
            if (!piece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            if (!piece.get().isSameColor(this)) {
                positions.add(nextPosition);
                break;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeUpRoutes2(final Pieces pieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int index = rank; index < 8; index++) {
            Position nextPosition = Position.valueOf(index + 1, file);
            Optional<Piece> piece = pieces.findPiece(nextPosition);
            if (!piece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            if (!piece.get().isSameColor(this)) {
                positions.add(nextPosition);
                break;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeDownRightRoutes2(final Pieces pieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex > 0; rankIndex--) {
            Position nextPosition = Position.valueOf(rankIndex - 1, file++ + 1);
            if (file > 7) {
                break;
            }
            Optional<Piece> piece = pieces.findPiece(nextPosition);
            if (!piece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            if (!piece.get().isSameColor(this)) {
                positions.add(nextPosition);
                break;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeDownLeftRoutes2(final Pieces pieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex > 0; rankIndex--) {
            Position nextPosition = Position.valueOf(rankIndex - 1, file-- - 1);
            if (file < 1) {
                break;
            }
            Optional<Piece> piece = pieces.findPiece(nextPosition);
            if (!piece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            if (!piece.get().isSameColor(this)) {
                positions.add(nextPosition);
                break;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeUpRightRoutes2(final Pieces pieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex < 8; rankIndex++) {
            Position nextPosition = Position.valueOf(rankIndex + 1, file++ + 1);
            if (file > 7) {
                break;
            }
            Optional<Piece> piece = pieces.findPiece(nextPosition);
            if (!piece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            if (!piece.get().isSameColor(this)) {
                positions.add(nextPosition);
                break;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeUpLeftRoutes2(final Pieces pieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex < 8; rankIndex++) {
            Position nextPosition = Position.valueOf(rankIndex + 1, file-- - 1);
            if (file < 1) {
                break;
            }
            Optional<Piece> piece = pieces.findPiece(nextPosition);
            if (!piece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            if (!piece.get().isSameColor(this)) {
                positions.add(nextPosition);
                break;
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

    private static void validate(final String piece) {
        if (!SYMBOL.contains(piece)) {
            throw new IllegalArgumentException(String.format("옳지 않은 기물입니다! 입력 값: %s", piece));
        }
        if (piece.length() > 1) {
            throw new IllegalArgumentException(String.format("옳지 않은 기물입니다! 입력 값: %s", piece));
        }
    }
}
