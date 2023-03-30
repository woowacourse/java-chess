package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.InitialPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.ChessGameDto;
import java.util.Map;
import java.util.Optional;

public class ChessService {

    private final ChessGameDao gameDao;
    private final PieceDao pieceDao;

    public ChessService(final ChessGameDao gameDao, final PieceDao pieceDao) {
        this.gameDao = gameDao;
        this.pieceDao = pieceDao;
    }

    public ChessGame loadOrCreateGame() {
        Optional<ChessGameDto> game = gameDao.findLastGame();
        return game.map(this::loadSavedGame)
            .orElseGet(this::createNewGame);
    }

    private ChessGame loadSavedGame(final ChessGameDto chessGameDto) {
        return ChessGame.of(chessGameDto.getGameId(), chessGameDto.getTeamColor(),
            pieceDao.findAllByGameId(chessGameDto.getGameId()));
    }

    private ChessGame createNewGame() {
        ChessGame newGame = new ChessGame(new ChessBoard(InitialPiece.getPiecesWithPosition()));
        long gameId = gameDao.save(newGame);
        newGame.updateNewGameId(gameId);
        saveAllPieces(InitialPiece.getPiecesWithPosition(), gameId);
        return newGame;
    }

    private void saveAllPieces(final Map<Position, Piece> piecesByPosition, final long gameId) {
        pieceDao.save(piecesByPosition, gameId);
    }

    public void move(final ChessGame chessGame, final Position source, final Position dest) {
        chessGame.move(source, dest);
        updateMovement(source, dest, chessGame.getGameId());
        updateGameStatus(chessGame);
    }

    private void updateMovement(final Position source, final Position dest, final long gameId) {
        pieceDao.deleteByPositionAndGameId(dest, gameId);
        pieceDao.updatePositionByPositionAndGameId(source, gameId, dest);
    }

    private void updateGameStatus(final ChessGame chessGame) {
        gameDao.updateTurn(chessGame.getGameId(), chessGame.getTeamColor());
        if (chessGame.isEnd()) {
            updateGameStatusEnd(chessGame.getGameId());
        }
    }

    private void updateGameStatusEnd(final long gameId) {
        gameDao.updateStatus(gameId, true);
    }

}
