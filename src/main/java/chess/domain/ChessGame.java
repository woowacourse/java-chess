package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    private final ChessMen blackChessMen;
    private final ChessMen whiteChessMen;

    private ChessGame(ChessMen blackChessMen, ChessMen whiteChessMen) {
        this.blackChessMen = blackChessMen;
        this.whiteChessMen = whiteChessMen;
    }

    public static ChessGame create() {
        ChessMen blackChessMen = initializeChessMen(Team.BLACK, 7, 8);
        ChessMen whiteChessMen = initializeChessMen(Team.WHITE, 2, 1);
        return new ChessGame(blackChessMen, whiteChessMen);
    }

    private static ChessMen initializeChessMen(Team team, int pawnRow, int pieceRow) {
        List<ChessPiece> chessPieces = new ArrayList<>();
        addPawns(chessPieces, team, pawnRow);
        addPieces(chessPieces, team, Type.KNIGHT, 'b', pieceRow);
        addPieces(chessPieces, team, Type.BISHOP, 'c', pieceRow);
        addPieces(chessPieces, team, Type.ROOK, 'a', pieceRow);
        addKingAndQueen(chessPieces, team, pieceRow);
        return new ChessMen(chessPieces);
    }

    private static void addKingAndQueen(List<ChessPiece> whiteChessPieces, Team team, int row) {
        whiteChessPieces.add(new ChessPiece(team, Type.QUEEN, new ChessBoardPosition('d', row)));
        whiteChessPieces.add(new ChessPiece(team, Type.KING, new ChessBoardPosition('e', row)));
    }

    private static void addPawns(List<ChessPiece> chessPieces, Team team, int row) {
        for (char column = 'a'; column < 'i'; column++) {
            chessPieces.add(new ChessPiece(team, Type.PAWN, new ChessBoardPosition(column, row)));
        }
    }

    private static void addPieces(List<ChessPiece> chessPieces, Team team, Type type, char column, int row) {
        chessPieces.add(new ChessPiece(team, type, new ChessBoardPosition(column, row)));
        chessPieces.add(new ChessPiece(team, type, new ChessBoardPosition((char)('a' + 'h' - column), row)));
    }
}
