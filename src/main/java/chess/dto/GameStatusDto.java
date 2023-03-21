package chess.dto;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.view.PieceTypeConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameStatusDto {

    private static final int BOARD_WIDTH = 8;

    private final List<String> gameStatus;

    private GameStatusDto(final List<String> gameStatus) {
        this.gameStatus = gameStatus;
    }

    public static GameStatusDto from(final Board board) {
        List<String> gameStatus = new ArrayList<>();
        for (int i = BOARD_WIDTH - 1; i >= 0; i--) {
            final Rank rank = Rank.getRankByIndex(i);
            final String row = rowToString(board.getBoard(), rank);
            gameStatus.add(row);
        }
        return new GameStatusDto(gameStatus);
    }

    private static String rowToString(final Map<Square, Piece> board, final Rank rank) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < BOARD_WIDTH; index++) {
            final File file = File.getFileByIndex(index);
            final Square square = Square.of(file, rank);
            final char squareStatus = getSquareStatus(Map.copyOf(board), square);
            stringBuilder.append(squareStatus);
        }
        return stringBuilder.toString();
    }

    private static char getSquareStatus(final Map<Square, Piece> board, final Square square) {
        if (board.containsKey(square)) {
            final Piece piece = board.get(square);
            return PieceTypeConverter.getSymbol(piece.getPieceType(), piece.getColor());
        }
        return PieceTypeConverter.getEmptySymbol();
    }

    public List<String> getGameStatus() {
        return new ArrayList<>(gameStatus);
    }
}
