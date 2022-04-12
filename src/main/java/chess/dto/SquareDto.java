package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class SquareDto {

    private final String position;
    private String piece;
    private String pieceName;
    private String pieceColor;

    public SquareDto(final Position position) {
        final String fileName = position.getFile().name().toLowerCase();
        final int rankName = position.getRank().getValue();
        this.position = fileName + rankName;
    }

    public SquareDto(final Position position, final Piece piece) {
        final String fileName = position.getFile().name().toLowerCase();
        final int rankName = position.getRank().getValue();
        this.position = fileName + rankName;
        this.piece = piece.representative();
        this.pieceName = piece.representative().toUpperCase();
        this.pieceColor = piece.getColorName();
    }

    public String getPiece() {
        return piece;
    }

    public String getPieceName() {
        return pieceName;
    }

    public String getPieceColor() {
        return pieceColor;
    }
}
