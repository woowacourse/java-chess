package chess.dto.dtomapper;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.strategy.piecemovestrategy.PieceType;
import chess.dto.PieceDto;

public class PieceMapper {

    public PieceDto toDto(Piece piece) {
        final PieceType pieceType = piece.getPieceType();
        final Color pieceColor = piece.getColor();
        String type = getType(pieceType);
        String color = getColor(pieceColor);
        return new PieceDto(type, color);
    }

    private String getType(final PieceType pieceType) {
        switch (pieceType) {
            case PAWN: {
                return "p";
            }
            case BISHOP: {
                return "b";
            }
            case ROOK: {
                return "r";
            }
            case KNIGHT: {
                return "n";
            }
            case QUEEN: {
                return "q";
            }
            case KING: {
                return "k";
            }
            case EMPTY: {
                return ".";
            }
        }
        throw new IllegalArgumentException("뭔가 단단히 문제가 있습니다");
    }

    private String getColor(final Color pieceColor) {
        if (pieceColor == Color.WHITE) {
            return "white";
        }
        if (pieceColor == Color.BLACK) {
            return "black";
        }
        return "empty";
    }
}
