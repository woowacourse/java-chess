package view;

import domain.game.PieceType;

import java.util.Map;

import static domain.game.PieceType.*;

public class OutputConvertor {
    private static final Map<PieceType, String> pieceFormat = Map.ofEntries(
            Map.entry(BLACK_ROOK, "R"),
            Map.entry(BLACK_KNIGHT, "N"),
            Map.entry(BLACK_BISHOP, "B"),
            Map.entry(BLACK_QUEEN, "Q"),
            Map.entry(BLACK_KING, "K"),
            Map.entry(BLACK_PAWN, "P"),
            Map.entry(WHITE_ROOK, "r"),
            Map.entry(WHITE_KNIGHT, "n"),
            Map.entry(WHITE_BISHOP, "b"),
            Map.entry(WHITE_QUEEN, "q"),
            Map.entry(WHITE_KING, "k"),
            Map.entry(WHITE_PAWN, "p")
    );

    public String convertPieceTypeToString(final PieceType pieceType) {
        return pieceFormat.get(pieceType);
    }
}
