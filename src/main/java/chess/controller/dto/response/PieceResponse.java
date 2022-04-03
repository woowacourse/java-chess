package chess.controller.dto.response;

import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.Piece;

public class PieceResponse {

    private final String position;
    private final String pieceType;

    public PieceResponse(Position position, Piece piece) {
        this.position = convertPositionToString(position).toLowerCase();
        this.pieceType = convertPieceToString(piece).toLowerCase();
    }

    private static String convertPositionToString(Position position) {
        return position.getColumn() + convertRowToString(position.getRow());
    }

    private static String convertRowToString(Row row) {
        return String.valueOf(row.getValue());
    }

    private static String convertPieceToString(Piece piece) {
        return piece.getPieceType().toString() + piece.getColor().toString();
    }
}
