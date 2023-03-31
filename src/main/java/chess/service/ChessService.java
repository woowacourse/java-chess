package chess.service;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.game.Game;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.dto.GameDto;
import chess.dto.PiecesDto;
import chess.exception.ErrorCode;
import chess.exception.GameIdNotFoundException;
import java.util.Map;
import java.util.Optional;

public class ChessService {
    private final GameDao gameDao;
    private final PieceDao pieceDao;

    public ChessService(GameDao gameDao, PieceDao pieceDao) {
        this.gameDao = gameDao;
        this.pieceDao = pieceDao;
    }

    public Optional<Game> makeGame() {
        if (gameDao.select().isEmpty()) {
            return Optional.empty();
        }
        GameDto gameDto = gameDao.select().get();
        PiecesDto piecesDto = pieceDao.select(gameDto.getGameId());
        Map<Square, Piece> squarePieceMap = piecesDto.generateBoard();
        Board board = new Board(squarePieceMap);
        return Optional.of(new Game(board, Team.valueOf(gameDto.getTurn())));
    }

    public void save(Game game) {
        gameDao.save(game);
        Optional<GameDto> gameDto = gameDao.select();
        Optional<Integer> gameId = gameDto.map(GameDto::getGameId);
        pieceDao.save(game, gameId.orElseThrow(() -> new GameIdNotFoundException(ErrorCode.GAME_ID_NOT_FOUND)));
    }

    public void delete() {
        pieceDao.delete();
        gameDao.delete();
    }
}
