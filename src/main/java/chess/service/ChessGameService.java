package chess.service;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.ChessGame;
import chess.domain.piece.ChessmenInitializer;
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
        game = ChessGame.createOrGet(String.valueOf(gameId));
        return game.toBoard(gameId);
    }

    public BoardMapDto getCurrentGame() {
        return game.toBoard(gameId);
    }

    public GameResultDto calculateGameResult() {
        return game.calculateGameResult();
    }


    public void cleanGame() {
        game.clean(gameId);
    }

    public void initGame() {
        game = ChessGame.of(chessmenInitializer.init(), gameId);
    }

    public void move(String from, String to) {
        game.moveChessmen(new MovePositionCommandDto(from, to));
    }

    public void forceEnd() {
        game.forceEnd(gameId);
    }
}
