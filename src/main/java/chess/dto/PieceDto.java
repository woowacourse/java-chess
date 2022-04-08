package chess.dto;

import chess.chessgame.Position;
import chess.piece.Piece;

public class PieceDto {
    private final String color;
    private final String type;
    private final int x;
    private final int y;

    public PieceDto(Piece piece, Position position) {
        color = piece.getColor().getColor();
        type = piece.getType().getSymbol();
        x = position.getX();
        y = position.getY();
    }

    public PieceDto(String color, String type, int x, int y) {
        this.color = color;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
