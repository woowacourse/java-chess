package chess.domain.piece.service;

import chess.dao.PieceDao;
import chess.domain.piece.Piece;
import chess.domain.piece.state.PiecesState;
import chess.domain.position.MovingFlow;
import chess.domain.position.Position;

import java.util.Map;

public class PieceService {
    private final PieceDao pieceDao;

    public PieceService(PieceDao pieceDao) {
        this.pieceDao = pieceDao;
    }

    public PiecesState movePiece(MovingFlow movingFlow) {
        Position from = movingFlow.getFrom();
        Position to = movingFlow.getTo();
        Map<Position, Piece> pieces = pieceDao.getAll();
        PiecesState piecesState = PiecesState.of(pieces);
        PiecesState updatedPiecesState = piecesState.movePiece(from, to);
        Piece movedPiece = updatedPiecesState.findPiece(to);
        pieceDao.update(movedPiece, from, to);
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

    public PiecesState getAll() {
        Map<Position, Piece> pieces = pieceDao.getAll();
        return PiecesState.of(pieces);
    }
}
