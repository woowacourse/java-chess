package chess.service;

import chess.dao.ChessGameDao;
import chess.domain.ChessGame;
import chess.domain.ChessMap;
import chess.domain.GameResult;
import chess.domain.generator.BlackGenerator;
import chess.domain.generator.WhiteGenerator;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.dto.StatusDto;
import java.util.List;

public class ChessGameService {

    private final ChessGameDao chessGameDao;

    private ChessGame chessGame;

    public ChessGameService(ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public ChessMap initializeChessGame() {
        final Player whitePlayer = new Player(new WhiteGenerator(), Team.WHITE);
        final Player blackPlayer = new Player(new BlackGenerator(), Team.BLACK);
        chessGameDao.initializeChessGame(whitePlayer, blackPlayer);
        chessGame = new ChessGame(whitePlayer, blackPlayer);
        return chessGame.createMap();
    }

    public StatusDto findStatus() {
        final List<GameResult> gameResult = chessGame.findGameResult();
        final GameResult whitePlayerResult = gameResult.get(0);
        final GameResult blackPlayerResult = gameResult.get(1);
        return StatusDto.of(whitePlayerResult, blackPlayerResult);
    }
}
