package chess.domain.board;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.SlidingPiece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ChessBoard {
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

    public void move(Position start, Position destination) {
        if (canMove(start, destination)) {
            Piece piece = findPieceByPosition(start);
            board.remove(start);
            board.put(destination, piece);
            return;
        }
        throw new IllegalArgumentException();
    }

    public boolean canMove(Position start, Position destination) {

        Piece piece = findPieceByPosition(start);

        if (piece instanceof Pawn) {
            if (piece.canMove(start, destination) && positionIsEmpty(destination)) {
                return true;
            }
            if (((Pawn) piece).isKillPassing(start, destination) && !positionIsEmpty(destination) && piece.isOtherTeam(
                    findPieceByPosition(destination))) {
                return true;
            }
        }

        if (piece instanceof SlidingPiece) {
            List<Position> path = ((SlidingPiece) piece).searchPath(start, destination);
            if (piece.canMove(start, destination) && path.isEmpty() && (positionIsEmpty(destination)
                    || piece.isOtherTeam(findPieceByPosition(destination)))) {
                return true;
            }
        }

        if (piece.canMove(start, destination) && (positionIsEmpty(destination) || piece.isOtherTeam(
                findPieceByPosition(destination)))) {
            return true;
        }

        return false;
    }

    private boolean isEmpty(List<Position> path) {
        return path.stream()
                .allMatch(this::positionIsEmpty);
    }
}
