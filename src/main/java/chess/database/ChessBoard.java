package chess.database;

import java.util.Objects;

public class ChessBoard {
    private String position;
    private String piece;

    public ChessBoard(String position, String piece) {
        this.position = position;
        this.piece = piece;
    }

    public String getPosition() {
        return position;
    }

    public String getPiece() {
        return piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard board = (ChessBoard) o;
        return Objects.equals(position, board.position) &&
                Objects.equals(piece, board.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, piece);
    }
}