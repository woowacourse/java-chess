package domain.chessboard;

import domain.piece.Bishop;
import domain.piece.BlackPawn;
import domain.piece.Blank;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.WhitePawn;
import domain.piece.base.ChessPiece;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoardInitializer {

    private static final int CHESS_BOARD_SIZE = 8;

    public static List<Row> createInitialBoard() {
        return List.of(
                createFirstRank(Color.BLACK),
                createSamePiecesRow(getPawnByColor(Color.BLACK)),
                createSamePiecesRow(Blank.getInstance()),
                createSamePiecesRow(Blank.getInstance()),
                createSamePiecesRow(Blank.getInstance()),
                createSamePiecesRow(Blank.getInstance()),
                createSamePiecesRow(getPawnByColor(Color.WHITE)),
                createFirstRank(Color.WHITE)
        );
    }

    private static Row createFirstRank(Color color) {
        return new Row(Arrays.asList(
                new Rook(color),
                new Knight(color),
                new Bishop(color),
                new Queen(color),
                new King(color),
                new Bishop(color),
                new Knight(color),
                new Rook(color)
        ));
    }

    private static Row createSamePiecesRow(ChessPiece piece) {
        List<ChessPiece> row = new ArrayList<>();
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            row.add(piece);
        }
        return new Row(row);
    }

    private static ChessPiece getPawnByColor(Color color) {
        if (color == Color.WHITE) {
            return new WhitePawn();
        }
        return new BlackPawn();
    }
}
