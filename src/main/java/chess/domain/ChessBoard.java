package chess.domain;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
import chess.domain.position.Column;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private final Map<Position, Piece> pieces;
    private final List<Position> firstPositionsOfPawn = new ArrayList<>();

    public ChessBoard(Map<Position, Piece> pieces) {
        this.pieces = new HashMap<>(pieces);
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
            this.pieces.computeIfAbsent(new Position(column, row), value -> EmptyPiece.getInstance());
        }
    }

    public void move(GameCommand gameCommand) {
        Position from = gameCommand.getFromPosition();
        Piece piece = selectPiece(from);
        Map<Direction, List<Position>> movablePositions = piece.getMovablePositions(from);
        if (!isFirstMovePawn(piece, from)) {
            removeSecondMove(piece, movablePositions);
        }
        List<Position> positions = generateMovablePositionsWithBlock(from, piece, movablePositions);
    }

    public boolean isFirstMovePawn(Piece piece, Position position) {
        if (firstPositionsOfPawn.contains(position) && piece.isSamePieceName(PieceName.PAWN)) {
            return true;
        }
        return false;
    }

    private void removeSecondMove(Piece piece, Map<Direction, List<Position>> movablePositions) {
        if (piece.isBlack()) {
            List<Position> positions = movablePositions.get(Direction.SOUTH);
            positions.remove(1);
        }
        if (piece.isWhite()) {
            List<Position> positions = movablePositions.get(Direction.NORTH);
            positions.remove(1);
        }
    }

    public List<Position> generateMovablePositionsWithBlock(Position nowPosition, Piece piece,
                                                            Map<Direction, List<Position>> movablePositions) {
        List<Position> result = new ArrayList<>();
        for (Direction direction : movablePositions.keySet()) {
            List<Position> positions = movablePositions.get(direction);
            int cutIndex = getCutIndex(piece, positions);
            result.addAll(positions.subList(0, cutIndex));
        }
        addDiagonalMoveForPawn(nowPosition, piece, result);
        return Collections.unmodifiableList(result);
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

    public Piece selectPiece(Position position) {
        return pieces.get(position);
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
