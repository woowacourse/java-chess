package domain.chessboard;

import domain.piece.Color;
import domain.piece.Pawn;
import domain.position.Position;
import domain.position.Route;
import domain.squarestatus.Empty;
import domain.squarestatus.SquareStatus;
import domain.type.EmptyType;
import domain.type.PieceType;
import domain.type.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public final class ChessBoard {

    private static final Empty EMPTY = new Empty(EmptyType.EMPTY);
    private static final long ALL_KING_ALIVE = 2L;

    private final Map<Position, SquareStatus> chessBoard;

    public ChessBoard(final Map<Position, SquareStatus> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public SquareStatus findPosition(final Position position) {
        return chessBoard.getOrDefault(position, EMPTY);
    }

    public void move(final Position source, final Position target) {
        final SquareStatus squareStatus = chessBoard.remove(source);

        if (squareStatus.isSameType(PieceType.PAWN)) {
            chessBoard.put(target, new Pawn(squareStatus.getColor()));
            return;
        }
        chessBoard.put(target, squareStatus);
    }

    public boolean isKingAlive() {
        final long kingCount = chessBoard.values()
                .stream()
                .filter(chessBoard -> chessBoard.isSameType(PieceType.KING))
                .count();
        return kingCount == ALL_KING_ALIVE;
    }

    public Route findRoute(final Position source, final Position target) {
        final SquareStatus squareStatus = chessBoard.getOrDefault(source, EMPTY);

        return squareStatus.findRoute(source, target);
    }

    public List<SquareStatus> findPieces(final Color color) {
        return chessBoard.values()
                .stream()
                .filter(squareStatus -> squareStatus.isSameColor(color))
                .filter(squareStatus -> squareStatus.isDifferentType(PieceType.PAWN))
                .collect(toList());
    }

    public List<Long> findColumnPawnCounts(final Color color) {
        final Map<Integer, Long> ColumnPawnCount = chessBoard.entrySet()
                .stream()
                .filter(chessboard -> chessboard.getValue().isSameColor(color))
                .filter(chessboard -> chessboard.getValue().isSameType(PieceType.PAWN))
                .collect(groupingBy(chessBoard -> chessBoard.getKey().getX(), counting()));

        return new ArrayList<>(ColumnPawnCount.values());
    }

    public boolean isSameColor(final Position position, final Color color) {
        final SquareStatus squareStatus = chessBoard.getOrDefault(position, EMPTY);

        return squareStatus.isSameColor(color);
    }

    public boolean isDifferentColor(final Position position, final Color color) {
        final SquareStatus squareStatus = chessBoard.getOrDefault(position, EMPTY);

        return squareStatus.isDifferentColor(color);
    }

    public boolean isSameType(final Position position, final Type type) {
        final SquareStatus squareStatus = chessBoard.getOrDefault(position, EMPTY);

        return squareStatus.isSameType(type);
    }

    public boolean isDifferentType(final Position position, final Type type) {
        final SquareStatus squareStatus = chessBoard.getOrDefault(position, EMPTY);

        return squareStatus.isDifferentType(type);
    }

    public Map<Position, SquareStatus> getChessBoard() {
        return new HashMap<>(chessBoard);
    }

}
