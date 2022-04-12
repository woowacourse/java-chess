package chess.service;

import chess.dao.ChessGameDao;
import chess.domain.ChessGame;
import chess.domain.generator.BlackGenerator;
import chess.domain.generator.WhiteGenerator;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.dto.ChessGameDto;

public class ChessGameService {

    private final ChessGameDao chessGameDao;

    public ChessGameService(ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public ChessGameDto createNewChessGame(final String gameName) {
        final Player whitePlayer = new Player(new WhiteGenerator(), Team.WHITE);
        final Player blackPlayer = new Player(new BlackGenerator(), Team.BLACK);
        final ChessGame chessGame = new ChessGame(whitePlayer, blackPlayer);
        chessGameDao.createNewChessGame(chessGame, gameName);
        return ChessGameDto.of(chessGame, gameName);
    }
}
