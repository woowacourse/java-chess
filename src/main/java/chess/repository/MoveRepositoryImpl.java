package chess.repository;

import chess.domain.coordinate.Coordinate;
import chess.dto.MoveDto;
import chess.result.Result;
import chess.utils.IdGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static chess.utils.dbConnector.getConnection;

public class MoveRepositoryImpl implements MoveRepository {

    @Override
    public Result add(MoveDto moveDto) throws SQLException {
        String query = "INSERT INTO move VALUES (?, ?, ?, ?)";
        int moveId = IdGenerator.generateMoveId();
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, moveId);
        pstmt.setInt(2, moveDto.getRoomId());
        pstmt.setString(3, moveDto.getSource().toString());
        pstmt.setString(4, moveDto.getTarget().toString());
        pstmt.executeUpdate();
        return new Result(true, moveId);
    }

    @Override
    public Result findById(int moveId) throws SQLException {
        String query = "SELECT * FROM move WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, moveId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return new Result(false, null);
        }

        MoveDto moveDto = new MoveDto(rs.getInt("room_id"),
                Coordinate.of(rs.getString("source")),
                Coordinate.of(rs.getString("target")));
        moveDto.setMoveId(rs.getInt("id"));
        return new Result(true, moveDto);
    }

    @Override
    public Result findByRoomId(int roomId) throws SQLException {
        String query = "SELECT * FROM move WHERE room_id = ? ORDER BY id ASC;";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, roomId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return new Result(false, null);
        }
        List<MoveDto> moveDtos = new ArrayList<>();
        do {
            moveDtos.add(new MoveDto(rs.getInt("room_id"),
                    Coordinate.of(rs.getString("source")),
                    Coordinate.of(rs.getString("target"))));
        } while (rs.next());
        return new Result(true, moveDtos);
    }


    @Override
    public Result deleteById(int moveId) throws SQLException {
        String query = "DELETE FROM move WHERE id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, moveId);
        pstmt.executeUpdate();
        return new Result(true, null);
    }

    @Override
    public Result deleteByRoomId(int roomId) throws SQLException {
        String query = "DELETE FROM move WHERE room_id = ?";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, roomId);
        pstmt.executeUpdate();
        return new Result(true, null);
    }

    @Override
    public Result deleteAll() throws SQLException {
        String query = "DELETE FROM move";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.executeUpdate();
        return new Result(true, null);
    }
}
