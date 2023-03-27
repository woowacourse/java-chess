package chess.service;

import chess.dao.chess.PieceDao;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.move.Position;
import chess.entity.PieceEntity;
import chess.service.mapper.ChessBoardMapper;

import java.util.List;
import java.util.Map;

public class ChessBoardService {
    private final PieceDao pieceDao;

    public ChessBoardService(final PieceDao pieceDao) {
        this.pieceDao = pieceDao;
    }

    ChessBoard getByChessGameId(final Long chessGameId) {
        final List<PieceEntity> pieceEntities = pieceDao.findByChessGameId(chessGameId);
        final Map<Position, Piece> board = ChessBoardMapper.from(pieceEntities);
        return ChessBoard.create(board);
    }

    void savePiece(final PieceEntity pieceEntity) {
        pieceDao.save(pieceEntity);
    }

    void deletePieces(final PieceEntity sourcePiece, final PieceEntity targetPiece) {
        final Long chessGameId = sourcePiece.getChessGameId();
        pieceDao.deleteByPositions(chessGameId, sourcePiece, targetPiece);
    }

    void saveAll(final Long chessGameId, final Map<Position, Piece> chessBoard) {
        for (Position position : chessBoard.keySet()) {
            final Piece piece = chessBoard.get(position);
            PieceEntity pieceEntity = PieceEntity.createWithChessGameId(chessGameId, position.getRank(),
                    position.getFile(), piece.getPieceType().name(), piece.getCampType().name());
            pieceDao.save(pieceEntity);
        }
    }

    void deleteByChessGameId(final Long chessGameId) {
        pieceDao.deleteByChessGameId(chessGameId);
    }
}
