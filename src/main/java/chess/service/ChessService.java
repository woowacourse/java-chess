package chess.service;

import chess.domain.dao.BoardDao;
import chess.domain.dao.BoardDaoImpl;
import chess.domain.dao.GameDao;
import chess.domain.dao.GameDaoImpl;
import chess.domain.dto.BoardDto;
import chess.domain.dto.GameDto;
import chess.domain.game.ChessGame;
import chess.domain.state.Ready;

public class ChessService {

    private final GameDao gameDao;
    private final BoardDao boardDao;

    public ChessService() {
        this(new GameDaoImpl(), new BoardDaoImpl());
    }

    public ChessService(GameDao gameDao, BoardDao boardDao) {
        this.gameDao = gameDao;
        this.boardDao = boardDao;
    }

    public ChessGame createRoom() {
        final ChessGame chessGame = new ChessGame(new Ready());
        chessGame.start();

        System.out.println(GameDto.from(chessGame));
        final Long saveId = gameDao.save(GameDto.from(chessGame));

        boardDao.save(BoardDto.of(saveId, chessGame.board()));
        return chessGame;
    }
}
