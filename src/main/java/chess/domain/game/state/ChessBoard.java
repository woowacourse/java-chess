package chess.domain.game.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;

public class ChessBoard {
    private final Map<Position, Piece> board = new HashMap<>();

    public void move(Position source, Position target) {
        Piece sourcePiece = getPiece(source);
        Piece targetPiece = getPiece(target);

        validateExist(source);
        validateMove(source, target);

        changeState(sourcePiece, targetPiece);
        changePosition(source, target);
    }

    public void validateExist(Position position) {
        if (!isExist(getPiece(position))) {
            throw new IllegalArgumentException("해당 위치에 체스말이 존재하지 않습니다.");
        }
    }

    private boolean isExist(Piece piece) {
        return piece != null;
    }

    private void validateMove(Position source, Position target) {
        if (!canMove(source, target)) {
            throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }
    }

    private boolean canMove(Position source, Position target) {
        Piece piece = getPiece(source);
        List<Position> movablePaths = piece.getMovablePaths(source, this);

        return movablePaths.contains(target);
    }

    public boolean canMoveOneStep(Position source, Direction direction) {
        Position target = source.getNext(direction);
        if (source.isBlocked(direction)) {
            return false;
        }

        return !isFilled(target) || canKill(source, target);
    }

    public boolean canKill(Position source, Position target) {
        Piece sourcePiece = getPiece(source);
        Piece targetPiece = getPiece(target);

        return isFilled(target) && !sourcePiece.isSameColor(targetPiece.getColor());
    }

    private void changeState(Piece sourcePiece, Piece targetPiece) {
        kill(targetPiece);
        sourcePiece.updateState();
    }

    private void kill(Piece piece) {
        if (isExist(piece)) {
            piece.killed();
        }
    }

    private void changePosition(Position source, Position target) {
        board.put(target, getPiece(source));
        board.remove(source);
    }

    public void putPiece(Position position, Piece piece) {
        board.put(position, piece);
    }

    public boolean isFilled(Position target) {
        return board.containsKey(target);
    }

    public Piece getPiece(Position target) {
        return board.get(target);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
