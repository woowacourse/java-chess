package chess.repository.piece;

import chess.domain.dto.PieceDto;
import chess.domain.piece.Piece;
import java.sql.SQLException;
import java.util.List;

public interface PieceRepository {

    long insert(long roomId, Piece piece) throws SQLException;

    void update(long pieceId, Piece piece) throws SQLException;

    // deletePieceByLocation(String location)

    void deleteAll() throws SQLException;

    int count() throws SQLException;

    PieceDto findPieceById(long pieceId) throws SQLException;

    List<PieceDto> findPiecesByRoomId(long roomId) throws SQLException;
}
