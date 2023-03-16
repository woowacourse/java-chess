package domain.chessGame;

import domain.piece.Bishop;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.position.Position;

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
        chessBoard.put(Position.of(1, row), new Rook(color));
        chessBoard.put(Position.of(2, row), new Knight(color));
        chessBoard.put(Position.of(3, row), new Bishop(color));
        chessBoard.put(Position.of(4, row), new Queen(color));
        chessBoard.put(Position.of(5, row), new King(color));
        chessBoard.put(Position.of(6, row), new Bishop(color));
        chessBoard.put(Position.of(7, row), new Knight(color));
        chessBoard.put(Position.of(8, row), new Rook(color));
    }

    private void setUpFrontLine(int row, Color color) {
        List<Integer> columns = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        columns.stream()
                .map(column -> Position.of(column, row))
                .forEach(position -> chessBoard.put(position, new Pawn(color)));

    }
}
