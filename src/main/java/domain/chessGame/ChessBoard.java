package domain.chessGame;

import domain.piece.Piece;
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

        if (!validatePieceMovable(startPosition, endPosition)) {
            throw new IllegalArgumentException("[ERROR] 선택한 말은 목표 좌표로 이동이 불가능합니다.");
        }

        chessBoard.put(endPosition, chessBoard.get(startPosition));
        chessBoard.remove(startPosition);
    }

    private void validateExistPieceInStartPosition(Position startPosition) {
        if (!chessBoard.containsKey(startPosition)) {
            throw new IllegalArgumentException("[ERROR] 출발 좌표 위치에 말이 존재하지 않습니다.");
        }
    }

    private boolean validatePieceMovable(Position startPosition, Position endPosition) {
        List<Position> path = startPosition.getPathTo(endPosition);
        Piece startPiece = chessBoard.get(startPosition);
        // Todo : 폰에 대한 로직을 폰에 넣을 수 있나? 된다 하더라도 반드시 폰에 넣어야 하나?
        if (startPiece.isPawn()) {
            considerPawnCase(startPosition, endPosition);
        }
        return startPiece.isMovablePath(startPosition, path) &&
                validatePassablePath(path) &&
                validateMovableEndPosition(endPosition, startPiece);
    }

    private void considerPawnCase(Position startPosition, Position endPosition) {
        List<Position> path = startPosition.getPathTo(endPosition);
        Piece startPiece = chessBoard.get(startPosition);

        if (startPiece.isMovablePath(startPosition, path)) {
            validatePassablePathToForward(startPosition, path);
            validateMovableToDiagonal(startPosition, endPosition);
        }
    }

    private void validatePassablePathToForward(Position startPosition, List<Position> path) {
        if (path.contains(startPosition.moveUp()) || path.contains(startPosition.moveDown())) {
            //Todo : 폰이 isPassablePath처럼 마지막 도착 위치 전까지 판단하는게 아니라 마지막 위치까지 포함해서 계산하고 있어서 묶기 힘들다.
            path.forEach(position -> {
                if(chessBoard.containsKey(position)) {
                    throw new IllegalArgumentException("[ERROR] 폰은 직선 상 이동 경로에 말이 있으면 이동이 불가능합니다.");
                }
            });
        }
    }

    private void validateMovableToDiagonal(Position startPosition, Position endPosition) {
        Piece startPiece = chessBoard.get(startPosition);
        if (Direction.of(startPosition, endPosition) == Direction.DIAGONAL &&
                (!chessBoard.containsKey(endPosition) || isSameColorPiece(startPiece, chessBoard.get(endPosition)))) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각선 이동 경로에 말이 없거나, 같은 색 말이 있으면 이동이 불가능합니다.");
        }
    }

    private boolean validatePassablePath(List<Position> path) {
        for (Position position : path.subList(0, path.size() - 1)) {
            if (chessBoard.containsKey(position)) {
                throw new IllegalArgumentException("[ERROR] 진행 경로 상에 다른 말이 존재합니다.");
            }
        }
        return true;
    }

    private boolean validateMovableEndPosition(Position endPosition, Piece startPiece) {
        if (chessBoard.containsKey(endPosition)) {
            return !isSameColorPiece(startPiece, chessBoard.get(endPosition));
        }
        return true;
    }

    //Todo: 체스판에서 considerPawnCase를 분리하면 1곳에서만 쓰이는데 분리하지 않아도 될 것 같다고 하심
    private boolean isSameColorPiece(Piece startPiece, Piece endPiece) {
        return startPiece.isBlack() == endPiece.isBlack();
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }
}
