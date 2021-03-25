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
    private final List<Rank> ranks;

    public Board(final List<Rank> ranks) {
        this.ranks = ranks;
    }

    public List<Rank> ranks() {
        return this.ranks;
    }

    public Piece pieceByPosition(Position position) {
        return this.ranks.stream()
            .filter(rank -> rank.hasPosition(position))
            .map(map -> map.piece(position))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isAliveBothKings() {
        return this.ranks
            .stream()
            .flatMap(rank -> rank.squares().stream())
            .filter(Piece::isKing)
            .count() == BOTH_KINGS_ALIVE;
    }

    public void moveIfValidPosition(Position source, Position target) {
        if (isNotMovablePosition(source, target)) {
            throw new IllegalArgumentException("유효하지 않은 좌표 입력입니다.");
        }
        swapPieces(source, target);
    }

    private boolean isNotMovablePosition(Position source, Position target) {
        Piece piece = pieceByPosition(source);
        MoveStrategy moveStrategy = piece.moveStrategy();
        Set<Position> movablePath = moveStrategy.moveStrategy(this, source);
        return !movablePath.contains(target);
    }

    private void swapPieces(Position source, Position target) {
        Piece sourcePiece = pieceByPosition(source);
        replacePiece(source, Empty.create());
        replacePiece(target, sourcePiece);
    }

    private void replacePiece(Position position, Piece piece) {
        Rank findedRank = this.ranks
            .stream()
            .filter(rank -> rank.hasPosition(position))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);

        findedRank.replacePiece(position, piece);
    }
}
