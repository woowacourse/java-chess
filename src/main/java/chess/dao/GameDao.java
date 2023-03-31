package chess.dao;

import java.util.Map;

import chess.domain.game.Game;
import chess.domain.game.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public interface GameDao {
    Integer save(Game game);

    void put(Integer gameId, Game game);

    Map<Position, Piece> findPiecesBy(Integer gameId);

    Team findTurnBy(Integer gameId);

    void end(Integer gameId);

    boolean hasUnfinished();

    Integer findIdOfLastUnfinished();
}
