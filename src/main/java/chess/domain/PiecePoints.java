package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import java.util.LinkedHashMap;
import java.util.Map;

public final class PiecePoints {

    private final Map<Point, Piece> piecePoint = new LinkedHashMap<>();

    public PiecePoints() {
        setUpNobilityPiece(Rank.EIGHT, Camp.BLACK);
        setUpPawn(Rank.SEVEN, Camp.BLACK);

        setUpBlank(Rank.SIX);
        setUpBlank(Rank.FIVE);
        setUpBlank(Rank.FOUR);
        setUpBlank(Rank.THREE);

        setUpPawn(Rank.TWO, Camp.WHITE);
        setUpNobilityPiece(Rank.ONE, Camp.WHITE);
    }

    private void setUpNobilityPiece(Rank rank, Camp camp) {
        piecePoint.put(new Point(File.A, rank), new Rook(camp));
        piecePoint.put(new Point(File.B, rank), new Knight(camp));
        piecePoint.put(new Point(File.C, rank), new Bishop(camp));
        piecePoint.put(new Point(File.D, rank), new Queen(camp));
        piecePoint.put(new Point(File.E, rank), new King(camp));
        piecePoint.put(new Point(File.F, rank), new Bishop(camp));
        piecePoint.put(new Point(File.G, rank), new Knight(camp));
        piecePoint.put(new Point(File.H, rank), new Rook(camp));
    }

    private void setUpPawn(Rank rank, Camp camp) {
        for (File file : File.values()) {
            piecePoint.put(new Point(file, rank), new Pawn(camp));
        }
    }

    private void setUpBlank(Rank rank) {
        for (File file : File.values()) {
            piecePoint.put(new Point(file, rank), new Empty());
        }
    }

    public Map<Point, Piece> getPiecePoint() {
        return piecePoint;
    }
}
