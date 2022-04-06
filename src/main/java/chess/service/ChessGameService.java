package chess.service;

import chess.EmblemMapper;
import chess.dao.BoardDao;
import chess.model.ChessGame;
import chess.model.PieceArrangement.DefaultArrangement;
import chess.model.Turn;

public class ChessGameService {
    private final int gameId;
    private final BoardDao boardDao;
    private ChessGame chessGame;

    public ChessGameService(int gameId, BoardDao boardDao) {
        this.gameId = gameId;
        this.boardDao = boardDao;
        this.chessGame = new ChessGame(new Turn(), new DefaultArrangement());
    }

    public void save() {
        boardDao.save(gameId, EmblemMapper.StringPieceMapByPiecesByPositions(chessGame.getBoardValue()));
    }
}
