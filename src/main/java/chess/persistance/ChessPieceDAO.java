package chess.persistance;

import chess.model.AbstractChessPiece;
import chess.model.Point;

import java.util.Map;

public interface ChessPieceDAO {
    static ChessPieceDAO getInstance() {
        return ChessPieceDAOImpl.getInstance();
    }

    int countPieces(int gameId);

    void addPiece(Point point, AbstractChessPiece piece, int gameId);

    void addPieces(Map<Point, AbstractChessPiece> board, int gameId);

    void removePiece(Point point, int gameId);

    void updatePiece(Point source, Point target, int gameId);

    Map<Point, AbstractChessPiece> getAll(int gameId);
}
