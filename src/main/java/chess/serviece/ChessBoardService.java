package chess.serviece;

import chess.dao.boardpieces.BoardPiecesDao;
import chess.dao.boardpieces.LocalBoardPiecesDao;
import chess.dao.boardstatuses.BoardStatusesDao;
import chess.dao.boardstatuses.LocalBoardStatusesDao;
import chess.domain.Camp;
import chess.domain.ChessBoard;
import chess.domain.PieceInitializer;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.dto.ChessBoardStatus;
import java.util.List;
import java.util.Map;

public class ChessBoardService {

    private final BoardPiecesDao boardPiecesDao = new LocalBoardPiecesDao();
    private final BoardStatusesDao boardStatusesDao = new LocalBoardStatusesDao();

    public ChessBoard findChessBoardById(int boardId) {
        Map<Position, Piece> piecesByPosition = boardPiecesDao.find(boardId)
                .orElse(PieceInitializer.createPiecesWithPosition());
        ChessBoardStatus status = boardStatusesDao.find(boardId)
                .orElse(new ChessBoardStatus(Camp.WHITE, false));
        return new ChessBoard(boardId, piecesByPosition, status);
    }

    public void updateChessBoard(ChessBoard chessBoard) {
        boardStatusesDao.insertOrUpdate(chessBoard.getId(), chessBoard.status());
        boardPiecesDao.insertOrUpdate(chessBoard.getId(), chessBoard.piecesByPosition());
    }

    public List<Integer> findAllBoardIds() {
        return boardStatusesDao.findAvailableBoardIds();
    }
}
