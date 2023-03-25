package chess.dao;

import chess.dao.dto.PieceDto;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Optional;

public interface PieceDao {

    void save(final Long chessGameId, final Square square, final Piece piece);

    Optional<PieceDto> findBySquare(final Long chessGameId, final Square square);

    List<PieceDto> findAllByChessGameId(final Long chessGameId);

    void update(final Long chessGameId, final Square square, final Piece piece);

    void delete(final Long chessGameId, final Square square);
}
