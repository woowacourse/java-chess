package chess.domain.board;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public Piece findByPosition(Position position) {
        return squares.get(position);
    }

    public void move(Position from, Position to) {
        Piece sourcePiece = squares.get(from);
        Piece targetPiece = squares.get(to);

        // (테스트 필요)이동할 위치가 같은 색깔이 아님을 검증한다.
        validateNotSameColor(sourcePiece, targetPiece);

        if (!sourcePiece.canMove(this, from, to) && !targetPiece.isEmpty()) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }

        // 장애물이 없음을 검증한다
        validateNotHurdle(from, to);

        squares.replace(to, sourcePiece);
        squares.replace(from, new EmptyPiece());
    }

    private void validateNotSameColor(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameColor(targetPiece)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNotHurdle(Position from, Position to) {
        List<Position> route = findByPosition(from).getRoute(from, to);

        for (Position position : route) {
            if (!findByPosition(position).isEmpty()) {
                throw new IllegalArgumentException("이동할 수 없다.");
            }
        }
    }

//    private boolean isOppositeColor(Piece sourcePiece, Piece targetPiece) {
//        return !targetPiece.isEmpty() && !sourcePiece.isSameColor(targetPiece);
//    }
}
