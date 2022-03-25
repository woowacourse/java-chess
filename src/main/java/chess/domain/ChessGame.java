package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
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
        addRooks(chessPieces, team, pieceRow);
        addKnights(chessPieces, team, pieceRow);
        addBishops(chessPieces, team, pieceRow);
        addKingAndQueen(chessPieces, team, pieceRow);
        return new ChessMen(chessPieces);
    }

    private static void addPawns(List<ChessPiece> chessPieces, Team team, int row) {
        for (char column = 'a'; column < 'i'; column++) {
            chessPieces.add(new Pawn(team, new ChessBoardPosition(column, row)));
        }
    }

    private static void addRooks(List<ChessPiece> chessPieces, Team team, int row) {
        chessPieces.add(new Rook(team, new ChessBoardPosition(ChessGame.ROOK_COLUMN, row)));
        chessPieces.add(new Rook(team, new ChessBoardPosition(calculatePairColumn(ROOK_COLUMN), row)));
    }

    private static void addKnights(List<ChessPiece> chessPieces, Team team, int row) {
        chessPieces.add(new Knight(team, new ChessBoardPosition(ChessGame.KNIGHT_COLUMN, row)));
        chessPieces.add(new Knight(team, new ChessBoardPosition(calculatePairColumn(KNIGHT_COLUMN), row)));
    }

    private static void addBishops(List<ChessPiece> chessPieces, Team team, int row) {
        chessPieces.add(new Bishop(team, new ChessBoardPosition(ChessGame.BISHOP_COLUMN, row)));
        chessPieces.add(new Bishop(team, new ChessBoardPosition(calculatePairColumn(BISHOP_COLUMN), row)));
    }

    private static void addKingAndQueen(List<ChessPiece> whiteChessPieces, Team team, int row) {
        whiteChessPieces.add(new Queen(team, new ChessBoardPosition(QUEEN_COLUMN, row)));
        whiteChessPieces.add(new King(team, new ChessBoardPosition(KING_COLUMN, row)));
    }

    private static char calculatePairColumn(char column) {
        return (char) (PAIR_COLUMN_SUM - column);
    }

    public ChessMen getBlackChessMen() {
        return blackChessMen;
    }

    public ChessMen getWhiteChessMen() {
        return whiteChessMen;
    }

    public void move(ChessBoardPosition sourcePosition, ChessBoardPosition targetPosition) {
        ChessPiece chessPiece = getChessPiece(sourcePosition);
        ChessMen allyChessMen = getAlly();

        if (!chessPiece.movable(targetPosition, allyChessMen)) {
            throw new IllegalArgumentException("[ERROR] 경로에 다른 체스가 있어 이동할 수 없습니다.");
        }
        chessPiece.move(targetPosition);
    }

    private ChessMen getAlly() {
        if (turn.isWhite()) {
            return whiteChessMen;
        }
        return blackChessMen;
    }

    private ChessPiece getChessPiece(ChessBoardPosition sourcePosition) {
        if (turn.isSame(Team.WHITE)) {
            return whiteChessMen.getChessPieceAt(sourcePosition);
        }
        return blackChessMen.getChessPieceAt(sourcePosition);
    }
}
