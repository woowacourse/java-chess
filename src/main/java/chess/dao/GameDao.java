package chess.dao;

import chess.domain.ChessGame;
import java.util.List;
import java.util.Optional;

public interface GameDao {

    Long save(ChessGame game);

    Optional<ChessGame> findById(Long id);

    List<ChessGame> findAll();

    List<ChessGame> findHistorysByMemberId(Long memberId);

    void update(ChessGame game);
}
