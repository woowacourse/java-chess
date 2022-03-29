package chess.util;

import static chess.domain.board.piece.PieceType.BISHOP;
import static chess.domain.board.piece.PieceType.KING;
import static chess.domain.board.piece.PieceType.KNIGHT;
import static chess.domain.board.piece.PieceType.QUEEN;
import static chess.domain.board.piece.PieceType.ROOK;
import static chess.domain.board.position.Position.FILES_TOTAL_SIZE;

import chess.domain.board.piece.Color;
import chess.domain.board.piece.NonPawn;
import chess.domain.board.piece.Pawn;
import chess.domain.board.piece.Piece;
import chess.domain.board.piece.PieceType;
import chess.domain.board.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardMapGeneratorUtil {

    private static final int BLACK_NON_PAWN_INIT_RANK_IDX = 7;
    private static final int BLACK_PAWN_INIT_RANK_IDX = 6;
    private static final int WHITE_PAWN_INIT_RANK_IDX = 1;
    private static final int WHITE_NON_PAWN_INIT_RANK_IDX = 0;

    private static final List<PieceType> nonPawnOrder = List.of(
            ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);

    private BoardMapGeneratorUtil() {
    }

    public static Map<Position, Piece> initFullChessBoard() {
        final Map<Position, Piece> boardMap = new HashMap<>();
        updateBoard(boardMap, initNonPawns(Color.BLACK, BLACK_NON_PAWN_INIT_RANK_IDX));
        updateBoard(boardMap, initPawns(Color.BLACK, BLACK_PAWN_INIT_RANK_IDX));
        updateBoard(boardMap, initPawns(Color.WHITE, WHITE_PAWN_INIT_RANK_IDX));
        updateBoard(boardMap, initNonPawns(Color.WHITE, WHITE_NON_PAWN_INIT_RANK_IDX));
        return boardMap;
    }

    private static void updateBoard(Map<Position, Piece> boardMap, Map<Position, Piece> pieceInfoMap) {
        for (Position positionKey : pieceInfoMap.keySet()) {
            Piece pieceValue = pieceInfoMap.get(positionKey);
            boardMap.put(positionKey, pieceValue);
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
