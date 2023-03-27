package chess.dao;

import chess.domain.game.dto.LoadedPiecesInsertDto;
import chess.domain.game.dto.LoadedPiecesSelectDto;
import java.sql.Connection;
import java.util.List;

public interface PiecesDao {
    Connection getConnection();

    LoadedPiecesSelectDto findAll();

    void insertAll(List<Piece> pieces);

    void delete();
}
