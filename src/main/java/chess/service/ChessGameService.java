package chess.service;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.piece.ChessmenInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.Pieces;
import chess.dto.BoardMapDto;
import chess.dto.GameResultDto;
import chess.dto.MovePositionCommandDto;

public class ChessGameService {

    private final ChessmenInitializer chessmenInitializer = new ChessmenInitializer();

    private final PieceDao pieceDao;
    private final GameDao gameDao;

    private final String gameId;
    private ChessGame game;

    public ChessGameService(String gameId) {
        this.pieceDao = new PieceDao();
        this.gameDao = new GameDao();
        this.gameId = gameId;
    }

    public BoardMapDto createOrGet() {
        if (!gameDao.findById(gameId)) {
            createGame();
            return game.toBoard();
        }
        boolean forceEndFlag = gameDao.findForceEndFlagById(gameId);
        Pieces chessmen = pieceDao.findAllByGameId(gameId);
        game = new ChessGame(forceEndFlag, chessmen);
        return game.toBoard();
    }

    private void createGame() {
        game = new ChessGame();
    }

    public BoardMapDto getCurrentGame() {
        return game.toBoard();
    }

    public GameResultDto calculateGameResult() {
        return game.calculateGameResult();
    }


    public void cleanGame() {
        pieceDao.deleteAllByGameId(gameId);
        gameDao.deleteById(gameId);
        game.clean();
    }

    public void initGame() {
        gameDao.create(gameId);
        Pieces chessmen = chessmenInitializer.init();
        pieceDao.saveAll(chessmen.getPieces(), gameId);

        game = ChessGame.of(chessmen);
    }

    public void move(String from, String to) {
        Color turn = gameDao.findTurnById(gameId);

        game.moveChessmen(new MovePositionCommandDto(from, to), turn);
        gameDao.updateTurnById(gameId, turn.nextTurn());
    }

    public void forceEnd() {
        boolean forceEndFlag = game.forceEnd(gameId);
        gameDao.updateForceEndFlagById(forceEndFlag, gameId);

    }
}
