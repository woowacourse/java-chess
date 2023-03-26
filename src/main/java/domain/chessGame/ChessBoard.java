package domain.chessGame;

import domain.piece.Color;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.position.Path;
import domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ChessBoard {

    private final Map<Position, Piece> chessBoard;
    private Color turnOfColor;

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = new HashMap<>(chessBoard);
        turnOfColor = Color.WHITE;
    }

    public ChessBoard(Map<Position, Piece> chessBoard, Color turnOfColor) {
        this.chessBoard = new HashMap<>(chessBoard);
        this.turnOfColor = turnOfColor;
    }

    public void movePiece(Position startPosition, Position endPosition) {
        validateBeforeMovePiece(startPosition, endPosition);

        chessBoard.put(endPosition, chessBoard.get(startPosition));
        chessBoard.remove(startPosition);

        turnOfColor = turnOfColor.changeColor();
    }

    private void validateBeforeMovePiece(Position startPosition, Position endPosition) {
        validateExistPieceInStartPosition(startPosition);
        validateCorrectTurnPieceInStartPosition(chessBoard.get(startPosition));

        if (!isPieceMovable(startPosition, endPosition)) {
            throw new IllegalArgumentException("[ERROR] 선택한 말은 목표 좌표로 이동이 불가능합니다.");
        }
    }

    private void validateExistPieceInStartPosition(Position startPosition) {
        if (!chessBoard.containsKey(startPosition)) {
            throw new IllegalArgumentException("[ERROR] 출발 좌표 위치에 말이 존재하지 않습니다.");
        }
    }

    private void validateCorrectTurnPieceInStartPosition(Piece piece) {
        if (turnOfColor == Color.WHITE && piece.isBlack()) {
            throw new IllegalArgumentException("[ERROR] 흰색 진영의 차례입니다.");
        }
        if (turnOfColor == Color.BLACK && piece.isWhite()) {
            throw new IllegalArgumentException("[ERROR] 검은색 진영의 차례입니다.");
        }
    }

    private boolean isPieceMovable(Position startPosition, Position endPosition) {
        Path path = new Path(startPosition, endPosition);
        Piece startPiece = chessBoard.get(startPosition);

        if (startPiece.isPawn() && startPiece.isMovablePath(startPosition, path)) {
            validatePawnMovable(startPosition, endPosition);
        }
        return startPiece.isMovablePath(startPosition, path) &&
                isPassablePath(path) &&
                isMovableEndPosition(endPosition, startPiece);
    }

    private boolean isPassablePath(Path path) {
        List<Position> pathPositionsExcludedEnd = path.subListFirstTo(path.size() - 1);
        pathPositionsExcludedEnd.forEach(this::validateNoPieceAt);
        return true;
    }

    private boolean isMovableEndPosition(Position endPosition, Piece startPiece) {
        Piece endPiece = chessBoard.get(endPosition);
        if (chessBoard.containsKey(endPosition) && (startPiece.isBlack() == endPiece.isBlack())) {
            throw new IllegalArgumentException("[ERROR] 같은 색의 말이 있는 칸으로 이동이 불가능합니다.");
        }
        return true;
    }

    private void validatePawnMovable(Position startPosition, Position endPosition) {
        Path path = new Path(startPosition, endPosition);
        Pawn selectedPawn = (Pawn) chessBoard.get(startPosition);

        if (selectedPawn.isForwardOneStep(startPosition, path.getFirstPosition())) {
            validatePassablePathToForward(path.getPositions());
            return;
        }
        validateMovableToDiagonal(selectedPawn, endPosition);
    }

    private void validatePassablePathToForward(List<Position> pathPositions) {
        pathPositions.forEach(this::validateNoPieceAt);
    }

    private void validateMovableToDiagonal(Pawn selectedPawn, Position endPosition) {
        if (!chessBoard.containsKey(endPosition)) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각선 이동 경로에 상대 말이 없으면 이동이 불가능합니다.");
        }
        isMovableEndPosition(endPosition, selectedPawn);
    }

    private void validateNoPieceAt(Position position) {
        if (chessBoard.containsKey(position)) {
            throw new IllegalArgumentException("[ERROR] 진행 경로 상에 다른 말이 존재합니다.");
        }
    }

    public boolean isGameEnded() {
        return chessBoard.values().stream().filter(Piece::isKing).count() != 2;
    }

    public Map<Position, Piece> getBlackPieces() {
        Map<Position, Piece> blackPieces = new HashMap<>();
        for (Map.Entry<Position, Piece> entry : chessBoard.entrySet()) {
            putOnlyBlackPiece(blackPieces, entry);
        }
        return blackPieces;
    }

    private void putOnlyBlackPiece(Map<Position, Piece> blackPieces, Map.Entry<Position, Piece> entry) {
        if (entry.getValue().isBlack()) {
            blackPieces.put(entry.getKey(), entry.getValue());
        }
    }

    public Map<Position, Piece> getWhitePieces() {
        Map<Position, Piece> whitePieces = new HashMap<>();
        for (Map.Entry<Position, Piece> entry : chessBoard.entrySet()) {
            putOnlyWhitePiece(whitePieces, entry);
        }
        return whitePieces;
    }

    private void putOnlyWhitePiece(Map<Position, Piece> whitePieces, Map.Entry<Position, Piece> entry) {
        if (entry.getValue().isWhite()) {
            whitePieces.put(entry.getKey(), entry.getValue());
        }
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }

    public Color getTurnOfColor() {
        return turnOfColor;
    }
}
