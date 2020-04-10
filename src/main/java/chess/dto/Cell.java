package chess.dto;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

public class Cell {
    private String tileColor;
    private String position;
    private String pieceColor;
    private String piece;

    private Cell(String tileColor, String position, String pieceColor, String piece) {
        this.tileColor = tileColor;
        this.position = position;
        this.pieceColor = pieceColor;
        this.piece = piece;
    }

    public static Cell from(Position position, Piece piece) {
        String tileColor = createTileColor(position);
        String positionName = position.getName();
        String pieceColor = piece.getPieceColorName();
        String pieceName = piece.getName();

        return new Cell(tileColor, positionName, pieceColor, pieceName);
    }

    private static String createTileColor(Position position) {
        int positionPointSum = position.getPointSum();

        if ((positionPointSum) % 2 == 0) {
            return "BLACK";
        }
        if ((positionPointSum) % 2 != 0) {
            return "WHITE";
        }
        return "NONE";
    }

    public String getTileColor() {
        return tileColor;
    }

    public void setTileColor(String tileColor) {
        this.tileColor = tileColor;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPieceColor() {
        return pieceColor;
    }

    public void setPieceColor(String pieceColor) {
        this.pieceColor = pieceColor;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }
}
