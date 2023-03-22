package chess.domain.board;

import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;
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
import java.util.concurrent.ConcurrentHashMap;

import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;

public final class ChessBoardFactory {

    private static final Map<ChessGame, ChessBoardFactory> CACHE = new ConcurrentHashMap<>();

    private ChessBoardFactory() {
    }

    static ChessBoardFactory getInstance(final ChessGame chessGame) {
        if (CACHE.containsKey(chessGame)) {
            return CACHE.get(chessGame);
        }
        final ChessBoardFactory chessBoardFactory = new ChessBoardFactory();
        CACHE.put(chessGame, chessBoardFactory);
        return chessBoardFactory;
    }

    public Map<Position, Piece> createBoard() {
        final Map<Position, Piece> board = new HashMap<>();
        createWhiteArea(board);
        createBlackArea(board);
        return board;
    }

    private void createWhiteArea(final Map<Position, Piece> board) {
        createPieces(board, 0, CampType.WHITE);
        createPawnPieces(board, 1, CampType.WHITE);
    }

    private void createBlackArea(final Map<Position, Piece> board) {
        createPawnPieces(board, 6, CampType.BLACK);
        createPieces(board, 7, CampType.BLACK);
    }

    private void createPieces(final Map<Position, Piece> board, final int rank, final CampType campType) {
        board.put(new Position(rank, 0), new Piece(ROOK, campType, new Rook()));
        board.put(new Position(rank, 1), new Piece(KNIGHT, campType, new Knight()));
        board.put(new Position(rank, 2), new Piece(BISHOP, campType, new Bishop()));
        board.put(new Position(rank, 3), new Piece(QUEEN, campType, new Queen()));
        board.put(new Position(rank, 4), new Piece(KING, campType, new King()));
        board.put(new Position(rank, 5), new Piece(BISHOP, campType, new Bishop()));
        board.put(new Position(rank, 6), new Piece(KNIGHT, campType, new Knight()));
        board.put(new Position(rank, 7), new Piece(ROOK, campType, new Rook()));
    }

    private void createPawnPieces(final Map<Position, Piece> board, final int rank, final CampType campType) {
        for (int file = 0; file < 8; file++) {
            board.put(new Position(rank, file), new Piece(PAWN, campType, new Pawn()));
        }
    }
}
