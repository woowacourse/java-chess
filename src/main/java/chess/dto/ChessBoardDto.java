package chess.dto;

import chess.domain.ChessBoard;
import chess.domain.position.Position;
import chess.domain.piece.Piece;
import chess.view.PieceName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessBoardDto {

    private static final int FIRST_INDEX = 1;
    private static final int RANK_SIZE = 8;
    private static final int FILE_SIZE = 8;
    private static final String EMPTY_SQUARE = ".";

    private final List<List<String>> board;

    private ChessBoardDto(final List<List<String>> convertedBoard) {
        this.board = convertedBoard;
    }

    public static ChessBoardDto from(final ChessBoard chessBoard) {
        List<List<String>> board = new ArrayList<>();
        Map<Position, Piece> piecesByPosition = chessBoard.piecesByPosition();
        for (int rankIndex = RANK_SIZE; rankIndex >= FIRST_INDEX; rankIndex--) {
            insertRank(board, rankIndex, piecesByPosition);
        }
        return new ChessBoardDto(board);
    }

    private static void insertRank(final List<List<String>> board, final int rankIndex,
        final Map<Position, Piece> piecesByPosition) {
        List<String> rank = new ArrayList<>();
        for (int fileIndex = FIRST_INDEX; fileIndex <= FILE_SIZE; fileIndex++) {
            insertSquare(rank, fileIndex, rankIndex, piecesByPosition);
        }
        board.add(rank);
    }

    private static void insertSquare(final List<String> rank, final int fileIndex,
        final int rankIndex, final Map<Position, Piece> piecesByPosition) {
        if (piecesByPosition.containsKey(Position.of(fileIndex, rankIndex))) {
            rank.add(
                PieceName.findNameByPiece(piecesByPosition.get(Position.of(fileIndex, rankIndex))));
            return;
        }
        rank.add(EMPTY_SQUARE);
    }

    public List<List<String>> getBoard() {
        return board;
    }

}
