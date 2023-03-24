package chess.repository;

import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.InitialPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.ChessGameDto;
import java.util.Map;
import java.util.Optional;

public class GameGenerationService {

    private final ChessGameDao gameDao;
    private final PieceDao pieceDao;

    public GameGenerationService(final ChessGameDao gameDao, final PieceDao pieceDao) {
        this.gameDao = gameDao;
        this.pieceDao = pieceDao;
    }

    public ChessGame createGame() {
        Optional<ChessGameDto> game = gameDao.findLastGame();
        return game.map(this::createGameFromDatabase)
            .orElseGet(this::createNewGame);
    }

    private ChessGame createGameFromDatabase(final ChessGameDto chessGameDto) {
        return ChessGame.fromDatabase(chessGameDto.getGameId(), chessGameDto.getTeamColor(),
            new ChessGameService(gameDao, pieceDao), pieceDao.findAllByGameId(chessGameDto.getGameId()));
    }

    private ChessGame createNewGame() {
        ChessGame newGame = new ChessGame(new ChessBoard(InitialPiece.getPiecesWithPosition()),
            new ChessGameService(gameDao, pieceDao));
        long gameId = gameDao.save(newGame);
        newGame.updateNewGameId(gameId);
        saveAllPieces(InitialPiece.getPiecesWithPosition(), gameId);
        return newGame;
    }

    private void saveAllPieces(final Map<Position, Piece> piecesByPosition, final long gameId) {
        pieceDao.save(piecesByPosition, gameId);
    }

}
