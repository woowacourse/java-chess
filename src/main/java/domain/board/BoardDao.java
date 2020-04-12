package domain.board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.piece.Piece;
import domain.piece.PieceCreator;
import domain.piece.position.Position;
import domain.piece.team.Team;
import domain.template.EmptyDatabaseException;
import domain.template.JdbcTemplate;
import domain.template.PreparedStatementSetter;
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
		PreparedStatementSetter pss = pstmt -> {
		};
		JdbcTemplate template = new JdbcTemplate();
		template.executeUpdate(query, pss);
	}

	public void saveBoard(Board board) throws SQLException {
		for (Rank rank : board.getRanks()) {
			savePiece(rank);
		}
	}

	private void savePiece(Rank rank) throws SQLException {
		String query = "INSERT INTO board VALUES (?, ?)";
		JdbcTemplate template = new JdbcTemplate();
		PreparedStatementSetter pss;
		for (Piece piece : rank.getPieces()) {
			String position =
				piece.getPosition().getColumn().getColumnName() + String.valueOf(piece.getPosition().getRow());
			pss = pstmt -> {
				pstmt.setString(1, position);
				pstmt.setString(2, piece.showSymbol());
			};
			template.executeUpdate(query, pss);
		}
	}

	public void clearTurn() throws SQLException {
		String query = "DELETE FROM TURN";
		JdbcTemplate template = new JdbcTemplate();
		PreparedStatementSetter pss = pstmt -> {
		};
		template.executeUpdate(query, pss);
	}

	public void saveTurn(Team turn) throws SQLException {
		String query = "INSERT INTO turn VALUES (?)";
		JdbcTemplate template = new JdbcTemplate();
		PreparedStatementSetter pss = pstmt -> {
			pstmt.setString(1, turn.getName());
		};
		template.executeUpdate(query, pss);
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
		PreparedStatementSetter pss = pstmt -> {
		};
		RowMapper<Rank> rowMapper = rs -> {
			List<Piece> pieces = new ArrayList<>();
			char rankOfPiece;
			int pieceRank;
			Position position;

			while (rs.next()) {
				rankOfPiece = rs.getString("piece_position").charAt(RANK_INDEX);
				pieceRank = Integer.parseInt(String.valueOf(rankOfPiece));
				if (pieceRank == rankIndex) {
					position = Position.of(rs.getString("piece_position"));
					pieces.add(PieceCreator.of(rs.getString("symbol"), position));
				}
			}
			return new Rank(pieces);
		};
		return template.executeQuery(query, pss, rowMapper);
	}

	public Team loadTurn() throws SQLException {
		JdbcTemplate template = new JdbcTemplate();
		String query = "SELECT * FROM turn";
		PreparedStatementSetter pss = pstmt -> {
		};
		RowMapper<Team> rowMapper = rs -> {
			if (rs.next()) {
				return Team.of(rs.getString("game_turn"));
			}
			throw new EmptyDatabaseException(EmptyDatabaseException.EMPTY_DATA);
		};
		return template.executeQuery(query, pss, rowMapper);
	}
}
