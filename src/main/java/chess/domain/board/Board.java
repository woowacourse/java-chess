package chess.domain.board;

import chess.domain.piece.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.util.NullValidator.validateNull;

public class Board {
    private final Map<Position, Piece> board = createBoard();

    private Map<Position, Piece> createBoard() {
        Map<Position, Piece> cells = createEmptyCells();
        setBlackPieces(cells);
        setWhitePieces(cells);
        return cells;
    }

    private Map<Position, Piece> createEmptyCells() {
        Map<Position, Piece> cells = new HashMap<>();
        for (Xpoint xPoint : Xpoint.values()) {
            for (Ypoint yPoint : Ypoint.values()) {
                EmptyPiece emptyPiece = new EmptyPiece(PieceColor.NONE, new Position(xPoint, yPoint));
                cells.put(new Position(xPoint, yPoint), emptyPiece);
            }
        }
        return cells;
    }

    private void setBlackPieces(Map<Position, Piece> cells) {
        cells.put(new Position(1, 8), new Rook(PieceColor.BLACK, new Position(1, 8)));
        cells.put(new Position(2, 8), new Knight(PieceColor.BLACK, new Position(2, 8)));
        cells.put(new Position(3, 8), new Bishop(PieceColor.BLACK, new Position(3, 8)));
        cells.put(new Position(4, 8), new Queen(PieceColor.BLACK, new Position(4, 8)));
        cells.put(new Position(5, 8), new King(PieceColor.BLACK, new Position(5, 8)));
        cells.put(new Position(6, 8), new Bishop(PieceColor.BLACK, new Position(6, 8)));
        cells.put(new Position(7, 8), new Knight(PieceColor.BLACK, new Position(7, 8)));
        cells.put(new Position(8, 8), new Rook(PieceColor.BLACK, new Position(8, 8)));

        for (int xPoint = 1; xPoint <= 8; xPoint++) {
            cells.put(new Position(xPoint, 7), new Pawn(PieceColor.BLACK, new Position(xPoint, 7)));
        }
    }

    private void setWhitePieces(Map<Position, Piece> cells) {
        for (int xPoint = 1; xPoint <= 8; xPoint++) {
            cells.put(new Position(xPoint, 2), new Pawn(PieceColor.WHITE, new Position(xPoint, 2)));
        }
        cells.put(new Position(1, 1), new Rook(PieceColor.WHITE, new Position(1, 1)));
        cells.put(new Position(2, 1), new Knight(PieceColor.WHITE, new Position(2, 1)));
        cells.put(new Position(3, 1), new Bishop(PieceColor.WHITE, new Position(3, 1)));
        cells.put(new Position(4, 1), new Queen(PieceColor.WHITE, new Position(4, 1)));
        cells.put(new Position(5, 1), new King(PieceColor.WHITE, new Position(5, 1)));
        cells.put(new Position(6, 1), new Bishop(PieceColor.WHITE, new Position(6, 1)));
        cells.put(new Position(7, 1), new Knight(PieceColor.WHITE, new Position(7, 1)));
        cells.put(new Position(8, 1), new Rook(PieceColor.WHITE, new Position(8, 1)));
    }

    public Piece findPiece(Position sourcePosition) {
        validateNull(sourcePosition);
        if (board.get(sourcePosition).isNoneColor()) {
            throw new IllegalArgumentException("움직일 수 있는 체스말이 없습니다.");
        }
        return board.get(sourcePosition);
    }

    public void checkPath(Piece piece, Position targetPosition) {
        if (piece instanceof Pawn) {
            checkPawnPath(piece, targetPosition);
            return;
        }
        List<Position> path = piece.getPathTo(targetPosition);
        if (havePieceBeforeTargetPosition(path)) {
            throw new IllegalArgumentException("이동 경로 중에 다른 체스말이 있기 때문에 지정한 위치로 이동할 수 없습니다.");
        }
        if (cannotMoveToTargetPosition(piece, targetPosition)) {
            throw new IllegalArgumentException("지정한 위치에 같은 색의 체스말이 있기 때문에 이동할 수 없습니다.");
        }
    }

    private boolean havePieceBeforeTargetPosition(List<Position> path) {
        path.remove(path.size() - 1);
        return !path.stream()
                .map(board::get)
                .allMatch(Piece::isNoneColor);
    }

    private boolean cannotMoveToTargetPosition(Piece piece, Position targetPosition) {
        Piece targetPiece = board.get(targetPosition);
        if (!targetPiece.isNoneColor()) {
            return piece.isSameColor(targetPiece);
        }
        return false;
    }

    private void checkPawnPath(Piece piece, Position targetPosition) {
        Pawn pawn = (Pawn) piece;
        List<Position> path = pawn.getPathTo(targetPosition);
        if (pawn.getDirection(targetPosition).isSouth() || pawn.getDirection(targetPosition).isNorth() && havePieceIn(path)) {
            throw new IllegalArgumentException("이동 경로 중에 다른 체스말이 있기 때문에 지정한 위치로 이동할 수 없습니다.");
        } else if (cannotMoveToTargetPosition(pawn, targetPosition)) {
            throw new IllegalArgumentException("지정한 위치에 다른 색의 체스말이 없기 때문에 이동할 수 없습니다.");
        }
    }

    private boolean havePieceIn(List<Position> path) {
        return !path.stream()
                .map(board::get)
                .allMatch(Piece::isNoneColor);
    }

    private boolean cannotMoveToTargetPosition(Pawn pawn, Position targetPosition) {
        Piece targetPiece = board.get(targetPosition);
        if (!targetPiece.isNoneColor()) {
            return pawn.isSameColor(targetPiece);
        }
        return true;
    }

    public Map<Position, Piece> get() {
        return Collections.unmodifiableMap(board);
    }
}
