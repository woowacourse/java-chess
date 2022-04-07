package chess.dao;

import java.util.HashMap;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.CustomBoardGenerator;
import chess.domain.board.Point;
import chess.domain.board.Route;
import chess.domain.piece.Piece;

public class FakeBoardDao implements BoardDao {

    private final Map<String, Map<Point, Piece>> memoryDatabase;

    public FakeBoardDao() {
        this.memoryDatabase = new HashMap<>();
    }

    @Override
    public void saveBoard(Map<Point, Piece> pointPieces, String roomName) {
        this.memoryDatabase.put(roomName, new HashMap<>(pointPieces));
    }

    @Override
    public Board readBoard(String roomName) {
        Map<Point, Piece> pointPieces = memoryDatabase.get(roomName);
        return Board.of(new CustomBoardGenerator(pointPieces));
    }

    @Override
    public void deletePiece(Point destination, String roomName) {
        Map<Point, Piece> pointPieces = memoryDatabase.get(roomName);
        pointPieces.remove(destination);
    }

    @Override
    public void updatePiece(Route route, String roomName) {
        Map<Point, Piece> pointPieces = memoryDatabase.get(roomName);
        Piece piece = pointPieces.get(route.getSource());
        pointPieces.remove(route.getSource());
        pointPieces.put(route.getDestination(), piece);
    }
}
