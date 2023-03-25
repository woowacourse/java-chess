package chess.dao;

import chess.dao.dto.PieceDto;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.List;
import java.util.Optional;

public interface PieceDao {

    void save(final Long chessGameId, final Square square, final Piece piece);

    Optional<PieceDto> findByFileAndRank(final Long chessGameId, final File file, final Rank rank);

    List<PieceDto> findAllByChessGameId(final Long chessGameId);

    void update(final Long id, final Color color, final PieceType pieceType);

    void delete(final Long id);
}
