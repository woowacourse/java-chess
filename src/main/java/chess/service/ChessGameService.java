package chess.service;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.command.MoveCommand;
import chess.domain.game.ChessGame;
import chess.domain.game.GameResult;
import chess.domain.piece.ChessmenInitializer;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.dto.GameResultDto;
import chess.dto.MoveCommandDto;
import chess.dto.PieceDto;
import chess.dto.PiecesDto;
import java.util.ArrayList;
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
            return new PiecesDto(List.of());
        }
        boolean forceEndFlag = gameDao.findForceEndFlagById(gameId);
        Color turn = gameDao.findTurnById(gameId);
        Pieces chessmen = pieceDao.findAllByGameId(gameId);

        game = new ChessGame(forceEndFlag, chessmen, turn);

        return new PiecesDto(toDto(game.getChessmen()));
    }

    private void createGame() {
        game = new ChessGame();
    }

    private List<PieceDto> toDto(Pieces chessmen) {
        List<PieceDto> pieces = new ArrayList<>();
        for (Piece piece : chessmen.getPieces()) {
            pieces.add(new PieceDto(piece.getPosition().getPosition(),
                piece.getColor().getName(),
                piece.getName()));
        }
        return pieces;
    }

    public PiecesDto getCurrentGame() {
        return new PiecesDto(toDto(game.getChessmen()));
    }

    public GameResultDto calculateGameResult() {
        GameResult gameResult = GameResult.calculate(game.getChessmen());
        return new GameResultDto(gameResult.getWinner().getName(),
            gameResult.getWhiteScore(),
            gameResult.getBlackScore());
    }

    public void cleanGame() {
        pieceDao.deleteAllByGameId(gameId);
        gameDao.deleteById(gameId);
        game.clean();
    }

    public void initGame() {
        gameDao.saveById(gameId);
        Pieces chessmen = chessmenInitializer.init();
        pieceDao.saveAllByGameId(chessmen.getPieces(), gameId);

        game = ChessGame.of(chessmen);
    }

    public void move(MoveCommandDto moveCommandDto) {
        String from = moveCommandDto.getSource();
        String to = moveCommandDto.getTarget();
        game.moveChessmen(new MoveCommand(from, to));
    }

    public void save() {
        Pieces chessmen = game.getChessmen();
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
