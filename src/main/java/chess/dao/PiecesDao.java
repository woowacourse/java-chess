package chess.dao;

import chess.domain.game.dto.LoadedPiecesInsertDto;
import chess.domain.game.dto.LoadedPiecesSelectDto;
import java.sql.Connection;

public interface PiecesDao {
    Connection getConnection();

    LoadedPiecesSelectDto findAll();

    void insertAll(LoadedPiecesInsertDto piecesInsertDto);

    void delete();
}
