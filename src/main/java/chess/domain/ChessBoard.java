package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
import chess.domain.piece.generator.PiecesGenerator;
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

    private static final int SECOND_MOVE_INDEX = 1;
    private static final int SECOND_MOVE_SIZE = 2;
    private static final int RUNNING_KING_COUNT = 2;

    private final Map<Position, Piece> pieces;
    private final List<Position> firstPositionsOfPawn = new ArrayList<>();

    public ChessBoard(PiecesGenerator piecesGenerator) {
        this.pieces = piecesGenerator.generate();
        initFirstPositionsOfPawn();
        fillEmptyPieceIfAbsent();
    }

    private void initFirstPositionsOfPawn() {
        firstPositionsOfPawn.addAll(Pawn.BLACK_INIT_LOCATIONS);
        firstPositionsOfPawn.addAll(Pawn.WHITE_INIT_LOCATIONS);
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
        Map<Direction, List<Position>> movablePositions = piece.getMovablePositions(from);
        refinePawnMovablePositions(from, piece, movablePositions);
        List<Position> finalMovablePositions = generateMovablePositionsWithBlock(from, piece, movablePositions);
        checkMovable(to, finalMovablePositions);
        movePiece(from, to, piece);
    }

    private void refinePawnMovablePositions(Position from, Piece piece,
                                            Map<Direction, List<Position>> movablePositions) {
        if (!isFirstMovePawn(from) && piece.isSamePieceName(PieceName.PAWN)) {
            removeSecondMove(piece, movablePositions);
        }
    }

    public boolean isFirstMovePawn(Position position) {
        return firstPositionsOfPawn.contains(position);
    }

    private void removeSecondMove(Piece piece, Map<Direction, List<Position>> movablePositions) {
        List<Position> positions = movablePositions.get(Direction.NORTH);
        if (piece.isBlack()) {
            positions = movablePositions.get(Direction.SOUTH);
        }
        if (positions.size() == SECOND_MOVE_SIZE) {
            positions.remove(SECOND_MOVE_INDEX);
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

    public List<Position> generateMovablePositionsWithBlock(Position nowPosition, Piece piece,
                                                            Map<Direction, List<Position>> movablePositions) {
        List<Position> result = new ArrayList<>();
        for (Map.Entry<Direction, List<Position>> entry : movablePositions.entrySet()) {
            List<Position> positions = entry.getValue();
            addMovablePositionsWithBlock(piece, result, positions);
        }
        addDiagonalMoveForPawn(nowPosition, piece, result);
        return Collections.unmodifiableList(result);
    }

    private void addMovablePositionsWithBlock(Piece piece, List<Position> result, List<Position> positions) {
        if (!positions.isEmpty()) {
            int cutIndex = getCutIndex(piece, positions);
            result.addAll(positions.subList(0, cutIndex));
        }
    }

    private int getCutIndex(Piece nowPiece, List<Position> positions) {
        int cutIndex = 0;
        while (cutIndex < positions.size() - 1 && selectPiece(positions.get(cutIndex)).isEmpty()) {
            cutIndex++;
        }
        Piece target = selectPiece(positions.get(cutIndex));
        if (target.isSameColor(nowPiece) || (nowPiece.isSamePieceName(PieceName.PAWN) && !target.isEmpty())) {
            return cutIndex;
        }
        return cutIndex + 1;
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

    public List<Piece> getPiecesOnColumn(Column column, Color color) {
        List<Piece> result = new ArrayList<>();
        for (Row row : Row.values()) {
            result.add(pieces.get(Position.of(column, row)));
        }
        return result.stream()
                .filter(piece -> piece.isSameColor(color))
                .collect(Collectors.toList());
    }

    public List<List<Piece>> getPiecesOnColumns(Color color) {
        List<List<Piece>> result = new ArrayList<>();
        for (Column column : Column.values()) {
            result.add(getPiecesOnColumn(column, color));
        }
        return result;
    }

    public boolean isEnd() {
        long kingCount = pieces.values().stream()
                .filter(p -> p.isSamePieceName(PieceName.KING))
                .count();
        return kingCount != RUNNING_KING_COUNT;
    }

    public Color getColor(Position position) {
        return selectPiece(position).getColor();
    }

    public Piece selectPiece(Position position) {
        return pieces.get(position);
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
