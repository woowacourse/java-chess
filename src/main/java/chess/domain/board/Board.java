package chess.domain.board;

import chess.domain.board.position.InitPosition;
import chess.domain.board.position.Position;
import chess.domain.board.position.Xpoint;
import chess.domain.board.position.Ypoint;
import chess.domain.movestrategy.MoveStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Board {

    public static final int BOTH_KINGS_ALIVE = 2;
    public static final String INVALID_POSITION_MESSAGE = "유효하지 않은 좌표 입력입니다.";

    private final Map<Position, Piece> squares;

    public Board() {
        this(InitPosition.initSquares());
    }

    public Board(final Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public Piece pieceByPosition(final Position position) {
        return this.squares.get(position);
    }

    public boolean isAliveBothKings() {
        return this.squares.values()
            .stream()
            .filter(Piece::isKing)
            .count() == BOTH_KINGS_ALIVE;
    }

    public void moveIfValidPosition(final Position source, final Position target) {
        if (isInvalidPosition(source, target)) {
            throw new IllegalArgumentException(INVALID_POSITION_MESSAGE);
        }

        swapPieces(source, target);
    }

    private boolean isInvalidPosition(final Position source, final Position target) {
        Piece piece = pieceByPosition(source);
        MoveStrategy moveStrategy = piece.moveStrategy();
        Set<Position> movablePath = moveStrategy.currentPositionMoveStrategy(this, source);
        return !movablePath.contains(target);
    }

    private void swapPieces(final Position source, final Position target) {
        Piece sourcePiece = pieceByPosition(source);
        replacePiece(source, Empty.create());
        replacePiece(target, sourcePiece);
    }

    private void replacePiece(final Position position, final Piece piece) {
        this.squares.replace(position, piece);
    }

    public List<Piece> piecesByXpoint(final Xpoint xpoint) {
        return this.squares.entrySet()
            .stream()
            .filter(entry -> {
                Position position = entry.getKey();
                return position.isSameX(xpoint);
            })
            .map(Entry::getValue)
            .collect(Collectors.toList());
    }

    public List<Piece> piecesByYpoint(final Ypoint ypoint) {
        return this.squares.entrySet()
            .stream()
            .filter(entry -> {
                Position position = entry.getKey();
                return position.isSameY(ypoint);
            })
            .map(Entry::getValue)
            .collect(Collectors.toList());
    }

    public List<Piece> piecesByColor(final Color color) {
        return this.squares.values()
            .stream()
            .filter(piece -> piece.isSameColor(color))
            .collect(Collectors.toList());
    }

    public List<Position> movablePath(Position position) {
        Piece piece = pieceByPosition(position);
        MoveStrategy moveStrategy = piece.moveStrategy();
        return new ArrayList<>(
            moveStrategy.currentPositionMoveStrategy(this, position)
        );
    }

    public Map<Position, Piece> squares() {
        return Collections.unmodifiableMap(this.squares);
    }
}
