package chess.repository.dao;

import chess.domain.position.Position;
import chess.dto.PieceDto;
import java.util.List;

public interface PieceDao {

    List<PieceDto> findAllByChessGameId(final int chessGameId);

    void saveAll(final int chessGameId, final List<PieceDto> pieceDtos);

    void save(final int chessGameId, final PieceDto pieceDto);

    void move(final int chessGameId, final Position source, final Position target);

    void update(final int chessGameId, final Position source, final Position target);

    void delete(final int chessGameId, final Position position);
}
