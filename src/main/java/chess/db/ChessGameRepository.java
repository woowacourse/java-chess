package chess.db;

import chess.db.dao.ChessGameDao;
import chess.db.dao.PieceDao;
import chess.db.entity.ChessGameEntity;
import chess.db.entity.PieceEntity;
import chess.domain.ChessGame;
import chess.domain.state.State;
import chess.dto.BoardDto;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ChessGameRepository {

    private final ChessGameDao chessGameDao = new ChessGameDao();
    private final PieceDao pieceDao = new PieceDao();

    public int save(final ChessGame chessGame) {
        int chessGameId = chessGameDao.save(chessGame);
        Map<String, String> board = BoardDto.from(chessGame.getBoard()).getBoard();
        for (Entry<String, String> entry : board.entrySet()) {
            pieceDao.save(entry.getKey(), entry.getValue(), chessGameId);
        }
        return chessGameId;
    }

    public ChessGame find(final int id) {
        ChessGameEntity chessGameEntity = chessGameDao.find(id);
        List<PieceEntity> pieceEntities = pieceDao.findByChessGameId(id);

        State state = chessGameEntity.getState(pieceEntities);
        return new ChessGame(state);
    }
}
