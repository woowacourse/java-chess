package chessboard;

import coordinate.Coordinate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import piece.Color;
import piece.Piece;

public class ChessBoard {

    private static final int ROW_DIRECTION = 0;
    private static final int COLUMN_DIRECTION = 1;
    private static final Map<Coordinate, Piece> board = ChessBoardInitializer.createInitialBoard();

    //todo: coordinate -> start ?
    public void playTurn(Coordinate coordinate, Coordinate destination, Color currentTurn) {
        Piece piece = findPiece(coordinate);
        validateTurn(piece, currentTurn);

        List<Integer> direction = piece.getDirection(coordinate, destination,
                isPieceExistOnDestination1(currentTurn, destination));
        Coordinate startPosition = coordinate.copied();

        validateCanMove(coordinate, destination, direction);
        board.remove(startPosition);
        board.put(destination, piece);
    }

    // todo: validateCanMove -> validatePathToDestination ?
    private void validateCanMove(Coordinate coordinate, Coordinate destination, List<Integer> direction) {
        while (!coordinate.equals(destination)) {
            coordinate.moveByDistances(direction.get(ROW_DIRECTION), direction.get(COLUMN_DIRECTION));

            hasPieceOnPath(coordinate, destination);
        }
    }

    // todo: hasPieceOnPath ->  validateCanMove ?
    private void hasPieceOnPath(Coordinate coordinate, Coordinate destination) {
        if (coordinate.equals(destination)) {
            return;
        }

        if (board.get(coordinate) != null) {
            throw new IllegalArgumentException("이동 경로에 말이 존재합니다.");
        }
    }

    private void validateTurn(Piece piece, Color currentTurn) {
        if (!piece.isSameColor(currentTurn)) {
            throw new IllegalArgumentException("상대의 말을 움직일 수 없습니다.");
        }
    }

    private Piece findPiece(Coordinate destination) {
        return board.get(destination);
    }

    // todo : 하나로 추상화 한 뒤 공통으로 사용할 수 있을 듯!
    private boolean isPieceExistOnDestination(Coordinate destination) {
        return board.get(destination) != null;
    }

    private boolean isPieceExistOnDestination1(Color current, Coordinate destination) {
        Piece destinationPiece = board.get(destination);
        return destinationPiece != null && !destinationPiece.isSameColor(current);
    }

    // todo : color 도입 했으니 색상까지 비교해야 할 듯 -> 지금은 폰이 팀킬을 할 수 있는 상황임!(돌려서 테스트해봄)
    /* 예시
    public boolean hasSameColor(Piece other) {
        return isBlack() == other.isBlack();
    }
     */
//    private boolean canAttack(Coordinate destination, Piece current) {
//        Piece pieceOnDestination = board.get(destination);
//        pieceOnDestination != null && current.hasSameColor(pieceOnDestination);
//    }

    public Map<Coordinate, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
