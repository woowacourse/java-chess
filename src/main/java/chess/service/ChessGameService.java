package chess.service;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.piece.ChessmenInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.Pieces;
import chess.dto.GameResultDto;
import chess.dto.MovePositionCommandDto;
import chess.dto.PiecesDto;
import java.util.List;

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

    public PiecesDto createOrGet() {
        if (!gameDao.findById(gameId)) {
            createGame();
            return new PiecesDto(new Pieces(List.of()));
        }
        boolean forceEndFlag = gameDao.findForceEndFlagById(gameId);
        Color turn = gameDao.findTurnById(gameId);
        Pieces chessmen = pieceDao.findAllByGameId(gameId);

        game = new ChessGame(forceEndFlag, chessmen, turn);
        return new PiecesDto(chessmen);
    }

    private void createGame() {
        game = new ChessGame();
    }

    public PiecesDto getCurrentGame() {
        return new PiecesDto(game.getChessmen());
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
        pieceDao.saveAllByGameId(chessmen.getPieces(), gameId);

        game = ChessGame.of(chessmen);
    }

    public void move(String from, String to) {
        game.moveChessmen(new MovePositionCommandDto(from, to));
    }

    public void save() {
        Pieces chessmen  = game.getChessmen();
        Color turn = game.getTurn();
        boolean forceEndFlag = game.getForceEndFlag();

        pieceDao.deleteAllByGameId(gameId);
        pieceDao.saveAllByGameId(chessmen.getPieces(), gameId);
        gameDao.updateTurnById(turn, gameId);
        gameDao.updateForceEndFlagById(forceEndFlag, gameId);
    }

    public void forceEnd() {
        game.forceEnd();
        save();
    }

}
