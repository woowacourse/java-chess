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

    public ChessBoard getByChessGameId(final Long chessGameId) {
        final List<PieceEntity> pieceEntities = pieceDao.findByChessGameId(chessGameId);
        final Map<Position, Piece> board = ChessBoardMapper.from(pieceEntities);
        return ChessBoard.create(board);
    }
}
