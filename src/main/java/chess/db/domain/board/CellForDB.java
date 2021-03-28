package chess.db.domain.board;

import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.MoveRouteForDB;
import chess.beforedb.domain.piece.type.PieceType;
import chess.beforedb.domain.player.type.TeamColor;

public class CellForDB {
    private static final String EMPTY_STATUS = ".";

    private PieceEntity pieceEntity;

    public CellForDB() {
        this.pieceEntity = null;
    }

    public void put(PieceEntity pieceEntity) {
        this.pieceEntity = pieceEntity;
    }

    public boolean canMoveTo(MoveRouteForDB moveRouteForDB, BoardForDB boardForDB) {
        return pieceEntity.canMoveTo(moveRouteForDB, boardForDB);
    }

    public void movePieceTo(CellForDB destinationCell) {
        destinationCell.put(pieceEntity);
        pieceEntity = null;
    }

    public TeamColor teamColor() {
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

    public String status() {
        if (pieceEntity != null) {
            return pieceEntity.getName();
        }
        return EMPTY_STATUS;
    }
}
