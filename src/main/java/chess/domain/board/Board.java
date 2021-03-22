package chess.domain.board;

import chess.domain.board.position.Position;
import chess.domain.movestrategy.MoveStrategy;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Board {
    public static final int BOTH_KINGS_ALIVE = 2;
    private final List<Map<Position, Piece>> squares;

    public Board(final List<Map<Position, Piece>> squares) {
        this.squares = squares;
    }

    public List<Map<Position, Piece>> squares() {
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
        return squares.stream()
                .filter(map -> map.containsKey(position))
                .map(map -> map.get(position))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private void swapPieces(Position source, Position target) {
        Piece sourcePiece = pieceOfPosition(source);
        replacePiece(source, Empty.create());
        replacePiece(target, sourcePiece);
    }

    private void replacePiece(Position position, Piece piece) {
        for (Map<Position, Piece> map : squares) {
            replaceIfValidKey(position, piece, map);
        }
    }

    private void replaceIfValidKey(Position position, Piece piece, Map<Position, Piece> map) {
        if (map.containsKey(position)) {
            map.replace(position, piece);
        }
    }

    public boolean isAliveBothKings() {
        return squares.stream()
                .flatMap(map -> map.values().stream())
                .filter(Piece::isKing)
                .count() == BOTH_KINGS_ALIVE;
    }
}
