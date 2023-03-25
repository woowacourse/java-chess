package chess.boardstrategy;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import chess.domain.piece.type.*;

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
        Column.getOrderedColumns().stream()
                .flatMap(column -> Rank.getOrderedRanks().stream()
                        .map(rank -> Position.of(column, rank)))
                .forEach(position -> board.put(position, EmptyPiece.of()));
    }

    private void initFirstRow(Rank rank, Color color) {
        List<Piece> firstRowPieces = List.of(new Rook(color), new Knight(color), new Bishop(color), new Queen(color),
                new King(color), new Bishop(color), new Knight(color), new Rook(color));

        IntStream.range(0, firstRowPieces.size())
                .boxed()
                .forEach(index -> board.put(Position.of(Column.findColumnByIndex(index + 1), rank), firstRowPieces.get(index)));
    }

    private void initSecondRow(Rank rank, Color color) {
        Arrays.stream(Column.values())
                .forEach(column -> board.put(Position.of(column, rank), new Pawn(color)));
    }
}
