package chess.domain;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Direction, List<Position>> move(GameCommand gameCommand) {
        Position from = gameCommand.getFromPosition();
        Piece piece = selectPiece(from);
        Map<Direction, List<Position>> movablePositions = piece.getMovablePositions(from);
        if (!isFirstMovePawn(from)) {
            removeSecondMove(piece, movablePositions);
        }
        return movablePositions;
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

    public Piece selectPiece(Position position) {
        return pieces.get(position);
    }

    public boolean isFirstMovePawn(Position position) {
        if (firstPositionsOfPawn.contains(position)) {
            return true;
        }
        return false;
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }

//    public Piece selectPiece(Position a1) {
//
//    }
}
