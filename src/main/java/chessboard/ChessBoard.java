package chessboard;

import coordinate.Coordinate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import piece.Piece;

public class ChessBoard {

    private static final Map<Coordinate, Piece> board = ChessBoardInitializer.createInitialBoard();

    public void playTurn(Coordinate coordinate, Coordinate destination, boolean blackTurn) {
        Piece piece = findPiece(coordinate);
        validateTurn(piece, blackTurn);

        List<Integer> direction = piece.getDirection(coordinate, destination, isPieceExistOnDestination(destination));
        Coordinate startPosition = coordinate.copied();

        validateCanMove(coordinate, destination, direction);
        board.remove(startPosition);
        board.put(destination, piece);
    }

    private void validateCanMove(Coordinate coordinate, Coordinate destination, List<Integer> direction) {
        while (!coordinate.equals(destination)) {
            coordinate.moveByDistances(direction.get(0), direction.get(1));

            hasPieceOnPath(coordinate, destination);
        }
    }

    private void hasPieceOnPath(Coordinate coordinate, Coordinate destination) {
        if (coordinate.equals(destination)) {
            return;
        }

        if (board.get(coordinate) != null) {
            throw new IllegalArgumentException("이동 경로에 말이 존재합니다.");
        }
    }

    private void validateTurn(Piece piece, boolean isBlackTurn) {
        if (!piece.isSameColor(isBlackTurn)) {
            throw new IllegalArgumentException("상대의 말을 움직일 수 없습니다.");
        }
    }

    private Piece findPiece(Coordinate destination) {
        return board.get(destination);
    }

    private boolean isPieceExistOnDestination(Coordinate destination) {
        return board.get(destination) != null;
    }

    public Map<Coordinate, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
