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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class InitialBoardStrategy implements BoardStrategy {
    private final Map<Position, Piece> board = new HashMap<>();

    @Override
    public Map<Position, Piece> generate() {
        initEmptyPieces();
        initFirstRow(Rank.EIGHT, Color.BLACK);
        initSecondRow(Rank.SEVEN, Color.BLACK);
        initFirstRow(Rank.ONE, Color.WHITE);
        initSecondRow(Rank.TWO, Color.WHITE);

        return new HashMap<>(board);
    }

    private void initEmptyPieces() {
        for (Rank rank : Rank.getReversedOrderedRanks()) {
            for (Column column : Column.getOrderedColumns()) {
                board.put(new Position(column, rank), new EmptyPiece());
            }
        }
    }

    private void initFirstRow(Rank rank, Color color) {
        List<Piece> firstRowPieces = List.of(new Rook(color), new Knight(color), new Bishop(color), new Queen(color),
                new King(color), new Bishop(color), new Knight(color), new Rook(color));

        IntStream.range(0, firstRowPieces.size())
                .forEach(i -> board.replace(new Position(Column.findColumnByIndex(i + 1), rank),
                        firstRowPieces.get(i))
                );

    }

    private void initSecondRow(Rank rank, Color color) {
        Arrays.stream(Column.values())
                .forEach(column -> board.replace(new Position(column, rank),
                        new Pawn(color))
                );
    }
}
