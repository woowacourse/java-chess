package chess.dao;

import chess.domain.ChessBoardPosition;
import chess.domain.piece.ChessPiece;
import chess.dto.ChessBoardDto;
import java.util.Map;

public interface BoardDao {

    void updateAll(ChessBoardDto chessBoardDto);

    ChessBoardDto findAll();
}
