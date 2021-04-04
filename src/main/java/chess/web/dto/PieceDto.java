package chess.web.dto;

import chess.domain.board.Board;
import chess.domain.piece.ChessPiece;

public class PieceDto {

    private final String notation;
    private final String position;

    public PieceDto(ChessPiece piece) {
        this.notation = piece.getNotation();
        position = toPosition(piece.getRow(), piece.getColumn());
    }

    private String toPosition(int row, int column) {
        return (char) (column + 'a') + String.valueOf(Board.getRow() - row);
    }

    public String getNotation() {
        return notation;
    }

    public String getPosition() {
        return position;
    }

}
