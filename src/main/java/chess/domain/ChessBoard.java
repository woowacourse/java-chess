package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Symbol;
import chess.domain.piece.generator.PiecesGenerator;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.position.Column;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private static final int KING_COUNTS = 2;
    private static final int FIRST_MOVE_POSITION_INDEX_FOR_PAWN = 1;

    private final Map<Position, Piece> pieces;
    private final List<Position> firstPositionsOfPawn = new ArrayList<>();

    public ChessBoard(final PiecesGenerator piecesGenerator) {
        this.pieces = piecesGenerator.generate();
        initFirstPositionsOfPawn();
        fillEmptyPieceIfAbsent();
    }

    private void initFirstPositionsOfPawn() {
        firstPositionsOfPawn.addAll(BlackPawn.BLACK_INIT_LOCATIONS);
        firstPositionsOfPawn.addAll(WhitePawn.WHITE_INIT_LOCATIONS);
    }

    private void fillEmptyPieceIfAbsent() {
        for (final Column column : Column.values()) {
            fillEmptyPieceInColumn(column);
        }
    }

    private void fillEmptyPieceInColumn(final Column column) {
        for (final Row row : Row.values()) {
            this.pieces.computeIfAbsent(Position.of(column, row), value -> EmptyPiece.getInstance());
        }
    }

    public void move(final GameCommand gameCommand) {
        final Position from = gameCommand.getFromPosition();
        final Position to = gameCommand.getToPosition();

        final Piece piece = selectPiece(from);
        final List<Position> finalMovablePositions = getMovablePositions(from, piece);

        checkMovable(to, finalMovablePositions);
        movePiece(from, to, piece);
    }

    private List<Position> getMovablePositions(final Position from, final Piece piece) {
        final Map<Direction, List<Position>> movablePositions = piece.getMovablePositions(from);
        refinePawnMovablePositions(from, piece, movablePositions);

        return generateMovablePositionsExceptObstacles(from, piece, movablePositions);
    }

    private void refinePawnMovablePositions(final Position from, final Piece piece,
                                            final Map<Direction, List<Position>> movablePositions) {
        if (!isFirstMovePawn(from) && piece.isSameSymbol(Symbol.PAWN)) {
            removeFirstMovablePositionForPawn(piece, movablePositions);
        }
    }

    public boolean isFirstMovePawn(final Position position) {
        return firstPositionsOfPawn.contains(position);
    }

    private void removeFirstMovablePositionForPawn(final Piece piece, final Map<Direction, List<Position>> movablePositions) {
        List<Position> positions = movablePositions.get(Direction.NORTH);
        if (piece.isBlack()) {
            positions = movablePositions.get(Direction.SOUTH);
        }

        if (positions.size() == 2) {
            positions.remove(FIRST_MOVE_POSITION_INDEX_FOR_PAWN);
        }
    }

    private void checkMovable(final Position to, final List<Position> finalMovablePositions) {
        if (!finalMovablePositions.contains(to)) {
            throw new IllegalArgumentException("해당 말은 입력한 위치로 이동할 수 없습니다.");
        }
    }

    private void movePiece(final Position from, final Position to, final Piece piece) {
        pieces.put(to, piece);
        pieces.put(from, EmptyPiece.getInstance());
        if (piece.isSameSymbol(Symbol.PAWN)) {
            firstPositionsOfPawn.remove(from);
        }
    }

    public List<Position> generateMovablePositionsExceptObstacles(final Position nowPosition, final Piece piece,
                                                                  final Map<Direction, List<Position>> movablePositions) {
        final List<Position> result = new ArrayList<>();
        for (final Direction direction : movablePositions.keySet()) {
            final List<Position> positions = movablePositions.get(direction);
            addMovablePositionsWithBlock(piece, result, positions);
        }
        addDiagonalMoveForPawn(nowPosition, piece, result);
        return Collections.unmodifiableList(result);
    }

    private void addMovablePositionsWithBlock(final Piece piece, final List<Position> result, final List<Position> positions) {
        if (positions.size() != 0) {
            final int removeIndex = getRemoveIndex(piece, positions);
            result.addAll(positions.subList(0, removeIndex));
        }
    }

    private int getRemoveIndex(final Piece nowPiece, final List<Position> positions) {
        int removeIndex = 0;
        while (removeIndex < positions.size() - 1
                && selectPiece(positions.get(removeIndex)).isEmpty()) {
            removeIndex++;
        }

        Piece target = selectPiece(positions.get(removeIndex));
        if (target.isSameColor(nowPiece) || (nowPiece.isSameSymbol(Symbol.PAWN) && !target.isEmpty())) {
            return removeIndex;
        }
        return removeIndex + FIRST_MOVE_POSITION_INDEX_FOR_PAWN;
    }

    private void addDiagonalMoveForPawn(final Position nowPosition, final Piece piece, final List<Position> result) {
        if (piece.isSameSymbol(Symbol.PAWN)) {
            final Direction direction = getPawnDirection(piece);
            final List<Direction> diagonalDirections = direction.getDiagonal();
            final List<Position> targetPositions = diagonalDirections.stream()
                    .map(nowPosition::toDirection)
                    .collect(Collectors.toList());

            addPositionsIfEnemy(piece, result, targetPositions);
        }
    }

    private Direction getPawnDirection(final Piece piece) {
        if (!piece.isSameSymbol(Symbol.PAWN)) {
            throw new IllegalStateException("폰만 해당 메서드를 사용 가능합니다.");
        }
        if (piece.isWhite()) {
            return Direction.NORTH;
        }
        return Direction.SOUTH;
    }

    private void addPositionsIfEnemy(final Piece piece, final List<Position> result, final List<Position> targetPositions) {
        for (final Position position : targetPositions) {
            addPositionIfEnemy(piece, result, position);
        }
    }

    private void addPositionIfEnemy(final Piece piece, final List<Position> result, final Position position) {
        final Piece targetPiece = selectPiece(position);
        if (!targetPiece.isSameColor(piece) && !targetPiece.isEmpty()) {
            result.add(position);
        }
    }

    public Piece selectPiece(final Position position) {
        return pieces.get(position);
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    public List<Pieces> getPiecesOnColumns(final Color color) {
        final List<Pieces> result = new ArrayList<>();
        for (final Column column : Column.values()) {
            result.add(getPiecesOnColumn(column, color));
        }
        return result;
    }

    public Pieces getPiecesOnColumn(final Column column, final Color color) {
        final List<Piece> result = new ArrayList<>();
        for (final Row row : Row.values()) {
            result.add(pieces.get(Position.of(column, row)));
        }
        final List<Piece> value = result.stream()
                .filter(piece -> piece.isSameColor(color))
                .collect(Collectors.toList());
        return new Pieces(value);
    }

    public boolean isEnd() {
        long kingCount = pieces.values().stream()
                .filter(p -> p.isSameSymbol(Symbol.KING))
                .count();
        return kingCount != KING_COUNTS;
    }
}
