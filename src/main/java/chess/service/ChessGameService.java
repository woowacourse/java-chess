package chess.service;

import java.util.Map;

import chess.Game;
import chess.MappingUtil;
import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.model.ChessGame;
import chess.model.PieceArrangement.PieceArrangement;
import chess.model.PieceArrangement.SavedPieceArrangement;
import chess.model.PieceColor;
import chess.model.Turn;

public class ChessGameService {
    private final BoardDao boardDao;
    private final GameDao gameDao;

    public ChessGameService(GameDao gameDao, BoardDao boardDao) {
        this.gameDao = gameDao;
        this.boardDao = boardDao;
    }

    public void save(Game game, ChessGame chessGame) {
        gameDao.save(game);
        boardDao.save(game.getId(), MappingUtil.StringPieceMapByPiecesByPositions(chessGame.getBoardValue()));
    }

    public ChessGame init(int gameId, Turn turn, PieceArrangement pieceArrangement) {
        if (findById(gameId).isEmpty()) {
            return new ChessGame(turn, pieceArrangement);
        }
        return new ChessGame(new Turn(PieceColor.valueOf(gameDao.findTurnById(gameId))),
            new SavedPieceArrangement(findById(gameId)));
    }

    public Map<String, String> findById(int gameId) {
        return boardDao.findById(gameId);
    }

    public void deleteById(int gameId) {
        boardDao.deleteById(gameId);
        gameDao.deleteById(gameId);
    }

    public int findIdByPlayers(String idPlayerWhite, String idPlayerBlack) {
        return gameDao.findIdByPlayers(idPlayerWhite, idPlayerBlack);
    }

    public String findTurnById(int gameId) {
        return findTurnById(gameId);
    }
}
