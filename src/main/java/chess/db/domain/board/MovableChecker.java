package chess.db.domain.board;

import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.MoveRequestForDB;

public class MovableChecker {
    public boolean canMove(PieceEntity pieceEntity, MoveRequestForDB moveRequestForDB) {
        return false;
    }
}
