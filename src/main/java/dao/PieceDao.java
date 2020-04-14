package dao;

import dto.PieceDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {
	private static final PieceDao PIECE_DAO;

	static {
		PIECE_DAO = new PieceDao(ConnectionDao.getInstance());
	}

	private final ConnectionDao connectionDao;

	private PieceDao(final ConnectionDao connectionDao) {
		this.connectionDao = connectionDao;
	}

	public static PieceDao getInstance() {
		return PIECE_DAO;
	}

	public int addPiece(final String pieceTypeName, final String teamName, final String coordinateRepresentation,
						final int roomId) throws SQLException {
		final String query = "INSERT INTO piece(piece_type, team, coordinate, room_id) "
				+ "VALUES(?, ?, ?, ?)";

		try (final Connection connection = connectionDao.getConnection();
			 final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, pieceTypeName);
			preparedStatement.setString(2, teamName);
			preparedStatement.setString(3, coordinateRepresentation);
			preparedStatement.setInt(4, roomId);
			return preparedStatement.executeUpdate();
		}
	}

	public List<PieceDto> findPiecesByRoomId(final int roomId) throws SQLException {
		final String query = "SELECT * FROM piece WHERE room_id = ?";

		try (final Connection connection = connectionDao.getConnection();
			 final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, roomId);
			return preparePieceDtos(preparedStatement);
		}

	}

	private List<PieceDto> preparePieceDtos(final PreparedStatement preparedStatement) throws SQLException {
		try (ResultSet resultSet = preparedStatement.executeQuery()) {
			return collectPieceDtos(resultSet);
		}
	}

	private List<PieceDto> collectPieceDtos(final ResultSet resultSet) throws SQLException {
		List<PieceDto> pieceDtos = new ArrayList<>();
		while (resultSet.next()) {
			pieceDtos.add(new PieceDto(resultSet.getInt("id"), resultSet.getString("piece_type"),
					resultSet.getString("team"), resultSet.getString("coordinate"),
					resultSet.getInt("room_id")));
		}

		return pieceDtos;
	}

	public int deletePieces(final int roomId) throws SQLException {
		final String query = "DELETE FROM piece WHERE room_id = ?";

		try (final Connection connection = connectionDao.getConnection();
			 final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, roomId);
			return preparedStatement.executeUpdate();
		}
	}
}
