package chess.service;

import chess.domain.board.ChessBoard;
import chess.domain.chess.CampType;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.move.Position;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;

public final class ChessBoardHelper {
    
    public static ChessBoard createMockProgressBoard() {
        final Map<Position, Piece> board = new HashMap<>();
        createWhiteProgressArea(board);
        createBlackProgressArea(board);
        return ChessBoard.create(board);
    }

    private static void createWhiteProgressArea(final Map<Position, Piece> board) {
        createProgressPieces(board, 0, CampType.WHITE);
        createProgressPawnPieces(board, 1, CampType.WHITE);
    }

    private static void createBlackProgressArea(final Map<Position, Piece> board) {
        createProgressPawnPieces(board, 6, CampType.BLACK);
        createProgressPieces(board, 7, CampType.BLACK);
    }

    private static void createProgressPieces(final Map<Position, Piece> board, final int rank, final CampType campType) {
        board.put(new Position(rank, 0), new Piece(ROOK, campType, new Rook()));
        board.put(new Position(rank, 1), new Piece(KNIGHT, campType, new Knight()));
        board.put(new Position(rank, 2), new Piece(BISHOP, campType, new Bishop()));
        board.put(new Position(rank, 3), new Piece(QUEEN, campType, new Queen()));
        board.put(new Position(rank, 4), new Piece(KING, campType, new King()));
    }

    private static void createProgressPawnPieces(final Map<Position, Piece> board, final int rank, final CampType campType) {
        for (int file = 0; file < 4; file++) {
            board.put(new Position(rank, file), new Piece(PAWN, campType, new Pawn()));
        }
    }
}
