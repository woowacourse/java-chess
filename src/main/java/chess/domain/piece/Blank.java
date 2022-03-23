package chess.domain.piece;

import chess.domain.Color;

public class Blank extends Piece{
    public Blank() {
        super(Color.NONE);
    }

    @Override
    public String baseSignature() {
        return ".";
    }
}
