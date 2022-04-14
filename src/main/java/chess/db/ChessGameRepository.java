package chess.db;

import chess.db.dao.ChessGameDao;
import chess.db.dao.PieceDao;
import chess.db.entity.ChessGameEntity;
import chess.db.entity.PieceEntity;
import chess.domain.ChessGame;
import chess.domain.state.State;
import chess.dto.BoardDto;
import chess.dto.StateType;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ChessGameRepository {

    private final ChessGameDao chessGameDao = new ChessGameDao();
    private final PieceDao pieceDao = new PieceDao();

    public int save(final ChessGame chessGame) {
        int chessGameId = chessGameDao.save(chessGame);
        savePieces(chessGame, chessGameId);
        return chessGameId;
    }

    public ChessGame find(final int id) {
        ChessGameEntity chessGameEntity = chessGameDao.find(id);
        List<PieceEntity> pieceEntities = pieceDao.findByChessGameId(id);

        State state = chessGameEntity.getState(pieceEntities);
        return new ChessGame(state);
    }

    public void move(final int chessGameId, final ChessGame chessGame) {
        String nextState = StateType.getType(chessGame.getState());
        chessGameDao.modify(chessGameId, nextState);

        movePieces(chessGame, chessGameId);
    }

    public void end(final int chessGameId, final ChessGame chessGame) {
        String endState = StateType.getType(chessGame.getState());
        chessGameDao.modify(chessGameId, endState);
    }

    private void savePieces(final ChessGame chessGame, final int chessGameId) {
        Map<String, String> board = BoardDto.from(chessGame.getBoard()).getBoard();
        for (Entry<String, String> entry : board.entrySet()) {
            pieceDao.save(entry.getKey(), entry.getValue(), chessGameId);
        }
    }

    private void movePieces(final ChessGame chessGame, final int chessGameId) {
        deletePieces(chessGameId);
        savePieces(chessGame, chessGameId);
    }

    private void deletePieces(final int chessGameId) {
        pieceDao.delete(chessGameId);
    }
}
