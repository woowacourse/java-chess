package chess.service;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameFactory;
import chess.domain.game.GameResult;
import chess.domain.game.Turn;
import chess.domain.position.Position;
import chess.dto.ChessGameDto;
import chess.dto.PieceDto;
import chess.repository.dao.ChessGameDao;
import chess.repository.dao.PieceDao;
import java.util.List;

public class ChessService {

    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;

    public ChessService(final ChessGameDao chessGameDao, final PieceDao pieceDao) {
        this.chessGameDao = chessGameDao;
        this.pieceDao = pieceDao;
    }

    public ChessGame newGame() {
        final Board board = BoardFactory.generateBoard();
        ChessGame chessGame = new ChessGame(board, Turn.WHITE);
        chessGameDao.save(chessGame);

        final int chessGameId = chessGameDao.findLastInsertId();
        chessGame.setId(chessGameId);
        pieceDao.saveAll(chessGameId, PieceDto.from(board));

        return chessGame;
    }

    public ChessGame enterGame(final int chessGameId) {
        final ChessGameDto chessGameDto = chessGameDao.findById(chessGameId);
        final List<PieceDto> pieceDtos = pieceDao.findAllByChessGameId(chessGameId);

        return ChessGameFactory.generateChessGame(chessGameDto, pieceDtos);
    }

    public void startGame(final ChessGame chessGame) {
        chessGame.start();
    }

    public void endGame(final ChessGame chessGame) {
        chessGame.end();
    }

    public void move(final ChessGame chessGame, final Position source, final Position target) {
        chessGame.movePiece(source, target);
        pieceDao.delete(chessGame.getId(), target);
        pieceDao.update(chessGame.getId(), source, target);
        chessGameDao.update(chessGame);
    }

    public GameResult getGameResult(final ChessGame chessGame) {
        return chessGame.getGameResult();
    }

    public Board getBoard(final ChessGame chessGame) {
        return chessGame.getBoard();
    }

    public List<ChessGameDto> findAllChessGame() {
        return chessGameDao.findAll();
    }

    public boolean isRunnable(final ChessGame chessGame) {
        return chessGame.isRunnable();
    }
}
