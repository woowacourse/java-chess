package chess.dto;

import chess.domain.piece.Piece;

public class PieceDTO {

    private final String name;
    private final String positionString;

    private PieceDTO(String name, String positionString) {
        this.name = name;
        this.positionString = positionString;
    }

    public static PieceDTO toDTO(Piece piece) {
        String positionString = piece.getPosition().getPositionString();
        if (piece.isBlank()) {
            return new PieceDTO("blank", positionString);
        }
        if (piece.isBlack()) {
            return new PieceDTO("black_" + piece.getSignature(), positionString);
        }
        return new PieceDTO("white_" + piece.getSignature(), positionString);
    }

    public String getName() {
        return name;
    }

    public String getPositionString() {
        return positionString;
    }
}
