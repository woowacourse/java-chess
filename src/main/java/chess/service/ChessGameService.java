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

import static chess.domain.game.StateOfChessGame.RUNNING;

public class ChessGameService {

    private static final Color DEFAULT_TURN = Color.WHITE;
    private final ChessGameDao chessGameDao;
    private final MoveDao movementDao;

    public ChessGameService(final ChessGameDao chessGameDao, final MoveDao movementDao) {
        this.chessGameDao = chessGameDao;
        this.movementDao = movementDao;
    }

    //start : 이전 게임 id 불러오기 or 체스 게임 db에 새 게임 생성 및 id 반환 => 게임의 상태 moving으로 바꾼다
    public int start() {
        Optional<Integer> lastChessGameId = chessGameDao.findLastGameIdByStatus(RUNNING.name());
        int gameId = lastChessGameId.orElseGet(() ->
                chessGameDao.add(RUNNING.name(), DEFAULT_TURN.name()));
        chessGameDao.updateStatusByGameId(StateOfChessGame.MOVING.name(), gameId);
        return gameId;
    }

    // move : 게임 아이디로, 이동에서
    //todo :(리팩터링2) boardStrategy가 ChessBoard 반환 및 반환된 체스보드로 체스 게임 생성할 수 있도록 수정하기
    public void move(int gameId, List<String> commandLine, BoardStrategy boardStrategy) {
        ChessGame chessGame = findChessGameByGameId(gameId, boardStrategy);
        chessGame.move(commandLine);
        movementDao.add(gameId, Command.findStartSource(commandLine),Command.findTargetSource(commandLine));
        if (chessGame.isFinished()) {
            chessGameDao.updateStatusByGameId(chessGame.getStatus().name(), gameId);
            return;
        }
        chessGameDao.updateTurn(chessGame.getTurn().name(), gameId); //
    }

    //todo : (리팩터링3) gameid의 상태가 moving 인 경우만
    private ChessGame findChessGameByGameId(final int gameId, BoardStrategy boardStrategy) {
        List<List<String>> moveCommandLines = movementDao.findMovesByGameId(gameId);

        ChessGame chessGame = new ChessGame();
        chessGame.start(boardStrategy);
        moveCommandLines.forEach(chessGame::move);
        return chessGame;
    }

    public Map<Position, Piece> findChessBoard(int gameId, BoardStrategy boardStrategy) {
        ChessGame chessGame = findChessGameByGameId(gameId, boardStrategy);
        return chessGame.getChessBoard();
    }

    public Map<Color, Double> findStatus(int gameId, BoardStrategy boardStrategy) {
        if (!StateOfChessGame.MOVING.name().equals(chessGameDao.findGameStatusByGameId(gameId))) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다");
        }
        ChessGame chessGame = findChessGameByGameId(gameId,boardStrategy);
        return chessGame.status();
    }

    public void end(int gameId, BoardStrategy boardStrategy) {
        ChessGame chessGame = findChessGameByGameId(gameId, boardStrategy);
        chessGame.end();
        chessGameDao.updateStatusByGameId(RUNNING.name(), gameId); // running 상태로 업데이트
    }

    public boolean isFinished(int gameId, BoardStrategy boardStrategy) {
        ChessGame chessGame = findChessGameByGameId(gameId, boardStrategy);
        return chessGame.isFinished();
    }

    public Color findWinner(int gameId, BoardStrategy boardStrategy) {
        ChessGame chessGame = findChessGameByGameId(gameId, boardStrategy);
        return chessGame.findWinner();
    }

}

/**
 *

 // move : 게임 아이디로, 이동에서
 //todo :(리팩터링2) boardStrategy가 ChessBoard 반환 및 반환된 체스보드로 체스 게임 생성할 수 있도록 수정하기
 public void move(int gameId, CommandRequest commandRequest, BoardStrategy boardStrategy) {
 ChessGame chessGame = findChessGameByGameId(gameId, boardStrategy);
 chessGame.move(commandRequest.getCommandLine());
 movementDao.add(gameId, commandRequest.getStartPosition(),commandRequest.getEndPosition());
 if (chessGame.isFinished()) {
 chessGameDao.updateStatusByGameId(chessGame.getStatus().name(), gameId);
 return;
 }
 chessGameDao.updateTurn(chessGame.getTurn().name(), gameId); //
 }

 //todo : (리팩터링3) gameid의 상태가 moving 인 경우만
 private ChessGame findChessGameByGameId(final int gameId, BoardStrategy boardStrategy) {
 List<List<String>> moveCommandLines = movementDao.findMovesByGameId(gameId);

 ChessGame chessGame = new ChessGame();
 chessGame.start(boardStrategy);
 moveCommandLines.forEach(chessGame::move);
 return chessGame;
 }

 public Map<Position, Piece> findChessBoard(int gameId, BoardStrategy boardStrategy) {
 ChessGame chessGame = findChessGameByGameId(gameId, boardStrategy);
 return chessGame.getChessBoard();
 }

 public Map<Color, Double> findStatus(int gameId, BoardStrategy boardStrategy) {
 if (!StateOfChessGame.MOVING.name().equals(chessGameDao.findGameStatusByGameId(gameId))) {
 throw new IllegalArgumentException("게임이 시작되지 않았습니다");
 }
 ChessGame chessGame = findChessGameByGameId(gameId,boardStrategy);
 return chessGame.status();
 }

 public void end(int gameId, BoardStrategy boardStrategy) {
 ChessGame chessGame = findChessGameByGameId(gameId, boardStrategy);
 chessGame.end();
 chessGameDao.updateStatusByGameId(RUNNING.name(), gameId); // running 상태로 업데이트
 }

 public boolean isFinished(int gameId, BoardStrategy boardStrategy) {
 ChessGame chessGame = findChessGameByGameId(gameId, boardStrategy);
 return chessGame.isFinished();
 }

 public Color findWinner(int gameId, BoardStrategy boardStrategy) {
 ChessGame chessGame = findChessGameByGameId(gameId, boardStrategy);
 return chessGame.findWinner();
 }
 */

