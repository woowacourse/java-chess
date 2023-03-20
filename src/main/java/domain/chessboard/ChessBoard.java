package domain.chessboard;

import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.piece.Color;
import domain.piece.Pawn;
import domain.squarestatus.Empty;
import domain.squarestatus.SquareStatus;
import domain.type.EmptyType;
import domain.type.PieceType;
import domain.type.Type;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private static final Empty EMPTY = new Empty(EmptyType.EMPTY);

    private final Map<Position, SquareStatus> chessBoard;

    protected ChessBoard(final Map<Position, SquareStatus> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public SquareStatus findPosition(final Position position) {
        return chessBoard.getOrDefault(position, EMPTY);
    }

    public void move(final Position source, final Position target) {
        final SquareStatus squareStatus = chessBoard.remove(source);

        if (squareStatus.getType() == PieceType.PAWN) {
            chessBoard.put(target, new Pawn(squareStatus.getColor()));
            return;
        }
        chessBoard.put(target, squareStatus);
    }

    public Route findRoute(final Position source, final Position target) {
        final SquareStatus squareStatus = chessBoard.getOrDefault(source, EMPTY);

        return squareStatus.findRoute(source, target);
    }

    public boolean isSameColor(final Position position, final Color color) {
        final SquareStatus squareStatus = chessBoard.getOrDefault(position, EMPTY);

        return squareStatus.getColor() == color;
    }

    public boolean isDifferentColor(final Position position, final Color color) {
        final SquareStatus squareStatus = chessBoard.getOrDefault(position, EMPTY);


        return squareStatus.getColor() != color;
    }

    public boolean isEqualType(final Position position, final Type type) {
        final SquareStatus squareStatus = chessBoard.getOrDefault(position, EMPTY);

        return squareStatus.getType() == type;
    }

    public boolean isDifferentType(final Position position, final Type type) {
        final SquareStatus squareStatus = chessBoard.getOrDefault(position, EMPTY);

        return squareStatus.getType() != type;
    }

    public Map<Position, SquareStatus> getChessBoard() {
        return new HashMap<>(chessBoard);
    }
}
