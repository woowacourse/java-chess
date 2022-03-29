package chess2.util2;

import static chess2.domain2.board2.piece2.PieceType.BISHOP;
import static chess2.domain2.board2.piece2.PieceType.KING;
import static chess2.domain2.board2.piece2.PieceType.KNIGHT;
import static chess2.domain2.board2.piece2.PieceType.QUEEN;
import static chess2.domain2.board2.piece2.PieceType.ROOK;
import static chess2.util2.PositionConverterUtil.FILES_TOTAL_SIZE;

import chess2.domain2.board2.position.Position;
import chess2.domain2.board2.piece2.Color;
import chess2.domain2.board2.piece2.NonPawn;
import chess2.domain2.board2.piece2.Pawn;
import chess2.domain2.board2.piece2.Piece;
import chess2.domain2.board2.piece2.PieceType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceGeneratorUtil {

    private static final int BLACK_NON_PAWN_INIT_RANK_IDX = 7;
    private static final int BLACK_PAWN_INIT_RANK_IDX = 6;
    private static final int WHITE_PAWN_INIT_RANK_IDX = 1;
    private static final int WHITE_NON_PAWN_INIT_RANK_IDX = 0;

    private static final List<PieceType> nonPawnOrder = List.of(
            ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);

    private PieceGeneratorUtil() {
    }

    public static Map<Position, Piece> initFullChessBoard() {
        final Map<Position, Piece> board = new HashMap<>();
        updateBoard(board, initNonPawns(Color.BLACK, BLACK_NON_PAWN_INIT_RANK_IDX));
        updateBoard(board, initPawns(Color.BLACK, BLACK_PAWN_INIT_RANK_IDX));
        updateBoard(board, initPawns(Color.WHITE, WHITE_PAWN_INIT_RANK_IDX));
        updateBoard(board, initNonPawns(Color.WHITE, WHITE_NON_PAWN_INIT_RANK_IDX));
        return board;
    }

    private static void updateBoard(Map<Position, Piece> board, Map<Position, Piece> pieceInfoMap) {
        for (Position positionKey : pieceInfoMap.keySet()) {
            Piece pieceValue = pieceInfoMap.get(positionKey);
            board.put(positionKey, pieceValue);
        }
    }

    private static Map<Position, Piece> initPawns(Color color, int initRank) {
        Map<Position, Piece> pawnBoard = new HashMap<>();
        for (int fileIdx = 0; fileIdx < FILES_TOTAL_SIZE; fileIdx++) {
            Position position = Position.of(fileIdx, initRank);
            pawnBoard.put(position, new Pawn(color));
        }
        return pawnBoard;
    }

    private static Map<Position, Piece> initNonPawns(Color color, int initRank) {
        Map<Position, Piece> nonPawnBoard = new HashMap<>();
        for (int fileIdx = 0; fileIdx < FILES_TOTAL_SIZE; fileIdx++) {
            Position position = Position.of(fileIdx, initRank);
            Piece nonPawn = new NonPawn(color, nonPawnOrder.get(fileIdx));
            nonPawnBoard.put(position, nonPawn);
        }
        return nonPawnBoard;
    }
}
