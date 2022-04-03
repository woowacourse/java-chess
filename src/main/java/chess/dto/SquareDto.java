package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.ImageNameMapper;

public class SquareDto {

    private final Position position;
    private Piece piece;
    private String pieceImageName;

    public SquareDto(final Position position) {
        this.position = position;
    }

    public SquareDto(final Position position, final Piece piece) {
        this.position = position;
        this.piece = piece;
        this.pieceImageName = ImageNameMapper.from(piece);
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    public String getPieceImageName() {
        return pieceImageName;
    }
}
