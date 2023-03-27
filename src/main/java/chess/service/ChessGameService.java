package chess.service;

import chess.boardstrategy.BoardStrategy;
import chess.controller.Command;
import chess.dao.ChessGameDao;
import chess.dao.MoveDao;
import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.domain.game.StateOfChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.type.Piece;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static chess.domain.game.StateOfChessGame.*;

public class ChessGameService {

    private static final Color DEFAULT_TURN = Color.WHITE;
    private final ChessGameDao chessGameDao;
    private final MoveDao movementDao;

    public ChessGameService(final ChessGameDao chessGameDao, final MoveDao movementDao) {
        this.chessGameDao = chessGameDao;
        this.movementDao = movementDao;
    }
    private static final int NONE_GAME_ID = -1;
    private int gameId = NONE_GAME_ID;

    public boolean isRunning() {
        Optional<Integer> lastChessGameId = chessGameDao.findLastGameId();
        int gameId = lastChessGameId.orElseThrow(() -> new IllegalArgumentException("게임이 시작되지 않았습니다"));
        String status = chessGameDao.findGameStatusByGameId(gameId);
        return !FINISHED.isSameStateWith(status) && !PAUSED.isSameStateWith(status);
    }

    public int start() {
        if(gameId != NONE_GAME_ID) {
            throw new IllegalArgumentException("게임이 이미 시작되었습니다");
        }
        Optional<Integer> lastChessGameId = chessGameDao.findLastGameId();
        gameId = lastChessGameId.orElseGet(() ->
                chessGameDao.add(STARTED.name(), DEFAULT_TURN.name()));
        if (FINISHED.isSameStateWith(chessGameDao.findGameStatusByGameId(gameId))) {
            gameId = chessGameDao.add(STARTED.name(), DEFAULT_TURN.name());
        }
        chessGameDao.updateStatusByGameId(StateOfChessGame.MOVING.name(), gameId);
        return gameId;
    }

    public void move(List<String> commandLine, BoardStrategy boardStrategy) {
        validateGameStarted();
        ChessGame chessGame = loadChessGameByGameId(boardStrategy);
        chessGame.move(commandLine);
        movementDao.add(gameId, Command.findStartSource(commandLine), Command.findTargetSource(commandLine));
        if (chessGame.isFinished()) {
            chessGameDao.updateStatusByGameId(chessGame.getStatus().name(), gameId);
            return;
        }
        chessGameDao.updateTurn(chessGame.getTurn().name(), gameId);
    }

    private void validateGameStarted() {
        if (gameId == NONE_GAME_ID) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다");
        }
        String status = chessGameDao.findGameStatusByGameId(gameId);
        if (!MOVING.isSameStateWith(status)) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다");
        }
    }

    public Map<Position, Piece> findChessBoard(BoardStrategy boardStrategy) {
        ChessGame chessGame = loadChessGameByGameId(boardStrategy);
        return chessGame.getChessBoard();
    }

    private ChessGame loadChessGameByGameId(BoardStrategy boardStrategy) {
        List<List<String>> moveCommandLines = movementDao.findMovesByGameId(gameId);

        ChessGame chessGame = new ChessGame();
        chessGame.start(boardStrategy);
        moveCommandLines.forEach(chessGame::move);
        return chessGame;
    }
    public Map<Color, Double> findStatus(BoardStrategy boardStrategy) {
        validateGameStarted();
        ChessGame chessGame = loadChessGameByGameId(boardStrategy);
        return chessGame.status();
    }

    public void end() {
        validateGameStarted();
        chessGameDao.updateStatusByGameId(PAUSED.name(), gameId);
    }

    public Color findWinner(BoardStrategy boardStrategy) {
        validateGameIsFinished();
        ChessGame chessGame = loadChessGameByGameId(boardStrategy);
        return chessGame.findWinner();
    }

    private void validateGameIsFinished() {
        if (gameId == NONE_GAME_ID) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다");
        }
        String status = chessGameDao.findGameStatusByGameId(gameId);
        if (!FINISHED.isSameStateWith(status)) {
            throw new IllegalArgumentException("게임이 종료되지 않았습니다");
        }
    }

    public boolean isFinished() {
        Optional<Integer> lastChessGameId = chessGameDao.findLastGameId();
        int gameId = lastChessGameId.orElseThrow(() -> new IllegalArgumentException("게임이 시작되지 않았습니다"));
        String status = chessGameDao.findGameStatusByGameId(gameId);
        return FINISHED.isSameStateWith(status);
    }
}