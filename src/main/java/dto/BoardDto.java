package dto;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;

public class BoardDto {
    private final String square;
    private final String piece;
    private final String camp;

    public BoardDto(String square, String piece, String camp) {
        this.square = square;
        this.piece = piece;
        this.camp = camp;
    }

    public static BoardDto of(Square square, Piece piece) {
        return new BoardDto(square.parseToString(), piece.getType().name(), piece.getCamp().name());
    }

    public String getSquare() {
        return square;
    }

    public String getPiece() {
        return piece;
    }

    public String getCamp() {
        return camp;
    }
}
