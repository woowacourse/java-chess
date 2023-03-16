package chess.domain.board;

import chess.domain.camp.CampType;
import chess.domain.chess.ChessGame;
import chess.domain.move.BishopMove;
import chess.domain.move.KingMove;
import chess.domain.move.KnightMove;
import chess.domain.move.PawnMove;
import chess.domain.move.QueenMove;
import chess.domain.move.RookMove;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

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
        board.put(new Position(rank, 0), new Rook(ROOK, campType, new RookMove()));
        board.put(new Position(rank, 1), new Knight(KNIGHT, campType, new KnightMove()));
        board.put(new Position(rank, 2), new Bishop(BISHOP, campType, new BishopMove()));
        board.put(new Position(rank, 3), new Queen(QUEEN, campType, new QueenMove()));
        board.put(new Position(rank, 4), new King(KING, campType, new KingMove()));
        board.put(new Position(rank, 5), new Bishop(BISHOP, campType, new BishopMove()));
        board.put(new Position(rank, 6), new Knight(KNIGHT, campType, new KnightMove()));
        board.put(new Position(rank, 7), new Rook(ROOK, campType, new RookMove()));
    }

    private void createPawnPieces(final Map<Position, Piece> board, final int rank, final CampType campType) {
        for (int file = 0; file < 8; file++) {
            board.put(new Position(rank, file), new Pawn(PAWN, campType, new PawnMove()));
        }
    }
}
