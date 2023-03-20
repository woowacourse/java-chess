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
        Piece sourcePiece = boards.get(sourcePosition);
        validateMove(sourcePosition, targetPosition, sourcePiece);
        boards.put(targetPosition, sourcePiece.move());
        boards.put(sourcePosition, Empty.create());
    }

    private void validateMove(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        validateEmptyPiece(sourcePiece);
        validatePieceMove(sourcePosition, targetPosition, sourcePiece);
        validatePath(sourcePosition, targetPosition);
    }

    private void validateEmptyPiece(Piece sourcePiece) {
        if (sourcePiece.isEmpty()) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }
    }

    private void validatePieceMove(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        if (!sourcePiece.canMove(sourcePosition, targetPosition, boards.get(targetPosition).getColor())) {
            throw new IllegalArgumentException("잘못된 위치를 지정하셨습니다.");
        }
    }

    private void validatePath(Position sourcePosition, Position targetPosition) {
        if (isBlockBetween(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("경로에 다른 말이 있습니다.");
        }
    }

    private boolean isBlockBetween(Position sourcePosition, Position targetPosition) {
        return sourcePosition.findPath(targetPosition).stream()
                .map(boards::get)
                .noneMatch(Piece::isEmpty);
    }

    public Map<Position, Piece> getBoards() {
        return boards;
    }
}
