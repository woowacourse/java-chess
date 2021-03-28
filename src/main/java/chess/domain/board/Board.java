package chess.domain.board;

import chess.domain.board.position.InitPosition;
import chess.domain.board.position.Position;
import chess.domain.board.position.Ypoint;
import chess.domain.movestrategy.MoveStrategy;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Board {

    public static final int BOTH_KINGS_ALIVE = 2;
    public static final String INVALID_POSITION_MESSAGE = "유효하지 않은 좌표 입력입니다.";

    private final List<Rank> ranks;
    private final Map<Position, Piece> squares;

    public Board() {
        this(InitPosition.initSquares(), InitPosition.initRanks());
    }

    public Board(final List<Rank> ranks) {
        this(InitPosition.initSquares(), ranks);
    }

    public Board(final Map<Position, Piece> squares) {
        this(squares, new ArrayList<>());
    }

    public Board(final Map<Position, Piece> squares, List<Rank> ranks) {
        this.squares = squares;
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

    public List<Piece> piecesByYpoint(Ypoint ypoint) {
        return this.squares.entrySet()
            .stream()
            .filter(entry -> {
                Position position = entry.getKey();
                return position.isSameY(ypoint);
            })
            .map(Entry::getValue)
            .collect(Collectors.toList());
    }
}
