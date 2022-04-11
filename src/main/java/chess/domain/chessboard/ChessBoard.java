package chess.domain.chessboard;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Symbol;
import chess.domain.piece.generator.PiecesGenerator;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private static final int KING_COUNTS = 2;

    private final Map<Position, Piece> pieces;

    public ChessBoard(final PiecesGenerator piecesGenerator) {
        this.pieces = piecesGenerator.generate();
    }

    public ChessBoard(final Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(final Position from, final Position to) {
        final Piece piece = selectPiece(from);
        validateMovable(from, to);
        validatePawn(from, to);
        checkRoute(from, to);
        validateMyTeam(piece, from, to);
        pieces.put(to, piece);
        pieces.put(from, EmptyPiece.getInstance());
    }

    private void validateMovable(final Position from, final Position to) {
        final Piece piece = selectPiece(from);
        if (!piece.isMovable(from, to)) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
        }
    }

    private void validatePawn(final Position from, final Position to) {
        final Piece piece = selectPiece(from);
        if (piece.isSameSymbol(Symbol.PAWN)) {
            checkDirection(from, to);
        }
    }

    private void checkDirection(final Position from, final Position to) {
        final Direction direction = Direction.getDirection(from, to);
        final List<Direction> directions = Direction.pawnDiagonalDirection();

        if (directions.contains(direction)) {
            checkEmptyInDiagonalPosition(from, direction);
        }
    }

    private void checkEmptyInDiagonalPosition(Position from, Direction direction) {
        Position position = from.toDirection(direction);
        if (selectPiece(position).isEmpty()) {
            throw new IllegalArgumentException("폰은 해당 위치로 이동할 수 없습니다.");
        }
    }

    private void checkRoute(Position from, Position to) {
        Position initialPosition = from;
        Direction direction = Direction.getDirection(from, to);
        while (!initialPosition.equals(to)) {
            initialPosition = initialPosition.toDirection(direction);
            validateRoute(initialPosition, to);
        }
    }

    private void validateRoute(final Position position, final Position toPosition) {
        if (position == toPosition) {
            return;
        }

        final Piece piece = pieces.get(position);
        if (!piece.isEmpty()) {
            throw new IllegalArgumentException("장애물 때문에 이동할 수 없습니다.");
        }
    }

    private void validateMyTeam(final Piece piece, final Position from, final Position to) {
        if (pieces.get(to).isEmpty()) {
            return;
        }
        if (piece.isSameColor(pieces.get(to))) {
            throw new IllegalArgumentException("같은 말은 잡을 수 없습니다.");
        }
        if (piece.isSameSymbol(Symbol.PAWN) &&
                Direction.pawnStraightDirection().contains(Direction.getDirection(from, to))) {
            throw new IllegalArgumentException("폰은 앞으로 움직일때는 빈칸으로 움직일 수 있습니다.");
        }
    }

    public Piece selectPiece(final Position position) {
        return pieces.get(position);
    }

    public boolean isEnd() {
        return getKingCount() != KING_COUNTS;
    }

    private long getKingCount() {
        final long kingCount = pieces.values().stream()
                .filter(piece -> piece.isSameSymbol(Symbol.KING))
                .count();
        return kingCount;
    }

    public Color getWinner() {
        return pieces.values().stream()
                .filter(piece -> piece.isSameSymbol(Symbol.KING))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("킹이 보드에 존재하지 않습니다."))
                .getColor();
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    public Map<String, Piece> toMap() {
        return pieces.entrySet()
                .stream()
                .collect(Collectors.toMap(m -> m.getKey().toString(), Map.Entry::getValue));
    }
}
