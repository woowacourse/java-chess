package chess.domain.position;

import chess.domain.piece.Bishop;
import chess.domain.piece.Camp;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoardFactory {

    private ChessBoardFactory() {
    }

    public static ChessBoard getInitialChessBoard() {
        Map<Position, Piece> piecesPosition = new LinkedHashMap<>();

        setUpNobilityPiece(piecesPosition, Rank.EIGHT, Camp.BLACK);
        setUpPawn(piecesPosition, Rank.SEVEN, Camp.BLACK);

        setUpPawn(piecesPosition, Rank.TWO, Camp.WHITE);
        setUpNobilityPiece(piecesPosition, Rank.ONE, Camp.WHITE);

        return new ChessBoard(piecesPosition);
    }

    private static void setUpNobilityPiece(Map<Position, Piece> piecesPosition, Rank rank, Camp camp) {
        piecesPosition.put(Position.of(File.A, rank), new Rook(camp));
        piecesPosition.put(Position.of(File.B, rank), new Knight(camp));
        piecesPosition.put(Position.of(File.C, rank), new Bishop(camp));
        piecesPosition.put(Position.of(File.D, rank), new Queen(camp));
        piecesPosition.put(Position.of(File.E, rank), new King(camp));
        piecesPosition.put(Position.of(File.F, rank), new Bishop(camp));
        piecesPosition.put(Position.of(File.G, rank), new Knight(camp));
        piecesPosition.put(Position.of(File.H, rank), new Rook(camp));
    }

    private static void setUpPawn(Map<Position, Piece> piecesPosition, Rank rank, Camp camp) {
        for (File file : File.values()) {
            piecesPosition.put(Position.of(file, rank), new Pawn(camp));
        }
    }
}
