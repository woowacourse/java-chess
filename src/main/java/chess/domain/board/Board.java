package chess.domain.board;

import chess.domain.board.position.InitPosition;
import chess.domain.board.position.Position;
import chess.domain.movestrategy.MoveStrategy;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Set;

public class Board {

    public static final int BOTH_KINGS_ALIVE = 2;
    public static final String INVALID_POSITION_MESSAGE = "유효하지 않은 좌표 입력입니다.";

    private final List<Rank> ranks;

    public Board() {
        this(InitPosition.initRanks());
    }

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
            .orElseThrow(() -> new IllegalArgumentException(INVALID_POSITION_MESSAGE));
    }

    public boolean isAliveBothKings() {
        return this.ranks.stream()
            .flatMap(rank -> rank.pieces().stream())
            .filter(Piece::isKing)
            .count() == BOTH_KINGS_ALIVE;
    }

    public void moveIfValidPosition(Position source, Position target) {
        if (isInvalidPosition(source, target)) {
            throw new IllegalArgumentException(INVALID_POSITION_MESSAGE);
        }

        swapPieces(source, target);
    }

    private boolean isInvalidPosition(Position source, Position target) {
        Piece piece = pieceByPosition(source);
        MoveStrategy moveStrategy = piece.moveStrategy();
        Set<Position> movablePath = moveStrategy.currentPositionMoveStrategy(this, source);
        return !movablePath.contains(target);
    }

    private void swapPieces(Position source, Position target) {
        Piece sourcePiece = pieceByPosition(source);
        replacePiece(source, Empty.create());
        replacePiece(target, sourcePiece);
    }

    private void replacePiece(Position position, Piece piece) {
        Rank foundRank = this.ranks.stream()
            .filter(rank -> rank.hasPosition(position))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_POSITION_MESSAGE));

        foundRank.replacePiece(position, piece);
    }
}
