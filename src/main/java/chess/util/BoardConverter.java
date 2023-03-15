package chess.util;

import chess.domain.position.Position;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardConverter {

    private static final int FIRST_INDEX = 1;
    private static final int RANK_SIZE = 8;
    private static final int FILE_SIZE = 8;
    private static final String EMPTY_SQUARE = ".";


    private BoardConverter() {
    }

    public static List<List<String>> convertToBoard(Map<Position, Piece> piecesByPosition) {
        List<List<String>> board = new ArrayList<>();
        for (int rankIndex = RANK_SIZE; rankIndex >= FIRST_INDEX; rankIndex--) {
            insertRank(board, rankIndex, piecesByPosition);
        }
        return board;
    }

    private static void insertRank(final List<List<String>> board, final int rankIndex,
                                   final Map<Position, Piece> piecesByPosition) {
        List<String> rank = new ArrayList<>();
        for (int fileIndex = FIRST_INDEX; fileIndex <= FILE_SIZE; fileIndex++) {
            insertSquare(rank, rankIndex, fileIndex, piecesByPosition);
        }
        board.add(rank);
    }

    private static void insertSquare(final List<String> rank, final int rankIndex, final int fileIndex,
                                     final Map<Position, Piece> piecesByPosition) {
        if (piecesByPosition.containsKey(new Position(rankIndex, fileIndex))) {
            rank.add(piecesByPosition.get(new Position(rankIndex, fileIndex)).getName());
            return;
        }
        rank.add(EMPTY_SQUARE);
    }
}
