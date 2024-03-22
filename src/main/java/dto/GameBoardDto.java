package dto;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import model.ChessGame;
import model.piece.Piece;
import model.position.Column;
import model.position.Position;
import model.position.Row;

public class GameBoardDto {

    private final String value;

    private GameBoardDto(final String value) {
        this.value = value;
    }

    public static GameBoardDto from(final ChessGame chessGame) {
        Map<Position, Piece> board = chessGame.getBoard();
        final String chessBoard = Arrays.stream(Row.values())
                .map(row -> Arrays.stream(Column.values())
                        .map(column -> createRow(board, column, row))
                        .collect(Collectors.joining())
                ).collect(Collectors.joining(System.lineSeparator()));
        return new GameBoardDto(chessBoard);
    }

    private static String createRow(final Map<Position, Piece> squarePieces, final Column column, final Row row) {
        final Position square = new Position(column, row);

        if (squarePieces.containsKey(square)) {
            final Piece piece = squarePieces.get(square);
            return piece.getName();
        }
        return ".";
    }

    public String getValue() {
        return value;
    }
}
