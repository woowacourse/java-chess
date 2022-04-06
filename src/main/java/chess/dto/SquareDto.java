package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.view.ImageNameMapper;

public class SquareDto {

    private final Position position;
    private Piece piece;
    private String pieceImageName;

    public SquareDto(final Position position) {
        this.position = position;
    }

    public SquareDto(final String position) {
        this(Position.valueOf(position));
    }

    public SquareDto(final Position position, final Piece piece) {
        this.position = position;
        this.piece = piece;
        this.pieceImageName = ImageNameMapper.from(piece);
    }

    public SquareDto(final String position, final String piece, final String color) {
        this.position = Position.valueOf(position);
        this.piece = PieceFactory.create(piece, color);
        this.pieceImageName = ImageNameMapper.from(this.piece);
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
