package chess.dao;

import chess.domain.ChessBoard;
import java.util.Optional;

public interface ChessBoardDao {

    Optional<ChessBoard> find();

    void insert(ChessBoard chessBoard);

    void delete();

}
