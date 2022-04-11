package chess.database.dao;

import java.util.HashMap;
import java.util.Map;

import chess.database.dto.BoardDto;
import chess.database.dto.PieceDto;
import chess.database.dto.PointDto;
import chess.database.dto.RouteDto;

public class JdbcBoardDao implements BoardDao {

    @Override
    public void saveBoard(BoardDto boardDto, String roomName) {
        JdbcConnector connector = JdbcConnector.query(
            "insert into board (horizontal_index, vertical_index, piece_type, piece_color, room_name)"
                + " values (?, ?, ?, ?, ?)");

        Map<PointDto, PieceDto> pointPieceDto = boardDto.getPointPieces();
        for (Map.Entry<PointDto, PieceDto> entry : pointPieceDto.entrySet()) {
            PointDto point = entry.getKey();
            PieceDto piece = entry.getValue();
            connector = connector
                .parameters(point.getHorizontal(), point.getVertical())
                .parameters(piece.getType(), piece.getColor(), roomName)
                .batch();
        }
        connector.executeBatch();
    }

    @Override
    public BoardDto readBoard(String roomName) {
        JdbcConnector.ResultSetHolder holder = JdbcConnector.query("select * from board where room_name = ?")
            .parameters(roomName)
            .executeQuery();

        Map<PointDto, PieceDto> pointPieceDto = new HashMap<>();
        while (holder.next()) {
            PointDto point = PointDto.of(
                holder.getInteger("horizontal_index"), holder.getInteger("vertical_index")
            );
            PieceDto piece = PieceDto.of(
                holder.getString("piece_type"), holder.getString("piece_color")
            );
            pointPieceDto.put(point, piece);
        }
        validateExist(pointPieceDto, roomName);
        return new BoardDto(pointPieceDto);
    }

    private void validateExist(Map<PointDto, PieceDto> pointPieces, String roomName) {
        if (pointPieces.size() == 0) {
            throw new IllegalArgumentException(
                String.format("[ERROR] %s에 해당하는 이름의 보드가 없습니다.", roomName)
            );
        }
    }

    @Override
    public void deletePiece(PointDto pointDto, String roomName) {
        JdbcConnector.query("DELETE FROM board WHERE horizontal_index = ? and vertical_index = ? and room_name = ?")
            .parameters(pointDto.getHorizontal(), pointDto.getVertical())
            .parameters(roomName)
            .executeUpdate();
    }

    @Override
    public void updatePiece(RouteDto routeDto, String roomName) {
        PointDto source = routeDto.getSource();
        PointDto destination = routeDto.getDestination();
        JdbcConnector.query("update board set horizontal_index = ?, vertical_index = ? "
                + "where horizontal_index = ? and vertical_index = ? and room_name = ?")
            .parameters(destination.getHorizontal(), destination.getVertical(),
                source.getHorizontal(), source.getVertical())
            .parameters(roomName)
            .executeUpdate();
    }
}
