package chess.db;

import chess.db.dao.ChessGameDao;
import chess.db.dao.PieceDao;
import chess.db.entity.ChessGameEntity;
import chess.db.entity.PieceEntity;
import chess.domain.ChessGame;
import chess.domain.state.State;

import java.util.List;

public class ChessGameRepository {

    private final ChessGameDao chessGameDao = new ChessGameDao();
    private final PieceDao pieceDao = new PieceDao();

    public int save(final ChessGame chessGame) {
        return chessGameDao.save(chessGame);
    }

    public ChessGame find(final int id) {
        ChessGameEntity chessGameEntity = chessGameDao.find(id);
        List<PieceEntity> pieceEntities = pieceDao.findByChessGameId(id);

        State state = chessGameEntity.getState(pieceEntities);
        return new ChessGame(state);
    }
}
