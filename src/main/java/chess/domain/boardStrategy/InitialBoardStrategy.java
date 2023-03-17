package chess.domain.boardStrategy;

import chess.domain.Color;
import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitialBoardStrategy implements BoardStrategy {

    private final static List<Column> initialColumnsOfPawn = Column.getOrderedColumns();
    private final static List<Column> initialColumnsOfRook = List.of(Column.A, Column.H);
    private final static List<Column> initialColumnsOfKnight = List.of(Column.B, Column.G);
    private final static List<Column> initialColumnsOfBishop = List.of(Column.C, Column.F);
    private final static List<Column> initialColumnsOfQueen = List.of(Column.D);
    private final static List<Column> initialColumnsOfKing = List.of(Column.E);
    private Map<Position, Piece> board = new HashMap<>();

    @Override
    public Map<Position, Piece> generate() {
        initEmptyPieces();
        initPiecesByColor(Rank.EIGHT, Rank.SEVEN, Color.BLACK);
        initPiecesByColor(Rank.ONE, Rank.TWO, Color.WHITE);

        return new HashMap<>(board);
    }

    private void initEmptyPieces() {
        for (Rank rank : Rank.getOrderedRanks()) {
            for (Column column : Column.getOrderedColumns()) {
                board.put(new Position(column, rank), new EmptyPiece());
            }
        }
    }

    private void initPiecesByColor(Rank firstRow, Rank secondRow, Color color) {
        initPawn(secondRow, color);
        initRook(firstRow, color);
        initKnight(firstRow, color);
        initBishop(firstRow, color);
        initQueen(firstRow, color);
        initKing(firstRow, color);
    }

    private void initPawn(Rank secondRow, Color color) {
        for (Column column : initialColumnsOfPawn) {
            board.replace(new Position(column, secondRow), new Pawn(color));
        }
    }

    private void initRook(Rank firstRow, Color color) {
        for (Column column : initialColumnsOfRook) {
            board.replace(new Position(column, firstRow), new Rook(color));
        }
    }

    private void initKnight(Rank firstRow, Color color) {
        for (Column column : initialColumnsOfKnight) {
            board.replace(new Position(column, firstRow), new Knight(color));
        }
    }

    private void initBishop(Rank firstRow, Color color) {
        for (Column column : initialColumnsOfBishop) {
            board.replace(new Position(column, firstRow), new Bishop(color));
        }
    }

    private void initQueen(Rank firstRow, Color color) {
        for (Column column : initialColumnsOfQueen) {
            board.replace(new Position(column, firstRow), new Queen(color));
        }
    }

    private void initKing(Rank firstRow, Color color) {
        for (Column column : initialColumnsOfKing) {
            board.replace(new Position(column, firstRow), new King(color));
        }
    }
}
