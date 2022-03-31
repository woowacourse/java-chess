package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
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

    public ChessBoard(PiecesGenerator piecesGenerator) {
        this.pieces = piecesGenerator.generate();
        initFirstPositionsOfPawn();
        fillEmptyPieceIfAbsent();
    }

    private void initFirstPositionsOfPawn() {
        firstPositionsOfPawn.addAll(BlackPawn.BLACK_INIT_LOCATIONS);
        firstPositionsOfPawn.addAll(WhitePawn.WHITE_INIT_LOCATIONS);
    }

    private void fillEmptyPieceIfAbsent() {
        for (Column column : Column.values()) {
            fillEmptyPieceInColumn(column);
        }
    }

    private void fillEmptyPieceInColumn(Column column) {
        for (Row row : Row.values()) {
            this.pieces.computeIfAbsent(Position.of(column, row), value -> EmptyPiece.getInstance());
        }
    }

    public void move(GameCommand gameCommand) {
        Position from = gameCommand.getFromPosition();
        Position to = gameCommand.getToPosition();

        Piece piece = selectPiece(from);
        List<Position> finalMovablePositions = getMovablePositions(from, piece);

        checkMovable(to, finalMovablePositions);
        movePiece(from, to, piece);
    }

    private List<Position> getMovablePositions(Position from, Piece piece) {
        Map<Direction, List<Position>> movablePositions = piece.getMovablePositions(from);
        refinePawnMovablePositions(from, piece, movablePositions);

        List<Position> finalMovablePositions = generateMovablePositionsExceptObstacles(from, piece,
                movablePositions);
        return finalMovablePositions;
    }

    private void refinePawnMovablePositions(Position from, Piece piece,
                                            Map<Direction, List<Position>> movablePositions) {
        if (!isFirstMovePawn(from) && piece.isSamePieceName(PieceName.PAWN)) {
            removeFirstMovablePositionForPawn(piece, movablePositions);
        }
    }

    public boolean isFirstMovePawn(Position position) {
        return firstPositionsOfPawn.contains(position);
    }

    private void removeFirstMovablePositionForPawn(Piece piece, Map<Direction, List<Position>> movablePositions) {
        List<Position> positions = movablePositions.get(Direction.NORTH);
        if (piece.isBlack()) {
            positions = movablePositions.get(Direction.SOUTH);
        }

        if (positions.size() == 2) {
            positions.remove(FIRST_MOVE_POSITION_INDEX_FOR_PAWN);
        }
    }

    private void checkMovable(Position to, List<Position> finalMovablePositions) {
        if (!finalMovablePositions.contains(to)) {
            throw new IllegalArgumentException("해당 말은 입력한 위치로 이동할 수 없습니다.");
        }
    }

    private void movePiece(Position from, Position to, Piece piece) {
        pieces.put(to, piece);
        pieces.put(from, EmptyPiece.getInstance());
        if (piece.isSamePieceName(PieceName.PAWN)) {
            firstPositionsOfPawn.remove(from);
        }
    }

    public List<Position> generateMovablePositionsExceptObstacles(Position nowPosition, Piece piece,
                                                                  Map<Direction, List<Position>> movablePositions) {
        List<Position> result = new ArrayList<>();
        for (Direction direction : movablePositions.keySet()) {
            List<Position> positions = movablePositions.get(direction);
            addMovablePositionsWithBlock(piece, result, positions);
        }
        addDiagonalMoveForPawn(nowPosition, piece, result);
        return Collections.unmodifiableList(result);
    }

    private void addMovablePositionsWithBlock(Piece piece, List<Position> result, List<Position> positions) {
        if (positions.size() != 0) {
            int removeIndex = getRemoveIndex(piece, positions);
            result.addAll(positions.subList(0, removeIndex));
        }
    }

    private int getRemoveIndex(Piece nowPiece, List<Position> positions) {
        int removeIndex = 0;
        while (removeIndex < positions.size() - 1
                && selectPiece(positions.get(removeIndex)).isEmpty()) {
            removeIndex++;
        }

        Piece target = selectPiece(positions.get(removeIndex));
        if (target.isSameColor(nowPiece) || (nowPiece.isSamePieceName(PieceName.PAWN) && !target.isEmpty())) {
            return removeIndex;
        }
        return removeIndex + FIRST_MOVE_POSITION_INDEX_FOR_PAWN;
    }

    private void addDiagonalMoveForPawn(Position nowPosition, Piece piece, List<Position> result) {
        if (piece.isSamePieceName(PieceName.PAWN)) {
            Direction direction = getPawnDirection(piece);
            List<Direction> diagonalDirections = direction.getDiagonal();
            List<Position> targetPositions = diagonalDirections.stream()
                    .map(nowPosition::toDirection)
                    .collect(Collectors.toList());

            addPositionsIfEnemy(piece, result, targetPositions);
        }
    }

    private Direction getPawnDirection(Piece piece) {
        if (!piece.isSamePieceName(PieceName.PAWN)) {
            throw new IllegalStateException("폰만 해당 메서드를 사용 가능합니다.");
        }
        if (piece.isWhite()) {
            return Direction.NORTH;
        }
        return Direction.SOUTH;
    }

    private void addPositionsIfEnemy(Piece piece, List<Position> result, List<Position> targetPositions) {
        for (Position position : targetPositions) {
            addPositionIfEnemy(piece, result, position);
        }
    }

    private void addPositionIfEnemy(Piece piece, List<Position> result, Position position) {
        Piece targetPiece = selectPiece(position);
        if (!targetPiece.isSameColor(piece) && !targetPiece.isEmpty()) {
            result.add(position);
        }
    }

    public Piece selectPiece(Position position) {
        return pieces.get(position);
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    public List<Pieces> getPiecesOnColumns(Color color) {
        List<Pieces> result = new ArrayList<>();
        for (Column column : Column.values()) {
            result.add(getPiecesOnColumn(column, color));
        }
        return result;
    }

    public Pieces getPiecesOnColumn(Column column, Color color) {
        List<Piece> result = new ArrayList<>();
        for (Row row : Row.values()) {
            result.add(pieces.get(Position.of(column, row)));
        }
        List<Piece> value = result.stream()
                .filter(piece -> piece.isSameColor(color))
                .collect(Collectors.toList());
        return new Pieces(value);
    }

    public boolean isEnd() {
        long kingCount = pieces.values().stream()
                .filter(p -> p.isSamePieceName(PieceName.KING))
                .count();
        return kingCount != KING_COUNTS;
    }
}
