package chess.web;

import chess.domain.ChessScore;
import chess.domain.piece.Piece;

public class Element {

    private String image;
    private ChessScore chessScore;
    private String message;

    private Element(Piece piece) {
        this.image = piece.getImage();
    }

    private Element(ChessScore chessScore) {
        this.chessScore = chessScore;
    }

    public Element(String message) {
        this.message = message;
    }

    public String getImage() {
        return this.image;
    }

    public static Element from(Piece piece) {
        return new Element(piece);
    }

    public static Element from(ChessScore chessScore) {
        return new Element(chessScore);
    }

    public static Element from(String message) {
        return new Element(message);
    }

    public String getWhiteScore() {
        return String.valueOf(chessScore.getWhiteScore());
    }

    public String getBlackScore() {
        return String.valueOf(chessScore.getBlackScore());
    }

    public String getMessage() {
        return message;
    }
}
