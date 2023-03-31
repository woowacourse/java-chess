package chess.service;

import chess.boardstrategy.BoardStrategy;
import chess.dao.ChessGameDao;
import chess.dao.MoveDao;
import chess.domain.board.ChessBoard;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.game.ChessGame;
import chess.domain.game.StateOfChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.type.Piece;

import java.util.List;
import java.util.Map;

public class ChessGameService {
    private final BoardStrategy boardStrategy;
    private final ChessGameDao chessGameDao;
    private final MoveDao moveDao;

    public ChessGameService(final ChessGameDao chessGameDao, final MoveDao movementDao, BoardStrategy boardStrategy) {
        this.boardStrategy = boardStrategy;
        this.chessGameDao = chessGameDao;
        this.moveDao = movementDao;
    }

    public void create() {
        if(chessGameDao.findGameId().isPresent()){
            chessGameDao.deleteGame();
            moveDao.deleteAllMoves();
        }
        chessGameDao.save(StateOfChessGame.STARTED, Color.WHITE);
    }

    public void start() {
        findStatusOfGame();
        chessGameDao.updateStatus(StateOfChessGame.STARTED);
    }

    private StateOfChessGame findStatusOfGame() {
        int gameId = chessGameDao.findGameId()
                .orElseThrow(() -> new IllegalStateException("게임이 존재하지 않습니다"));
        return chessGameDao.findStatusByGameId(gameId)
                .orElseThrow(() -> new IllegalStateException("게임이 존재하지 않습니다"));
    }

    public Map<Position, Piece> findChessBoard() {
        ChessGame chessGame = loadChessGame();
        return chessGame.getChessBoard();
    }

    private ChessGame loadChessGame() {
        List<List<String>> moves = moveDao.findMoves();
        ChessGame chessGame = new ChessGame(new ChessBoard(boardStrategy.generate()));
        moves.forEach(move -> chessGame
                .move(convertPosition(move.get(0)), convertPosition(move.get(1))));
        return chessGame;
    }

    private Position convertPosition(String position) {
        List<String> columnAndRank = List.of(position.split(""));
        Column column = Column.findColumnByValue(columnAndRank.get(0));
        Rank rank = Rank.findRankByValue(columnAndRank.get(1));

        return Position.of(column, rank);
    }

    public void move(String startPosition, String endPosition) {
        checkGameStarted();
        ChessGame chessGame = loadChessGame();
        chessGame.move(convertPosition(startPosition), convertPosition(endPosition));
        moveDao.save(startPosition, endPosition);
        chessGameDao.update(chessGame.getStatus(), chessGame.getTurn());
    }

    private void checkGameStarted() {
        StateOfChessGame status = findStatusOfGame();
        if(status != StateOfChessGame.STARTED) {
            throw new IllegalStateException("게임이 시작되지 않았습니다");
        }
    }

    public Map<Color, Double> findStatus() {
        checkGameStarted();
        ChessGame chessGame = loadChessGame();
        return chessGame.status();
    }

    public void end() {
        checkGameStarted();
        chessGameDao.updateStatus(StateOfChessGame.PAUSED);
    }

    public boolean isFinished() {
        ChessGame chessGame = loadChessGame();
        return chessGame.getStatus() == StateOfChessGame.FINISHED;
    }
}
