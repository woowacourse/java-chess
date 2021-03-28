package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Empty;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.king.King;

import java.util.*;

public class Board {
    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public Piece of(final Position position) {
        return board.get(position);
    }

    public Piece of(final Vertical vertical, final Horizontal horizontal) {
        return of(Position.of(vertical, horizontal));
    }

    private Path ableToPath(final Position source) {
        final Piece sourcePiece = of(source);
        final List<Path> paths = sourcePiece.ableToPath(source);
        return Path.of(paths, source, this);
    }

    public void move(final Position source, final Position target) {
        validateMove(source, target);
        movePiece(source, target);
    }

    private void validateMove(final Position source, final Position target) {
        if (isUnableToMove(source, target)) {
            throw new IllegalArgumentException("유효하지 않은 이동입니다.");
        }
    }

    private boolean isUnableToMove(final Position source, final Position target) {
        return !ableToPath(source).contains(target);
    }

    private void movePiece(final Position source, final Position target) {
        putPiece(source, target);
        putEmpty(source);
    }

    private void putPiece(final Position source, final Position target) {
        board.put(target, board.get(source));
    }

    private void putEmpty(final Position position) {
        board.put(position, Empty.getInstance());
    }

    public Path getAbleToMove(final Position source) {
        return ableToPath(source);
    }

    public boolean isKingAlive(final Owner owner) {
        return board.containsValue(King.getInstanceOf(owner));
    }

    public boolean isTargetKing(Position target) {
        return of(target).isKing();
    }

    public boolean isPositionOwner(final Position position, final Owner owner) {
        return of(position).isSameOwner(owner);
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