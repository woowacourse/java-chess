package chess.dao;

import java.util.HashMap;
import java.util.Map;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.board.CustomBoardGenerator;
import chess.domain.board.Point;
import chess.domain.board.Route;
import chess.domain.piece.Piece;

public class JdbcBoardDao implements BoardDao {

    @Override
    public void saveBoard(Map<Point, Piece> pointPieces, String roomName) {
        JdbcConnector connector = JdbcConnector.query(
            "insert into board (horizontal_index, vertical_index, piece_type, piece_color, room_name)"
                + " values (?, ?, ?, ?, ?)");

        for (Map.Entry<Point, Piece> entry : pointPieces.entrySet()) {
            Point point = entry.getKey();
            Piece piece = entry.getValue();
            connector = connector
                .parameters(point.getHorizontal(), point.getVertical())
                .parameters(piece.getType(), piece.getColor(), roomName)
                .batch();
        }
        connector.executeBatch();
    }

    @Override
    public Board readBoard(String roomName) {
        JdbcConnector.ResultSetHolder holder = JdbcConnector.query("select * from board where room_name = ?")
            .parameters(roomName)
            .executeQuery();

        Map<Point, Piece> pointPieces = new HashMap<>();
        while (holder.next()) {
            Point point = Point.of(
                holder.getInteger("horizontal_index"), holder.getInteger("vertical_index")
            );
            Piece piece = PieceCache.getPiece(
                holder.getString("piece_type"), Color.of(holder.getString("piece_color"))
            );
            pointPieces.put(point, piece);
        }
        validateExist(pointPieces, roomName);
        BoardGenerator generator = new CustomBoardGenerator(pointPieces);
        return Board.of(generator);
    }

    private void validateExist(Map<Point, Piece> pointPieces, String roomName) {
        if (pointPieces.size() == 0) {
            throw new IllegalArgumentException(
                String.format("[ERROR] %s에 해당하는 이름의 보드가 없습니다.", roomName)
            );
        }
    }

    @Override
    public void deletePiece(Point point, String roomName) {
        JdbcConnector.query("DELETE FROM board WHERE horizontal_index = ? and vertical_index = ? and room_name = ?")
            .parameters(point.getHorizontal(), point.getVertical())
            .parameters(roomName)
            .executeUpdate();
    }

    @Override
    public void updatePiece(Route route, String roomName) {
        Point source = route.getSource();
        Point destination = route.getDestination();
        JdbcConnector.query("update board set horizontal_index = ?, vertical_index = ? "
                + "where horizontal_index = ? and vertical_index = ? and room_name = ?")
            .parameters(destination.getHorizontal(), destination.getVertical(),
                source.getHorizontal(), source.getVertical())
            .parameters(roomName)
            .executeUpdate();
    }
}
