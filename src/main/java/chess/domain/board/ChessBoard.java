package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ChessBoard {
    // TODO : 테스트 커버리지 높이기
    private final Map<Position, Piece> board;

    public ChessBoard(Map<Position, Piece> board) {
        this.board = board;
    }

    public boolean positionIsEmpty(Position position) {
        return !board.containsKey(position);
    }

    public Piece findPieceByPosition(Position targetPosition) {
        if (positionIsEmpty(targetPosition)) {
            throw new NoSuchElementException("해당 위치에 기물이 존재하지 않습니다.");
        }
        return board.get(targetPosition);
    }

    public boolean pathIsAllEmpty(List<Position> path) {
        return path.stream()
                .allMatch(this::positionIsEmpty);
    }

    public void move(Position start, Position destination) {
        if (canMove(start, destination)) {
            movePiece(start, destination);
            return;
        }
        throw new IllegalArgumentException("잘못된 움직임 명령입니다.");
    }

    private void movePiece(Position start, Position destination) {
        Piece piece = findPieceByPosition(start);
        board.remove(start);
        board.put(destination, piece);
    }

    private boolean canMove(Position start, Position destination) {
        Piece piece = findPieceByPosition(start);

        return piece.canMove(start, destination, this)
                && isMovablePosition(start, destination);
    }

    private boolean isMovablePosition(Position start, Position destination) {
        return positionIsEmpty(destination) || piecesIsOtherTeam(start, destination);
    }

    private boolean piecesIsOtherTeam(Position start, Position destination) {
        Piece startPiece = findPieceByPosition(start);
        Piece desinationPiece = findPieceByPosition(destination);
        return startPiece.isOtherTeam(desinationPiece);
    }
}
