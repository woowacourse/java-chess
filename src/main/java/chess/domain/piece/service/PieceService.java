package chess.domain.piece.service;

import chess.dao.PieceDao;
import chess.domain.piece.Piece;
import chess.domain.piece.state.PiecesState;
import chess.domain.piece.state.Result;
import chess.domain.position.MovingFlow;
import chess.domain.position.Position;

import java.util.Map;

//todo: test
public class PieceService {
    private final PieceDao pieceDao;

    public PieceService(PieceDao pieceDao) {
        this.pieceDao = pieceDao;
    }

    public PiecesState movePiece(PiecesState piecesState, MovingFlow movingFlow) {
        Position from = movingFlow.getFrom();
        Position to = movingFlow.getTo();
        PiecesState updatedPiecesState = piecesState.movePiece(from, to);
        Piece movedPiece = updatedPiecesState.findPiece(to);
        pieceDao.update(movedPiece, to);
        return updatedPiecesState;
    }

    public PiecesState initialize() {
        PiecesState piecesState = PiecesState.initialize();
        Map<Position, Piece> pieces = piecesState.getPieces();
        for (Position position : pieces.keySet()) {
            Piece piece = pieces.get(position);
            pieceDao.add(piece, position);
        }

        return piecesState;
    }
}
