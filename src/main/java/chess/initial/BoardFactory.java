package chess.initial;

import chess.domain.Position;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.Map;

public class BoardFactory {

    private final Map<Position, Piece> board;

    private BoardFactory(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static BoardFactory create(final Map<Position, Piece> board) {
        fillEmpty(board);

        return new BoardFactory(board);
    }

    private static void fillEmpty(final Map<Position, Piece> board) {
        for (final Rank rank : Rank.values()) {
            fill(board, rank);
        }
    }

    private static void fill(final Map<Position, Piece> board, final Rank rank) {
        for (final File file : File.values()) {
            board.put(Position.of(file.value(), rank.value()), new Empty("."));
        }
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
