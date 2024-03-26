package service;

import domain.ChessGameResult;
import domain.Team;
import domain.chessboard.ChessBoard;
import domain.piece.Piece;
import domain.square.Square;
import repository.ChessBoardDao;
import repository.ChessGameDao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessGameService {

    private final ChessBoardDao chessBoardDao;
    private final ChessGameDao chessGameDao;

    public ChessGameService(final Connection connection) {
        this.chessBoardDao = new ChessBoardDao(connection);
        this.chessGameDao = new ChessGameDao(connection);
    }

    public int createNewGame() {
        final int id = chessGameDao.addGame(Team.WHITE, ChessGameStatus.RUNNING);

        final ChessBoard chessBoard = ChessBoard.create();

        final Map<Square, Piece> pieceSquares = chessBoard.getPieceSquares();
        chessBoardDao.addAll(pieceSquares, id);

        return id;
    }

    public void move(final int gameId, final Square source, final Square target) {
        final ChessBoard chessBoard = createChessBoard(gameId);

        chessBoard.move(source, target);

        updateChessBoardDao(gameId, source, target);

        chessGameDao.updateCurrentTeam(gameId, chessBoard.currentTeam());

        if (isKingDead(gameId)) {
            endGame(gameId);
        }
    }

    private ChessBoard createChessBoard(final int gameId) {
        final Map<Square, Piece> pieceSquares = chessBoardDao.findAll(gameId);
        final Team currentTeam = chessGameDao.findCurrentTeamById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("진행 중인 게임이 없습니다."));

        return new ChessBoard(pieceSquares, currentTeam);
    }

    private void updateChessBoardDao(final int gameId, final Square source, final Square target) {
        final Piece piece = chessBoardDao.findBySquare(source, gameId)
                .orElseThrow(() -> new IllegalArgumentException("Source에 기물이 없습니다."));

        final Optional<Piece> targetPiece = chessBoardDao.findBySquare(target, gameId);
        if (targetPiece.isEmpty()) {
            chessBoardDao.addSquarePiece(target, piece, gameId);
        } else {
            chessBoardDao.update(target, piece, gameId);
        }

        chessBoardDao.deleteBySquare(source, gameId);
    }

    public void endGame(final int gameId) {
        chessGameDao.updateStatusById(gameId, ChessGameStatus.END);
    }

    public ChessGameResult calculateResult(final int gameId) {
        final Map<Square, Piece> pieceSquares = chessBoardDao.findAll(gameId);

        return ChessGameResult.from(pieceSquares);
    }

    public List<Integer> findRunningGame() {
        final List<Integer> games = chessGameDao.findRunningGames();
        if (games.isEmpty()) {
            throw new IllegalArgumentException("진행중인 게임이 없습니다.");
        }
        return games;
    }

    public boolean containRunningGame(final int gameId) {
        return chessGameDao.existRunningById(gameId);
    }

    public Team currentTeam(final int gameId) {
        return chessGameDao.findCurrentTeamById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("게임을 찾을 수 없습니다."));
    }

    public boolean isNotEnd(final int gameId) {
        final ChessGameStatus chessGameStatus = chessGameDao.findStatus(gameId)
                .orElseThrow(() -> new IllegalArgumentException("게임을 찾을 수 없습니다."));

        return chessGameStatus == ChessGameStatus.RUNNING;
    }

    public boolean isKingDead(final int gameId) {
        return chessBoardDao.countKing(gameId) < 2;
    }

    public Map<Square, Piece> getPieceSquares(final int gameId) {
        return chessBoardDao.findAll(gameId);
    }
}
