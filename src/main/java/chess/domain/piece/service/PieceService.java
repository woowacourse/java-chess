package chess.domain.piece.service;

import chess.dao.PieceDao;
import chess.domain.piece.Piece;
import chess.domain.piece.state.PiecesState;
import chess.domain.position.MovingFlow;
import chess.domain.position.Position;

import java.sql.SQLException;

public class PieceService {
    private PieceDao pieceDao;

    //todo: final
    public PiecesState piecesState;

    //todo: refac throws
    public PiecesState movePiece(MovingFlow movingFlow) throws SQLException, ClassNotFoundException {
        Position from = movingFlow.getFrom();
        Position to = movingFlow.getTo();
        PiecesState updatedPiecesState = this.piecesState.movePiece(from, to);
        Piece movedPiece = updatedPiecesState.findPiece(to);
        pieceDao.update(movedPiece, to);
        return updatedPiecesState;
    }

}
