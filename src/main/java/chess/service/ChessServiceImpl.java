package chess.service;

import chess.dao.ChessDAO;
import chess.domain.ChessGame;
import chess.domain.ChessGameImpl;
import chess.domain.CurrentGameRoom;
import chess.exception.InvalidGameException;
import java.util.Optional;

public class ChessServiceImpl implements ChessService {

    private ChessDAO chessDAO;
    private CurrentGameRoom currentGameRoom;

    public ChessServiceImpl(ChessDAO chessDAO) {
        this.chessDAO = chessDAO;
        this.currentGameRoom = new CurrentGameRoom();
    }

    @Override
    public ChessGame loadChess(Long gameId) {
        Optional<ChessGame> chessGame = currentGameRoom.loadGame(gameId);
        if(chessGame.isPresent()) {
            return chessGame.get();
        }
        ChessGame loadedGame = chessDAO.loadGame(gameId)
            .orElse(ChessGameImpl.initialGame());

        currentGameRoom.save(gameId, loadedGame);
        return loadedGame;
    }

    @Override
    public void restart(Long gameId) {
        currentGameRoom.restart(gameId);
    }

    @Override
    public void exitGame(Long gameId) {
        currentGameRoom.remove(gameId);
        chessDAO.removeGame(gameId);
    }

    @Override
    public void saveGame(Long gameId) {
        ChessGame chessGame = currentGameRoom.loadGame(gameId)
            .orElseThrow(InvalidGameException::new);

        chessDAO.saveGame(chessGame, gameId);
    }


}
