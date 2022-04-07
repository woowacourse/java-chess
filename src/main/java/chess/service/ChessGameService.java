package chess.service;

import chess.dao.ChessGameDao;
import chess.domain.ChessGame;
import chess.domain.ChessMap;
import chess.domain.generator.BlackGenerator;
import chess.domain.generator.WhiteGenerator;
import chess.domain.player.Player;
import chess.domain.player.Team;

public class ChessGameService {

    private final ChessGameDao chessGameDao;

    private ChessGame chessGame;

    public ChessGameService(ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public ChessMap initializeChessGame() {
        final Player whitePlayer = new Player(new WhiteGenerator(), Team.WHITE);
        final Player blackPlayer = new Player(new BlackGenerator(), Team.BLACK);
        chessGame = new ChessGame(whitePlayer, blackPlayer);
        chessGameDao.initializeChessGame(whitePlayer.findAll(), blackPlayer.findAll());
        return chessGame.createMap();
    }
}
