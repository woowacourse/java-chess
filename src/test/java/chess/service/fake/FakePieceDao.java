package chess.service.fake;

import chess.dao.PieceDao;
import chess.model.piece.Color;
import chess.model.piece.Piece;
import chess.model.square.File;
import java.util.List;

public class FakePieceDao implements PieceDao<Piece> {

    @Override
    public Piece save(Piece piece, int squareId) {
        return null;
    }

    @Override
    public Piece findBySquareId(int squareId) {
        return null;
    }

    @Override
    public int updatePieceSquareId(int originSquareId, int newSquareId) {
        return 0;
    }

    @Override
    public int deletePieceBySquareId(int squareId) {
        return 0;
    }

    @Override
    public List<Piece> getAllPiecesByBoardId(int boardId) {
        return null;
    }

    @Override
    public int countPawnsOnSameColumn(int roomId, File column, Color color) {
        return 0;
    }
}
