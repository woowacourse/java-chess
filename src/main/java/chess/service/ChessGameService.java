package chess.service;

import chess.dao.ChessGameDao;
import chess.domain.ChessGame;
import chess.domain.GameResult;
import chess.domain.generator.BlackGenerator;
import chess.domain.generator.WhiteGenerator;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.domain.position.Position;
import chess.dto.ChessGameDto;
import chess.dto.ChessGameUpdateDto;
import chess.dto.StatusDto;
import java.util.List;

public class ChessGameService {

    private final ChessGameDao chessGameDao;

    public ChessGameService(ChessGameDao chessGameDao) {
        this.chessGameDao = chessGameDao;
    }

    public ChessGameDto createNewChessGame(final String gameName) {
        final boolean hasGameByName = chessGameDao.findChessGameIdByName(gameName).isPresent();
        if (hasGameByName) {
            throw new IllegalArgumentException("중복된 게임 이름입니다.");
        }
        final Player whitePlayer = new Player(new WhiteGenerator(), Team.WHITE);
        final Player blackPlayer = new Player(new BlackGenerator(), Team.BLACK);
        final ChessGame chessGame = new ChessGame(whitePlayer, blackPlayer);
        chessGameDao.createNewChessGame(chessGame, gameName);
        return ChessGameDto.of(chessGame, gameName);
    }

    public StatusDto findStatus(final String gameName) {
        final ChessGame chessGame = findGameByName(gameName);
        final List<GameResult> gameResult = chessGame.findGameResult();
        final GameResult whitePlayerResult = gameResult.get(0);
        final GameResult blackPlayerResult = gameResult.get(1);
        return StatusDto.of(whitePlayerResult, blackPlayerResult);
    }

    public StatusDto finishGame(final String gameName) {
        final StatusDto status = findStatus(gameName);
        final int gameId = findGameIdByGameName(gameName);
        chessGameDao.deletePieces(gameId);
        chessGameDao.deleteChessGame(gameId);
        return status;
    }

    public ChessGameDto loadChessGame(final String gameName) {
        final ChessGame chessGame = findGameByName(gameName);
        return ChessGameDto.of(chessGame, gameName);
    }

    public ChessGameDto move(final String gameName, final String current, final String destination) {
        final int gameId = findGameIdByGameName(gameName);
        final ChessGame chessGame = findGameByName(gameName);
        final Player currentPlayer = chessGame.getCurrentPlayer();
        final Player opponentPlayer = chessGame.getOpponentPlayer();
        chessGame.move(currentPlayer, opponentPlayer, new Position(current), new Position(destination));
        chessGameDao.updateGameTurn(gameId, chessGame.getTurn());
        chessGameDao.updatePiece(gameId, current, destination, currentPlayer.getTeamName(),
                opponentPlayer.getTeamName());
        return ChessGameDto.of(chessGame, gameName);
    }

    private ChessGame findGameByName(final String gameName) {
        final int chessGameId = findGameIdByGameName(gameName);
        final ChessGameUpdateDto gameUpdateDto = chessGameDao.findChessGame(chessGameId);
        return ChessGame.of(gameUpdateDto);
    }

    private int findGameIdByGameName(final String gameName) {
        return chessGameDao.findChessGameIdByName(gameName)
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 게임이 존재하지 않습니다."));
    }
}
