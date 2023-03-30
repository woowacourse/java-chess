package techcourse.fp.chess;

import java.util.List;
import java.util.Map;
import techcourse.fp.chess.dao.ChessGameDao;
import techcourse.fp.chess.dao.PieceDao;
import techcourse.fp.chess.domain.Board;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.piece.Piece;
import techcourse.fp.chess.domain.piece.Turn;
import techcourse.fp.chess.dto.response.ChessGameInfo;

public class ChessGameService {

    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;

    public ChessGameService(final ChessGameDao chessGameDao, final PieceDao pieceDao) {
        this.chessGameDao = chessGameDao;
        this.pieceDao = pieceDao;
    }

    public void save(Board board, String name) {
        final int chessGameId = chessGameDao.save(name, board.getTurn());
        pieceDao.save(board.getBoard(), chessGameId);
    }

    public Board findById(final int id) {
        final Turn turn = chessGameDao.findTurn(id);
        final Map<Position, Piece> board = pieceDao.findByGameId(id);
        return new Board(board, turn);
    }

    public List<ChessGameInfo> findInfos() {
        return chessGameDao.findInfos();
    }
}
