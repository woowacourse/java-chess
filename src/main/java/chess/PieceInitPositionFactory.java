package chess;

import chess.domain.Player;
import chess.domain.chesspieces.*;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;
import javafx.geometry.Pos;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PieceInitPositionFactory {
    private static Map<Piece, List<Position>> initPositions = new LinkedHashMap<>();
    static {
        Player blackPlayer = Player.BLACK;
        initPositions.put(new Rook(blackPlayer), Arrays.asList(Positions.of(Row.A, Column.EIGHT), Positions.of(Row.H, Column.EIGHT)));
        initPositions.put(new Knight(blackPlayer), Arrays.asList(Positions.of(Row.B, Column.EIGHT), Positions.of(Row.G, Column.EIGHT)));
        initPositions.put(new Bishop(blackPlayer), Arrays.asList(Positions.of(Row.C, Column.EIGHT), Positions.of(Row.F, Column.EIGHT)));
        initPositions.put(new Queen(blackPlayer), Arrays.asList(Positions.of(Row.D, Column.EIGHT)));
        initPositions.put(new King(blackPlayer), Arrays.asList(Positions.of(Row.E, Column.EIGHT)));
        List<Position> blackPawnPositions = Arrays.stream(Row.values())
                .map(row -> Positions.of(row, Column.SEVEN))
                .collect(Collectors.toList());
        for (Position position : blackPawnPositions) {
            initPositions.put(new Pawn(blackPlayer, position), Arrays.asList(position));
        }

        Player whitePlayer = Player.WHITE;
        initPositions.put(new Rook(whitePlayer), Arrays.asList(Positions.of(Row.A, Column.ONE), Positions.of(Row.H, Column.ONE)));
        initPositions.put(new Knight(whitePlayer), Arrays.asList(Positions.of(Row.B, Column.ONE), Positions.of(Row.G, Column.ONE)));
        initPositions.put(new Bishop(whitePlayer), Arrays.asList(Positions.of(Row.C, Column.ONE), Positions.of(Row.F, Column.ONE)));
        initPositions.put(new Queen(whitePlayer), Arrays.asList(Positions.of(Row.D, Column.ONE)));
        initPositions.put(new King(whitePlayer), Arrays.asList(Positions.of(Row.E, Column.ONE)));

        List<Position> whitePawnPositions = Arrays.stream(Row.values())
                .map(row -> Positions.of(row, Column.TWO))
                .collect(Collectors.toList());
        for (Position position : whitePawnPositions) {
            initPositions.put(new Pawn(whitePlayer, position), Arrays.asList(position));
        }
    }

    public static Map<Piece, List<Position>> create() {
        return initPositions;
    }

}
