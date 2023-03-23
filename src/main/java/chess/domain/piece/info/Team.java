package chess.domain.piece.info;

import chess.domain.position.File;

public enum Team {
    BLACK,
    WHITE,
    EMPTY;

    public static Team initialOf(File file) {
        if (file == File.SEVEN || file == File.EIGHT) {
            return BLACK;
        }
        if(file == File.ONE || file == File.TWO){
            return WHITE;
        }
        return EMPTY;
    }
}
