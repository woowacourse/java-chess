package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> boards;

    public Board(Map<Position, Piece> boards) {
        this.boards = boards;
    }

    public Piece findPiece(Position position) {
        if (boards.containsKey(position)) {
            return boards.get(position);
        }
        throw new IllegalArgumentException("잘못된 위치를 입력했습니다");
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        validateMove(sourcePosition, targetPosition);
        Piece sourcePiece = boards.get(sourcePosition);
        Piece movedPiece = sourcePiece.move();
        boards.put(targetPosition, movedPiece);
        boards.put(sourcePosition, Empty.create());
    }

    private void validateMove(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = findPiece(sourcePosition);
        validateEmpty(sourcePiece);
        validateCanMove(sourcePosition, targetPosition, sourcePiece);
        validatePath(sourcePosition, targetPosition);
    }

    private void validatePath(Position sourcePosition, Position targetPosition) {
        if (!isEmptyPosition(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("경로가 없습니다.");
        }
    }

    private boolean isEmptyPosition(Position sourcePosition, Position targetPosition) {
        return sourcePosition.findPath(targetPosition).stream()
                .map(boards::get)
                .allMatch(Piece::isEmpty);
    }

    private void validateCanMove(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        if (!sourcePiece.canMove(sourcePosition, targetPosition, boards.get(targetPosition).getColor())) {
            throw new IllegalArgumentException("잘못된 위치를 지정하셨습니다.");
        }
    }

    private void validateEmpty(Piece sourcePiece) {
        if (sourcePiece.isEmpty()) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }
    }

    public Map<Position, Piece> getBoards() {
        return boards;
    }
}
