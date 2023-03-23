package chess.domain.chessGame;

import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardGenerator implements BoardGenerator {

    private final Map<Position, Piece> chessBoard = new HashMap<>();

    @Override
    public Map<Position, Piece> generate() {
        setUp();
        return chessBoard;
    }

    private void setUp() {
        setUpBlackPieces();
        setUpWhitePieces();
    }

    private void setUpBlackPieces() {
        setUpBackLine(8, Color.BLACK);
        setUpFrontLine(7, Color.BLACK);
    }

    private void setUpWhitePieces() {
        setUpFrontLine(2, Color.WHITE);
        setUpBackLine(1, Color.WHITE);
    }

    private void setUpBackLine(int row, Color color) {
        chessBoard.put(Position.of(row, 1), new Rook(color));
        chessBoard.put(Position.of(row, 2), new Knight(color));
        chessBoard.put(Position.of(row, 3), new Bishop(color));
        chessBoard.put(Position.of(row, 4), new Queen(color));
        chessBoard.put(Position.of(row, 5), new King(color));
        chessBoard.put(Position.of(row, 6), new Bishop(color));
        chessBoard.put(Position.of(row, 7), new Knight(color));
        chessBoard.put(Position.of(row, 8), new Rook(color));
    }

    private void setUpFrontLine(int row, Color color) {
        List<Integer> columns = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        for (Integer column : columns) {
            Position position = Position.of(row, column);
            chessBoard.put(position, new Pawn(color));
        }
    }
}
