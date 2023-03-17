package chess.domain.board;

import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;
import chess.domain.piece.Position;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Knight;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Piece;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static chess.domain.piece.type.PieceType.BISHOP;
import static chess.domain.piece.type.PieceType.KING;
import static chess.domain.piece.type.PieceType.KNIGHT;
import static chess.domain.piece.type.PieceType.PAWN;
import static chess.domain.piece.type.PieceType.QUEEN;
import static chess.domain.piece.type.PieceType.ROOK;

public final class ChessBoardFactory {

    private static final Map<ChessGame, ChessBoardFactory> CACHE = new ConcurrentHashMap<>();

    public ChessBoardFactory() {
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
        board.put(new Position(rank, 0), new Rook(ROOK, campType));
        board.put(new Position(rank, 1), new Knight(KNIGHT, campType));
        board.put(new Position(rank, 2), new Bishop(BISHOP, campType));
        board.put(new Position(rank, 3), new Queen(QUEEN, campType));
        board.put(new Position(rank, 4), new King(KING, campType));
        board.put(new Position(rank, 5), new Bishop(BISHOP, campType));
        board.put(new Position(rank, 6), new Knight(KNIGHT, campType));
        board.put(new Position(rank, 7), new Rook(ROOK, campType));
    }

    private void createPawnPieces(final Map<Position, Piece> board, final int rank, final CampType campType) {
        for (int file = 0; file < 8; file++) {
            board.put(new Position(rank, file), new Pawn(PAWN, campType));
        }
    }
}
