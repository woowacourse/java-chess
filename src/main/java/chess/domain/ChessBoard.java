package chess.domain;

import chess.domain.chesspieces.*;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {
    private final Map<Position, ChessPiece> chessBoard = new LinkedHashMap<>();

    public ChessBoard() {
        Positions.getValues().forEach(position -> chessBoard.put(position, new Empty()));

        Map<ChessPiece, List<Position>> locationInfo = new HashMap<>();
        locationInfo.put(new Rook("R"), Arrays.asList(Positions.of(Row.A, Column.EIGHT), Positions.of(Row.H, Column.EIGHT)));
        locationInfo.put(new Knight("N"), Arrays.asList(Positions.of(Row.B, Column.EIGHT), Positions.of(Row.G, Column.EIGHT)));
        locationInfo.put(new Bishop("B"), Arrays.asList(Positions.of(Row.C, Column.EIGHT), Positions.of(Row.F, Column.EIGHT)));
        locationInfo.put(new Queen("Q"), Arrays.asList(Positions.of(Row.D, Column.EIGHT)));
        locationInfo.put(new Queen("K"), Arrays.asList(Positions.of(Row.E, Column.EIGHT)));
        locationInfo.put(new Pawn("P"), Arrays.stream(Row.values()).map(row -> Positions.of(row, Column.SEVEN)).collect(Collectors.toList()));

        locationInfo.put(new Rook("r"), Arrays.asList(Positions.of(Row.A, Column.ONE), Positions.of(Row.H, Column.ONE)));
        locationInfo.put(new Knight("n"), Arrays.asList(Positions.of(Row.B, Column.ONE), Positions.of(Row.G, Column.ONE)));
        locationInfo.put(new Bishop("b"), Arrays.asList(Positions.of(Row.C,Column.ONE), Positions.of(Row.F, Column.ONE)));
        locationInfo.put(new Queen("q"), Arrays.asList(Positions.of(Row.D, Column.ONE)));
        locationInfo.put(new Queen("k"), Arrays.asList(Positions.of(Row.E, Column.ONE)));
        locationInfo.put(new Pawn("p"), Arrays.stream(Row.values()).map(row -> Positions.of(row, Column.TWO)).collect(Collectors.toList()));

        for (Map.Entry<ChessPiece, List<Position>> entry : locationInfo.entrySet()){
            entry.getValue().forEach(position -> chessBoard.put(position, entry.getKey()));
        }
    }

    public Map<Position, ChessPiece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }
}
