package chess.controller.dto.response;

import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Locale;

public class PieceResponse {

    private final String position;
    private final String pieceType;
    private final String color;

    public PieceResponse(Position position, Piece piece) {
        this.position = convertPositionToString(position).toLowerCase();
        this.pieceType = piece.getPieceType().name().toLowerCase(Locale.ROOT);
        this.color = piece.getColor().name().toLowerCase(Locale.ROOT);
    }

    private static String convertPositionToString(Position position) {
        return position.getColumn() + convertRowToString(position.getRow());
    }

    private static String convertRowToString(Row row) {
        return String.valueOf(row.getValue());
    }

    public String getPosition() {
        return position;
    }

    public Piece toPiece() {
        return PieceType.valueOf(pieceType.toUpperCase(Locale.ROOT))
                .createPiece(Color.valueOf(color.toUpperCase(Locale.ROOT)));
    }
}
