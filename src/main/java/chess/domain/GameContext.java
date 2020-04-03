package chess.domain;

import java.util.List;
import java.util.Map;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.player.Player;
import chess.dto.MoveRequestDto;

public interface GameContext {
    int addGame(Player white, Player black);

    boolean isEmpty();

    Game findGameById(int id);

    Map<Position, Piece> findBoardById(int id);

    void resetGameById(int id);

    void recoverMovesById(int id, List<MoveRequestDto> moves);

    void finishGameById(int id);

    double getScoreById(int id, Side side);

    Map<Integer, Map<Side, Player>> getPlayerContexts();

    Map<Integer, Map<Side, Double>> getScoreContexts();

    Map<Side, Double> getScore(int id);
}
