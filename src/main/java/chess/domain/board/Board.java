package chess.domain.board;

import chess.domain.board.position.Position;
import chess.domain.movestrategy.MoveStrategy;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Board {
    public static final int BOTH_KINGS_ALIVE = 2;

    private final Map<Position, Piece> squares;

    public Board(final Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public Map<Position, Piece> squares() {
        return squares;
    }

    public void moveIfValidPosition(Position source, Position target) {
        if (isNotMovablePosition(source, target)) {
            throw new IllegalArgumentException("유효하지 않은 좌표 입력입니다.");
        }
        swapPieces(source, target);
    }

    private Boolean isNotMovablePosition(Position source, Position target) {
        Piece piece = pieceOfPosition(source);
        MoveStrategy moveStrategy = piece.moveStrategy();
        Set<Position> movablePath = moveStrategy.moveStrategy(this, source);
        return !movablePath.contains(target);
    }

    public Piece pieceOfPosition(Position position) {
        Piece piece = squares.get(position);
        if (Objects.isNull(piece)) {
            throw new IllegalArgumentException();
        }
        return piece;
    }

    private void swapPieces(Position source, Position target) {
        Piece sourcePiece = pieceOfPosition(source);
        replacePiece(source, Empty.create());
        replacePiece(target, sourcePiece);
    }

    private void replacePiece(Position position, Piece piece) {
        if (squares.containsKey(position)) {
            squares.replace(position, piece);
        }
    }

    public boolean isAliveBothKings() {
        return squares.values().stream()
                .filter(Piece::isKing)
                .count() == BOTH_KINGS_ALIVE;
    }
}
