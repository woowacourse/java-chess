package chess.domain.board.initial;

import chess.domain.board.Board;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.team.Team;

import java.util.HashMap;
import java.util.Map;

public final class BoardFactory {

    private BoardFactory() {
    }

    public static Board from(final int id, final Map<Position, Piece> board) {
        fillEmpty(board);
        fillPieces(board);
        return new Board(id, board);
    }

    private static void fillPieces(final Map<Position, Piece> board) {
        board.putAll(BlackFactory.from(new HashMap<>()));
        board.putAll(WhiteFactory.from(new HashMap<>()));
    }

    private static void fillEmpty(final Map<Position, Piece> board) {
        for (final Rank rank : Rank.values()) {
            fill(board, rank);
        }
    }

    private static void fill(final Map<Position, Piece> board, final Rank rank) {
        for (final File file : File.values()) {
            board.put(Position.of(file, rank), new Empty(Team.NONE));
        }
    }
}
