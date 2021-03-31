package chess.repository;

import chess.dao.ChessDAO;
import chess.dao.ChessDAOImpl;
import chess.domain.ChessGame;
import chess.domain.ChessGameImpl;
import chess.domain.Pieces;
import chess.domain.TeamColor;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessRepository {

    private final Map<Long, ChessGame> chessGames = new HashMap<>();
    private final ChessDAO chessDAO = new ChessDAOImpl();

    public ChessGame createGame(Long gameId) {
        return chessGames.computeIfAbsent(gameId,
            (id) -> {
                List<Piece> pieces = chessDAO.loadGame(id);
                if(pieces.size() == 0) {
                    pieces = PieceFactory.initialPieces(8,0,7);
                }
                return ChessGameImpl.from(Pieces.from(pieces), TeamColor.WHITE);
            }
        );
    }

    public void endGame(Long gameId) {
        chessGames.remove(gameId);
        chessDAO.removeGame(gameId);
    }

    public void restart(Long gameId) {
        chessGames.put(gameId, ChessGameImpl.initialGame());
    }

    public void save(Long gameId) {
        ChessGame chessGame = chessGames.get(gameId);
        chessDAO.saveGame(chessGame.pieces().asList(), gameId);
    }
}
