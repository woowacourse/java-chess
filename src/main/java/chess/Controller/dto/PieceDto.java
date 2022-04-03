package chess.Controller.dto;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.util.PieceImage;
import java.util.Locale;

public class PieceDto {

    private final String imageUrl;
    private final String position;
    private final String symbol;

    private PieceDto(final Position position, final Piece piece) {
        final String imageKey = piece.getColor().toString()
                + piece.getPieceType().getSymbol();
        this.symbol = makeSymbol(piece);
        this.imageUrl = PieceImage.of(imageKey);
        this.position = position.getColumn().toString().toLowerCase(Locale.ROOT)
                + position.getRow().getValue();
    }

    private String makeSymbol(Piece piece) {
        final Color color = piece.getColor();
        final String symbol = piece.getPieceType().getSymbol();
        if (color == Color.BLACK) {
            return symbol.toUpperCase(Locale.ROOT);
        }
        return symbol.toLowerCase(Locale.ROOT);
    }

    public static PieceDto fromEntity(final Position position, final Piece piece) {
        return new PieceDto(position, piece);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPosition() {
        return position;
    }

    public String getSymbol() {
        return symbol;
    }
}
