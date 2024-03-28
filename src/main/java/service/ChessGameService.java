package service;

import domain.ChessGameStatus;
import domain.Team;
import domain.chessboard.ChessBoard;
import domain.piece.Piece;
import domain.player.Player;
import domain.player.PlayerName;
import domain.square.Square;
import repository.ChessBoardDao;
import repository.ChessGameDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ChessGameService {

    private final Connection connection;
    private final ChessGameDao chessGameDao;
    private final ChessBoardDao chessBoardDao;

    public ChessGameService(final Connection connection) {
        this.connection = connection;
        this.chessGameDao = new ChessGameDao(connection);
        this.chessBoardDao = new ChessBoardDao(connection);
    }

    public int createNewGame(final Player blackPlayer, final Player whitePlayer) throws SQLException {
        try {
            connection.setAutoCommit(false);

            final int id = chessGameDao.addGame(blackPlayer, whitePlayer, Team.WHITE, ChessGameStatus.RUNNING);

            final ChessBoard chessBoard = ChessBoard.create();
            final Map<Square, Piece> pieceSquares = chessBoard.getPieceSquares();
            chessBoardDao.addAll(pieceSquares, id);

            connection.commit();
            return id;
        } catch (final Exception e) {
            connection.rollback();
            throw new IllegalArgumentException(e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public List<Integer> findRunningGame() {
        final List<Integer> games = chessGameDao.findRunningGames();
        if (games.isEmpty()) {
            throw new IllegalArgumentException("진행중인 게임이 없습니다.");
        }
        return games;
    }

    public PlayerName findPlayerName(final int gameId, final Team team) {
        return chessGameDao.findPlayerName(gameId, team)
                .orElseThrow(() -> new IllegalArgumentException("플레이어를 찾을 수 없습니다."));
    }

    public Team findCurrentTeam(final int gameId) {
        return chessGameDao.findCurrentTeamById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("게임을 찾을 수 없습니다."));
    }

    public void endGame(final int gameId) {
        chessGameDao.updateStatusById(gameId, ChessGameStatus.END);
    }

    public boolean containRunningGame(final int gameId) {
        return chessGameDao.existRunningById(gameId);
    }

    public boolean isEnd(final int gameId) {
        final ChessGameStatus chessGameStatus = chessGameDao.findStatusById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("게임을 찾을 수 없습니다."));

        return chessGameStatus == ChessGameStatus.END;
    }

    public boolean isNotEnd(final int gameId) {
        return !isEnd(gameId);
    }
}
