package chess.dto;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class PieceInfoDto {
    private final Position position;
    private final Piece piece;

    private PieceInfoDto(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public static PieceInfoDto create(File file, Rank rank, Color color, PieceType type) {
        Position position = Position.from(file, rank);
        Piece piece = type.getInstance(color);
        return new PieceInfoDto(position, piece);
    }

    public static PieceInfoDto create(Position position, Piece piece) {
        return new PieceInfoDto(position, piece);
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return "PieceInfoDto{" +
                "position=" + position +
                ", piece=" + piece +
                '}';
    }
}
