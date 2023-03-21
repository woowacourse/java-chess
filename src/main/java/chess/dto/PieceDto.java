package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.Map;

import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.EMPTY;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;

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
