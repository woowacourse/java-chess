package domain.repository;

import domain.Board;
import domain.Location;
import domain.dao.BoardDao;
import domain.dao.PieceDao;
import domain.piece.Piece;
import domain.type.Color;
import java.util.Map;

public final class ChessRepositoryImpl implements ChessRepository {

    private final BoardDao boardDao;
    private final PieceDao pieceDao;

    public ChessRepositoryImpl(final BoardDao boardDao, final PieceDao pieceDao) {
        this.boardDao = boardDao;
        this.pieceDao = pieceDao;
    }

    @Override
    public Board findBoardById(final String id) {
        final Map<Location, Piece> board = pieceDao.findAllByBoardId(id);
        return new Board(board);
    }

    @Override
    public Color findLastColorFromBoardById(final String id) {
        return boardDao.findLastColor(id);
    }

    @Override
    public boolean existBoard(final String id) {
        return boardDao.count(id) != 0;
    }

    @Override
    public void insert(final String boardId, final Map<Location, Piece> board, final Color color) {
        boardDao.insert(boardId, color);
        pieceDao.insert(board, boardId);
    }

    @Override
    public void update(final String boardId, final Map<Location, Piece> board, final Color color) {
        boardDao.update(boardId, color);
        pieceDao.update(board, boardId);
    }
}
