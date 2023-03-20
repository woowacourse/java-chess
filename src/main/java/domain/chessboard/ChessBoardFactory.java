package domain.chessboard;

import domain.coordinate.Position;
import domain.piece.*;
import domain.squarestatus.SquareStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ChessBoardFactory {

    OTHERS_BLACK(List.of(new Rook(Color.BLACK), new Knight(Color.BLACK), new Bishop(Color.BLACK), new Queen(Color.BLACK), new King(Color.BLACK), new Bishop(Color.BLACK), new Knight(Color.BLACK), new Rook(Color.BLACK))),
    PAWN_BLACK(List.of(new InitPawn(Color.BLACK), new InitPawn(Color.BLACK), new InitPawn(Color.BLACK), new InitPawn(Color.BLACK), new InitPawn(Color.BLACK), new InitPawn(Color.BLACK), new InitPawn(Color.BLACK), new InitPawn(Color.BLACK))),
    PAWN_WHITE(List.of(new InitPawn(Color.WHITE), new InitPawn(Color.WHITE), new InitPawn(Color.WHITE), new InitPawn(Color.WHITE), new InitPawn(Color.WHITE), new InitPawn(Color.WHITE), new InitPawn(Color.WHITE), new InitPawn(Color.WHITE))),
    OTHERS_WHITE(List.of(new Rook(Color.WHITE), new Knight(Color.WHITE), new Bishop(Color.WHITE), new Queen(Color.WHITE), new King(Color.WHITE), new Bishop(Color.WHITE), new Knight(Color.WHITE), new Rook(Color.WHITE)));

    private static final Map<Position, SquareStatus> chessBoard;
    private static final int MAX_SIZE = 8;

    private final List<SquareStatus> squareStatuses;

    ChessBoardFactory(final List<SquareStatus> squareStatuses) {
        this.squareStatuses = squareStatuses;
    }

    static {
        chessBoard = new HashMap<>();

        putRow(0, OTHERS_BLACK.squareStatuses);
        putRow(1, PAWN_BLACK.squareStatuses);
        putRow(6, PAWN_WHITE.squareStatuses);
        putRow(7, OTHERS_WHITE.squareStatuses);
    }

    public static ChessBoard generate() {
        return new ChessBoard(new HashMap<>(chessBoard));
    }

    private static void putRow(final int row, final List<SquareStatus> squareStatuses) {
        for (int column = 0; column < MAX_SIZE; column++) {
            chessBoard.put(Position.of(column, row), squareStatuses.get(column));
        }
    }

}
