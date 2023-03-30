package chess.dao;

import chess.domain.game.dto.LoadedPiecesInsertDto;
import chess.domain.game.dto.LoadedPiecesSelectDto;

public interface PiecesDao {
    LoadedPiecesSelectDto findAll();

    void insertAll(LoadedPiecesInsertDto piecesInsertDto);

    void delete();
}
