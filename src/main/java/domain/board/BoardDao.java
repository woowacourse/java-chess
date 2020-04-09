package domain.board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.piece.Piece;
import domain.piece.PieceCreator;
import domain.piece.position.Position;
import domain.piece.team.Team;
import domain.template.JdbcTemplate;
import domain.template.RowMapper;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class BoardDao {
	private static final int FIRST_RANK_INDEX = 1;
	private static final int LAST_RANK_INDEX = 8;
	private static final int RANK_INDEX = 1;

	public void clearBoardDb() throws SQLException {
		String query = "DELETE FROM board";
		JdbcTemplate template = new JdbcTemplate();
		template.executeUpdate(query);
	}

	public void saveBoard(Board board) throws SQLException {
		for (Rank rank : board.getRanks()) {
			savePiece(rank);
		}
	}

	private void savePiece(Rank rank) throws SQLException {
		String query = "INSERT INTO board VALUES (?, ?)";
		for (Piece piece : rank.getPieces()) {
			String position =
				piece.getPosition().getColumn().getColumnName() + String.valueOf(piece.getPosition().getRow());
			JdbcTemplate template = new JdbcTemplate();
			template.executeUpdate(query, position, piece.showSymbol());
		}
	}

	public void clearTurn() throws SQLException {
		String query = "DELETE FROM TURN";
		JdbcTemplate template = new JdbcTemplate();
		template.executeUpdate(query);
	}

	public void saveTurn(Team turn) throws SQLException {
		String query = "INSERT INTO turn VALUES (?)";
		JdbcTemplate template = new JdbcTemplate();
		template.executeUpdate(query, turn.getName());
	}

	public Board loadBoard() throws SQLException {
		List<Rank> ranks = new ArrayList<>();
		for (int i = FIRST_RANK_INDEX; i <= LAST_RANK_INDEX; i++) {
			ranks.add(loadRank(i));
		}
		return new Board(ranks);
	}

	private Rank loadRank(int rankIndex) throws SQLException {
		String query = "SELECT * FROM board";
		JdbcTemplate template = new JdbcTemplate();

		RowMapper<Piece> rowMapper = rs -> {
			Piece piece = null;
			char rankOfPiece = rs.getString("piece_position").charAt(RANK_INDEX);
			int pieceRank = Integer.parseInt(String.valueOf(rankOfPiece));
			Position position = Position.of(rs.getString("piece_position"));
			if (pieceRank == rankIndex) {
				piece = PieceCreator.of(rs.getString("symbol"), position);
			}
			return piece;
		};
		return new Rank(template.executeQuery(query, rowMapper));
	}

	public Team loadTurn() throws SQLException {
		JdbcTemplate template = new JdbcTemplate();
		String query = "SELECT * FROM turn";
		RowMapper<Team> rowMapper = rs -> Team.of(rs.getString("game_turn"));
		return template.executeQuery(query, rowMapper).get(0);
	}
}
