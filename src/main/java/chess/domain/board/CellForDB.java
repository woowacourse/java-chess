package chess.domain.board;

import chess.domain.piece.PieceEntity;
import chess.domain.piece.type.PieceType;
import chess.domain.player.type.TeamColor;

public class CellForDB {
    private static final String EMPTY_STATUS = ".";

    private PieceEntity pieceEntity;

    public CellForDB() {
        this.pieceEntity = null;
    }

    public CellForDB(PieceEntity pieceEntity) {
        this.pieceEntity = pieceEntity;
    }

    public void put(PieceEntity pieceEntity) {
        this.pieceEntity = pieceEntity;
    }

    public void movePieceTo(CellForDB destinationCell) {
        destinationCell.put(pieceEntity);
        pieceEntity = null;
    }

    public TeamColor getTeamColor() {
        return pieceEntity.getTeamColor();
    }

    public boolean isEmpty() {
        return pieceEntity == null;
    }

    public PieceType getPieceType() {
        return pieceEntity.getPieceType();
    }

    public PieceEntity getPieceEntity() {
        return pieceEntity;
    }

    public String getStatus() {
        if (pieceEntity != null) {
            return pieceEntity.getName();
        }
        return EMPTY_STATUS;
    }
}
