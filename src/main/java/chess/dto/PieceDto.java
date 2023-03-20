package chess.dto;

import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;

import java.util.Map;

import static chess.piece.PieceType.BISHOP;
import static chess.piece.PieceType.EMPTY;
import static chess.piece.PieceType.KING;
import static chess.piece.PieceType.KNIGHT;
import static chess.piece.PieceType.PAWN;
import static chess.piece.PieceType.QUEEN;
import static chess.piece.PieceType.ROOK;

public class PieceDto {

    private final String view;
    private final Map<PieceType, String> views = Map.of(
            KING, "k",
            QUEEN, "q",
            KNIGHT, "n",
            PAWN, "p",
            ROOK, "r",
            BISHOP, "b",
            EMPTY, "."
    );

    public PieceDto(final Piece piece) {
        this.view = parseByTeam(piece);
    }

    private String parseByTeam(final Piece piece) {
        String result = views.get(piece.getType());

        if (piece.getTeam() == Team.BLACK) {
            return result.toUpperCase();
        }
        return result;
    }

    public String getView() {
        return view;
    }
}
