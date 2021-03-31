package chess.dao;

import chess.domain.piece.Piece;
import java.util.List;

public interface ChessDAO {

    Long saveGame(List<Piece> pieces, Long gameId);

    List<Piece> loadGame(Long gameId);

    void removeGame(Long gameId);
}