package chess.db.entity;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.PieceInfo;

public class PieceEntity {

    private final int id;
    private final String position;
    private final String symbol;

    public PieceEntity(final int id, final String position, final String symbol) {
        this.id = id;
        this.position = position;
        this.symbol = symbol;
    }

    public Position getPosition() {
        return Position.from(position);
    }

    public Piece getPiece() {
        return PieceInfo.getPiece(symbol);
    }
}
