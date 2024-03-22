package domain.board;

import domain.piece.Empty;
import domain.piece.Piece;
import domain.position.Position;
import domain.position.Route;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> board;

    public ChessBoard(final Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public void move(final Position source, final Position target) {
        validateEmptyRoute(source, target);
        validateLegalMove(source, target);
        movePiece(source, target);
    }

    private void validateEmptyRoute(final Position source, final Position target) {
        final Route route = Route.create(source, target);
        final List<Position> positions = route.getRoute();
        final boolean isPieceExist = positions.stream().anyMatch(board::containsKey);
        if (isPieceExist) {
            throw new IllegalArgumentException("중간에 말이 있어서 이동할 수 없습니다.");
        }
    }

    private void validateLegalMove(final Position source, final Position target) {
        final Piece resourcePiece = findByPosition(source);
        resourcePiece.validateMovement(source, target, findByPosition(target));
    }

    private void movePiece(final Position source, final Position target) {
        final Piece piece = findByPosition(source);
        board.remove(source);
        board.put(target, piece);
    }

    private Piece findByPosition(final Position position) {
        return board.getOrDefault(position, Empty.getInstance());
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
