package chess.view;

import chess.domain.Board;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.square.Square;
import chess.domain.square.Squares;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoardView {

    private static final int MAP_SIZE = 64;
    private static final int LINE_SIZE = 8;

    private static final Map<PieceType, String> pieceInitial = new HashMap<>();

    static {
        pieceInitial.put(PieceType.KING, "k");
        pieceInitial.put(PieceType.QUEEN, "q");
        pieceInitial.put(PieceType.ROOK, "r");
        pieceInitial.put(PieceType.KNIGHT, "n");
        pieceInitial.put(PieceType.BISHOP, "b");
        pieceInitial.put(PieceType.PAWN, "p");
        pieceInitial.put(PieceType.EMPTY, ".");
    }

    public static String showChessBoard(final Board board) {
        Map<Integer, String> boardResult = makeChessBoard(board);
        StringBuilder chessBoard = new StringBuilder();
        for (int index = 0; index < MAP_SIZE; index++) {
            chessBoard.append(makeOneRank(boardResult, index));
        }
        return chessBoard.toString();
    }

    private static StringBuilder makeOneRank(final Map<Integer, String> boardResult, final int index) {
        StringBuilder rank = new StringBuilder();
        if (index % LINE_SIZE == 0) {
            rank.append("\n");
        }
        rank.append(boardResult.getOrDefault(index, "."));
        return rank;
    }

    private static Map<Integer, String> makeChessBoard(final Board board) {
        Map<Integer, String> boardResult = new LinkedHashMap<>();
        Map<Square, Piece> pieces = board.getBoard();
        for (Square key : pieces.keySet()) {
            boardResult.put(Squares.getIndex(key), convertToPieceInitial(pieces.get(key)));
        }
        return boardResult;
    }

    private static String convertToPieceInitial(final Piece piece) {
        final String initial = pieceInitial.get(piece.getPieceType());
        if (piece.getTeam() == Team.BLACK) {
            return initial.toUpperCase();
        }
        return initial;
    }
}
