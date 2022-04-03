package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardInitializer {
    private static final char COLUMN_FIRST_INDEX = 'a';
    private static final char COLUMN_LAST_INDEX = 'h';
    private static final int WHITE_PIECE_ROW = 1;
    private static final int WHITE_PAWN_ROW = 2;
    private static final int BLACK_PIECE_ROW = 8;
    private static final int BLACK_PAWN_ROW = 7;
    private static final char ROOK_COLUMN = 'a';
    private static final char KNIGHT_COLUMN = 'b';
    private static final char BISHOP_COLUMN = 'c';
    private static final char QUEEN_COLUMN = 'd';
    private static final char KING_COLUMN = 'e';
    private static final int PAIR_COLUMN_SUM = 'a' + 'h';

    private ChessBoardInitializer() {
    }

    public static Map<ChessBoardPosition, ChessPiece> generate() {
        Map<ChessBoardPosition, ChessPiece> blackChessPieces = generateChessPiece(Team.BLACK, BLACK_PAWN_ROW,
                BLACK_PIECE_ROW);
        Map<ChessBoardPosition, ChessPiece> whiteChessPieces = generateChessPiece(Team.WHITE, WHITE_PAWN_ROW,
                WHITE_PIECE_ROW);
        Map<ChessBoardPosition, ChessPiece> board = new HashMap<>();
        board.putAll(blackChessPieces);
        board.putAll(whiteChessPieces);
        return board;
    }

    private static Map<ChessBoardPosition, ChessPiece> generateChessPiece(Team team, int pawnRow, int pieceRow) {
        Map<ChessBoardPosition, ChessPiece> chessPieces = new HashMap<>();
        addPawns(chessPieces, team, pawnRow);
        addRooks(chessPieces, team, pieceRow);
        addKnights(chessPieces, team, pieceRow);
        addBishops(chessPieces, team, pieceRow);
        addKingAndQueen(chessPieces, team, pieceRow);
        return chessPieces;
    }

    private static void addPawns(Map<ChessBoardPosition, ChessPiece> chessPieces, Team team, int row) {
        for (char column = COLUMN_FIRST_INDEX; column <= COLUMN_LAST_INDEX; column++) {
            chessPieces.put(ChessBoardPosition.of(column, row), new Pawn(team));
        }
    }

    private static void addRooks(Map<ChessBoardPosition, ChessPiece> chessPieces, Team team, int row) {
        chessPieces.put(ChessBoardPosition.of(ROOK_COLUMN, row), new Rook(team));
        chessPieces.put(ChessBoardPosition.of(calculatePairColumn(ROOK_COLUMN), row), new Rook(team));
    }

    private static void addKnights(Map<ChessBoardPosition, ChessPiece> chessPieces, Team team, int row) {
        chessPieces.put(ChessBoardPosition.of(KNIGHT_COLUMN, row), new Knight(team));
        chessPieces.put(ChessBoardPosition.of(calculatePairColumn(KNIGHT_COLUMN), row), new Knight(team));
    }

    private static void addBishops(Map<ChessBoardPosition, ChessPiece> chessPieces, Team team, int row) {
        chessPieces.put(ChessBoardPosition.of(BISHOP_COLUMN, row), new Bishop(team));
        chessPieces.put(ChessBoardPosition.of(calculatePairColumn(BISHOP_COLUMN), row), new Bishop(team));
    }

    private static void addKingAndQueen(Map<ChessBoardPosition, ChessPiece> chessPieces, Team team, int row) {
        chessPieces.put(ChessBoardPosition.of(QUEEN_COLUMN, row), new Queen(team));
        chessPieces.put(ChessBoardPosition.of(KING_COLUMN, row), new King(team));
    }

    private static char calculatePairColumn(char column) {
        return (char) (PAIR_COLUMN_SUM - column);
    }

}
