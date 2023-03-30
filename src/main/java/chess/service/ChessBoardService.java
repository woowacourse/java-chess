package chess.service;

import chess.dao.boardpieces.BoardPiecesDao;
import chess.dao.boardstatuses.BoardStatusesDao;
import chess.domain.Camp;
import chess.domain.ChessBoard;
import chess.domain.PieceInitializer;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.ChessBoardStatus;
import java.util.List;
import java.util.Map;

public class ChessBoardService {

    private final BoardPiecesDao boardPiecesDao;
    private final BoardStatusesDao boardStatusesDao;

    public ChessBoardService(final BoardPiecesDao boardPiecesDao, final BoardStatusesDao boardStatusesDao) {
        this.boardPiecesDao = boardPiecesDao;
        this.boardStatusesDao = boardStatusesDao;
    }

    public ChessBoard loadChessBoard(int boardId) {
        Map<Position, Piece> piecesByPosition = boardPiecesDao.find(boardId)
                .orElseGet(PieceInitializer::createPiecesWithPosition);
        ChessBoardStatus status = boardStatusesDao.findByBoardId(boardId)
                .orElseGet(() -> new ChessBoardStatus(Camp.WHITE, false));
        return new ChessBoard(boardId, piecesByPosition, status);
    }

    public void saveChessBoard(ChessBoard chessBoard) {
        boardStatusesDao.insertOrUpdate(chessBoard.getId(), chessBoard.status());
        boardPiecesDao.insertOrUpdate(chessBoard.getId(), chessBoard.piecesByPosition());
    }

    public List<Integer> findAllBoardIds() {
        return boardStatusesDao.findAllNotOverBoardIds();
    }
}
