package chess.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.dto.MoveDto;
// CREATE TABLE user (
// user_id BIGINT NOT NULL AUTO_INCREMENT,
// user_name VARCHAR(8) NOT NULL,
// update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
//
// PRIMARY KEY (user_id)
// );

// CREATE TABLE chess_game (
// chess_game_id BIGINT NOT NULL AUTO_INCREMENT,
// user1_id BIGINT NOT NULL,
// user2_id BIGINT NOT NULL,
// update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
//
// FOREIGN KEY (user1_id) REFERENCES user(user_id) ON UPDATE CASCADE ON DELETE CASCADE,
// FOREIGN KEY (user2_id) REFERENCES user(user_id) ON UPDATE CASCADE ON DELETE CASCADE,
//
// PRIMARY KEY (user1_id, user2_id)
// );

// CREATE TABLE move (
// 	id BIGINT NOT NULL AUTO_INCREMENT,
// 	chess_game_id BIGINT NOT NULL,
// 	source_column TINYINT NOT NULL,
// 	source_row TINYINT NOT NULL,
// 	target_column TINYINT NOT NULL,
// 	target_row TINYINT NOT NULL,
// 	update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
//
// 	FOREIGN KEY (chess_game_id) REFERENCES chess_game(chess_game_id) ON UPDATE CASCADE ON DELETE CASCADE,
// 	PRIMARY KEY (id)
// );
public class ChessGameDaoImpl implements ChessGameDao {

	private final JdbcTemplate jdbcTemplate;

	private ChessGameDaoImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static ChessGameDaoImpl create() {
		return new ChessGameDaoImpl(new JdbcTemplate());
	}



	@Override
	public boolean isLastGameExists() {
		final String query = "SELECT COUNT(*) as cnt FROM move";
		final List<Object> parameters = Collections.emptyList();
		return jdbcTemplate.executeQuery(
			query,
			resultSet -> {
				while (resultSet.next()) {
					if (resultSet.getInt("cnt") > 0) {
						return true;
					}
				}
				return false;
				},
			parameters
		);
	}

	@Override
	public void saveMove(final MoveDto moveDto) {
		final String query = "INSERT INTO move(source_column, source_row, target_column, target_row) "
			+ "VALUES (?, ?, ?, ?)";
		final List<Object> parameters = List.of(
			moveDto.getSourceColumn(),
			moveDto.getSourceRow(),
			moveDto.getTargetColumn(),
			moveDto.getTargetRow()
		);
		jdbcTemplate.executeUpdate(query, parameters);
	}

	@Override
	public List<MoveDto> loadMoves() {
		final String query = "SELECT * FROM move";
		final List<Object> parameters = Collections.emptyList();
		return jdbcTemplate.executeQuery(
			query,
			resultSet -> {
				List<MoveDto> moves = new ArrayList<>();
				while (resultSet.next()) {
					int sourceColumn = resultSet.getInt("source_column");
					int sourceRow = resultSet.getInt("source_row");
					int targetColumn = resultSet.getInt("target_column");
					int targetRow = resultSet.getInt("target_row");
					moves.add(new MoveDto(sourceColumn, sourceRow, targetColumn, targetRow));
				}
				return moves;
			},
			parameters
		);
	}

	@Override
	public void deleteMoves() {
		final String query = "DELETE FROM move";
		final List<Object> parameters = Collections.emptyList();
		jdbcTemplate.executeUpdate(query, parameters);
	}
}
