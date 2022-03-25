package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    private static final int BLACK_PAWN_ROW = 2;
    private static final int BLACK_PIECE_ROW = 1;
    private static final int WHITE_PAWN_ROW = 7;
    private static final int WHITE_PIECE_ROW = 8;
    private static final char ROOK_COLUMN = 'a';
    private static final char KNIGHT_COLUMN = 'b';
    private static final char BISHOP_COLUMN = 'c';
    private static final char QUEEN_COLUMN = 'd';
    private static final char KING_COLUMN = 'e';
    private static final int PAIR_COLUMN_SUM = 'a' + 'h';

    private final ChessMen blackChessMen;
    private final ChessMen whiteChessMen;
    private final Team turn;

    private ChessGame(ChessMen blackChessMen, ChessMen whiteChessMen) {
        this.blackChessMen = blackChessMen;
        this.whiteChessMen = whiteChessMen;
        this.turn = Team.WHITE;
    }

    public static ChessGame create() {
        ChessMen blackChessMen = initializeChessMen(Team.BLACK, BLACK_PAWN_ROW, BLACK_PIECE_ROW);
        ChessMen whiteChessMen = initializeChessMen(Team.WHITE, WHITE_PAWN_ROW, WHITE_PIECE_ROW);
        return new ChessGame(blackChessMen, whiteChessMen);
    }

    private static ChessMen initializeChessMen(Team team, int pawnRow, int pieceRow) {
        List<ChessPiece> chessPieces = new ArrayList<>();
        addPawns(chessPieces, team, pawnRow);
        addPieces(chessPieces, team, Type.ROOK, ROOK_COLUMN, pieceRow);
        addPieces(chessPieces, team, Type.KNIGHT, KNIGHT_COLUMN, pieceRow);
        addPieces(chessPieces, team, Type.BISHOP, BISHOP_COLUMN, pieceRow);
        addKingAndQueen(chessPieces, team, pieceRow);
        return new ChessMen(chessPieces);
    }

    private static void addKingAndQueen(List<ChessPiece> whiteChessPieces, Team team, int row) {
        whiteChessPieces.add(new ChessPiece(team, Type.QUEEN, new ChessBoardPosition(QUEEN_COLUMN, row)));
        whiteChessPieces.add(new ChessPiece(team, Type.KING, new ChessBoardPosition(KING_COLUMN, row)));
    }

    private static void addPawns(List<ChessPiece> chessPieces, Team team, int row) {
        for (char column = 'a'; column < 'i'; column++) {
            chessPieces.add(new ChessPiece(team, Type.PAWN, new ChessBoardPosition(column, row)));
        }
    }

    private static void addPieces(List<ChessPiece> chessPieces, Team team, Type type, char column, int row) {
        chessPieces.add(new ChessPiece(team, type, new ChessBoardPosition(column, row)));
        chessPieces.add(new ChessPiece(team, type, new ChessBoardPosition((char) (PAIR_COLUMN_SUM - column), row)));
    }

    public boolean existsAllyChessPiece(ChessBoardPosition position) {
        if (turn.isWhite()) {
            return whiteChessMen.existChessPieceInPosition(position);
        }
        return blackChessMen.existChessPieceInPosition(position);
    }

    public ChessMen getBlackChessMen() {
        return blackChessMen;
    }

    public ChessMen getWhiteChessMen() {
        return whiteChessMen;
    }
}
