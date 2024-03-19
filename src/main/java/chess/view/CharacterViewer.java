package chess.view;

import chess.domain.piece.character.Character;

public class CharacterViewer {
    public static String convertToString(Character character) {
        return switch (character) {
            case BLACK_PAWN -> "P";
            case BLACK_KNIGHT -> "N";
            case BLACK_BISHOP -> "B";
            case BLACK_ROOK -> "R";
            case BLACK_QUEEN -> "Q";
            case BLACK_KING -> "K";
            case WHITE_PAWN -> "p";
            case WHITE_KNIGHT -> "n";
            case WHITE_BISHOP -> "b";
            case WHITE_ROOK -> "r";
            case WHITE_QUEEN -> "q";
            case WHITE_KING -> "k";
        };
    }
}
