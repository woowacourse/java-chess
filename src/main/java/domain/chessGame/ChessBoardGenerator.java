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

public class ChessBoardGenerator {

    private final Map<Position, Piece> chessBoard = new HashMap<>();

    public Map<Position, Piece> generate() {
        setUpBlackPieces();
        setUpWhitePieces();
        return chessBoard;
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

        for (int column : columns) {
            chessBoard.put(Position.of(row, column), new Pawn(color));
        }
    }
}
