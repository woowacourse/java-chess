package domain.chessGame;

import domain.piece.Piece;
import domain.piece.PieceName;
import domain.position.Direction;
import domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ChessBoard {

    private final Map<Position, Piece> chessBoard;

    public ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = new HashMap<>(chessBoard);
    }

    public void movePiece(Position startPosition, Position endPosition) {
        validateExistPieceInStartPosition(startPosition);

        if (isPieceMovable(startPosition, endPosition)) {
            chessBoard.put(endPosition, chessBoard.get(startPosition));
            chessBoard.remove(startPosition);
            return;
        }
        throw new IllegalArgumentException("[ERROR] 선택한 말은 목표 좌표로 이동이 불가능합니다.");
    }

    private void validateExistPieceInStartPosition(Position startPosition) {
        if (!chessBoard.containsKey(startPosition)) {
            throw new IllegalArgumentException("[ERROR] 출발 좌표 위치에 말이 존재하지 않습니다.");
        }
    }

    private boolean isPieceMovable(Position startPosition, Position endPosition) {
        List<Position> path = startPosition.getPathTo(endPosition);
        Piece startPiece = chessBoard.get(startPosition);
        if (startPiece.getPieceType() == PieceName.PAWN) {
            considerPawnCase(startPosition, endPosition);
        }
        return startPiece.isMovablePath(startPosition, path) &&
                isPassablePath(path) &&
                isMovableEndPosition(endPosition, startPiece);
    }

    private void considerPawnCase(Position startPosition, Position endPosition) {
        List<Position> path = startPosition.getPathTo(endPosition);
        Piece startPiece = chessBoard.get(startPosition);

        if (startPiece.isMovablePath(startPosition, path)) {
            checkPassablePathToForward(startPosition, path);
            checkMovableToDiagonal(startPosition, endPosition);
        }
    }

    private void checkPassablePathToForward(Position startPosition, List<Position> path) {
        if (path.contains(startPosition.moveUp()) || path.contains(startPosition.moveDown())) {
            path.stream()
                    .filter(chessBoard::containsKey)
                    .forEach(position -> {
                        throw new IllegalArgumentException("[ERROR] 폰은 직선 상 이동 경로에 말이 있으면 이동이 불가능합니다.");
                    });
        }
    }

    private void checkMovableToDiagonal(Position startPosition, Position endPosition) {
        Piece startPiece = chessBoard.get(startPosition);
        if (Direction.of(startPosition, endPosition) == Direction.DIAGONAL &&
                (!chessBoard.containsKey(endPosition) || isSameColorPiece(startPiece, chessBoard.get(endPosition)))) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각선 이동 경로에 말이 없거나, 같은 색 말이 있으면 이동이 불가능합니다.");
        }
    }

    private boolean isSameColorPiece(Piece startPiece, Piece endPiece) {
        if (startPiece.isBlack() == endPiece.isBlack()) {
            throw new IllegalArgumentException("[ERROR] 목표 좌표에 같은 색 말이 있으면 이동이 불가능합니다.");
        }
        return false;
    }

    private boolean isPassablePath(List<Position> path) {
        for (Position position : path.subList(0, path.size() - 1)) {
            if (chessBoard.containsKey(position)) {
                throw new IllegalArgumentException("[ERROR] 진행 경로 상에 다른 말이 존재합니다.");
            }
        }
        return true;
    }

    private boolean isMovableEndPosition(Position endPosition, Piece startPiece) {
        if (chessBoard.containsKey(endPosition)) {
            return !isSameColorPiece(startPiece, chessBoard.get(endPosition));
        }
        return true;
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }
}
