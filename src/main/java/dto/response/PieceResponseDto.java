package dto.response;

import domain.piece.Piece;
import domain.position.Position;
import java.util.List;

public class PieceResponseDto {

    private final String coordinate;
    private final String name;
    private final boolean isBlack;

    public PieceResponseDto(Piece piece, Position position) {
        coordinate = position.chessCoordinate();
        name = piece.getName();
        isBlack = piece.isBlack();
    }

}
