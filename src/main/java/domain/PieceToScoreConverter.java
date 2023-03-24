package domain;

import java.util.HashMap;
import java.util.Map;

import domain.piece.Camp;
import domain.piece.Piece;
import domain.piece.nonslider.Empty;
import domain.piece.nonslider.Knight;
import domain.piece.nonslider.Pawn;
import domain.piece.slider.Bishop;
import domain.piece.slider.King;
import domain.piece.slider.Queen;
import domain.piece.slider.Rook;

public class PieceToScoreConverter {
    private static final Map<Piece, Double> piecesAndScores = new HashMap<>();

    private PieceToScoreConverter() {
    }

    public static Double convert(Piece piece) {
        return piecesAndScores.get(piece);
    }

    public static void init() {
        piecesAndScores.put(new Empty(), 0.0);
        piecesAndScores.put(new King(Camp.WHITE), 0.0);
        piecesAndScores.put(new King(Camp.BLACK), 0.0);
        piecesAndScores.put(new Queen(Camp.WHITE), 9.0);
        piecesAndScores.put(new Queen(Camp.BLACK), 9.0);
        piecesAndScores.put(new Knight(Camp.WHITE), 2.5);
        piecesAndScores.put(new Knight(Camp.BLACK), 2.5);
        piecesAndScores.put(new Bishop(Camp.WHITE), 3.0);
        piecesAndScores.put(new Bishop(Camp.BLACK), 3.0);
        piecesAndScores.put(new Rook(Camp.WHITE), 5.0);
        piecesAndScores.put(new Rook(Camp.BLACK), 5.0);
        piecesAndScores.put(new Pawn(Camp.WHITE), 1.0);
        piecesAndScores.put(new Pawn(Camp.BLACK), 1.0);
    }
}
