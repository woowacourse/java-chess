package chess.service;

import chess.domain.board.Board;
import chess.game.ChessGame;
import chess.game.Turn;
import database.dto.ChessGameDto;
import database.production.BoardDao;
import database.production.ChessGameDao;

import java.util.List;

public class ChessGameService {

    private final ChessGameDao chessGameDao = new ChessGameDao();
    private final BoardDao boardDao = new BoardDao();

    public void save(ChessGame chessGame, String status) {
        ChessGameDto chessGameDto = new ChessGameDto(chessGame.getId(), status, chessGame.getCurrentTeam().name());
        int saveId = chessGameDao.save(chessGameDto);
        chessGame.setId(saveId);
    }

    public void update(ChessGame chessGame, String status) {
        ChessGameDto chessGameDto = new ChessGameDto(chessGame.getId(), status, chessGame.getCurrentTeam().name());
        chessGameDao.update(chessGameDto);
    }

    public ChessGame findById(int gameId) {
        Turn turn = chessGameDao.findTurnById(gameId);
        Board board = boardDao.findByGameId(gameId);
        ChessGame chessGame = new ChessGame(board, turn);
        chessGame.setId(gameId);
        return chessGame;
    }

    public List<Integer> findAllGameIds() {
        return chessGameDao.findContinuingGameIds();
    }
}
