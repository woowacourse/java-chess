package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.king.King;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public Piece pickPiece(final Position position) {
        return this.board.get(position);
    }

    public Piece pickPiece(final Horizontal horizontal, final Vertical vertical) {
        return pickPiece(Position.of(horizontal, vertical));
    }

    public Path movablePath(final Position source) {
        final Piece sourcePiece = pickPiece(source);
        final List<Path> paths = sourcePiece.movablePath(source);
        return Path.filterPaths(paths, source, this);
    }

    public void move(final Position source, final Position target) {
        validateMove(source, target);
        movePiece(source, target);
    }

    private void validateMove(final Position source, final Position target) {
        if (isUnmovableMove(source, target)) {
            throw new IllegalArgumentException("유효하지 않은 이동입니다.");
        }
    }

    private boolean isUnmovableMove(final Position source, final Position target) {
        return !movablePath(source).contains(target);
    }

    private void movePiece(final Position source, final Position target) {
        putPiece(source, target);
        putEmpty(source);
    }

    private void putPiece(final Position source, final Position target) {
        board.put(target, board.get(source));
    }

    private void putEmpty(final Position position) {
        board.put(position, EmptyPiece.getInstance());
    }

    public boolean isKingAlive(final Owner owner) {
        return board.containsValue(King.getInstanceOf(owner));
    }

    public boolean isPositionSameOwner(final Position position, final Owner owner) {
        return pickPiece(position).isSameOwner(owner);
    }

    public List<Piece> pieces() {
        return new ArrayList<>(board.values());
    }

    public Map<Position, Piece> getBoard() {
        return new HashMap<>(board);
    }

    public void resetBoard() {
        BoardInitializer.resetBoard(board);
    }
}
