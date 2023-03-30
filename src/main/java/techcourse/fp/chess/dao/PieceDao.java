package techcourse.fp.chess.dao;

import java.util.Map;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.piece.Piece;

//TODO: 테이블 명 및 클래스 명 변경 고민 board Dao
public interface PieceDao {

    void save(final Map<Position, Piece> board, final int chessGameId);

    Map<Position, Piece> findByGameId(final int chessGameId);
}
