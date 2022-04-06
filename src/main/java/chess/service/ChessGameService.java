package chess.service;

import java.util.Map;

import chess.EmblemMapper;
import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.model.ChessGame;
import chess.model.PieceArrangement.DefaultArrangement;
import chess.model.PieceArrangement.PieceArrangement;
import chess.model.PieceColor;
import chess.model.Position;
import chess.model.Turn;

public class ChessGameService {
    private final BoardDao boardDao;
    private final GameDao gameDao;
    private ChessGame chessGame;

    public ChessGameService(GameDao gameDao, BoardDao boardDao) {
        this.gameDao = gameDao;
        this.boardDao = boardDao;
        this.chessGame = new ChessGame(new Turn(), new DefaultArrangement());
    }

    public void save() {
        boardDao.save(gameDao.getId(), EmblemMapper.StringPieceMapByPiecesByPositions(chessGame.getBoardValue()));
    }

    public Map<String, String> find() {
        return boardDao.findById(gameDao.getId());
    }

    public void move(Position source, Position target) {
        chessGame.move(source, target);
    }

    public void delete() {
        boardDao.deleteById(gameDao.getId());
        gameDao.deleteById(gameDao.getId());
    }

    public void init(Turn turn, PieceArrangement pieceArrangement) {
        this.chessGame = new ChessGame(turn, pieceArrangement);
    }

    public PieceColor getTurnColor() {
        return chessGame.getTurnColor();
    }
}
