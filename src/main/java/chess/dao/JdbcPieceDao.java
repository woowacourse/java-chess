package chess.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import chess.dao.entity.PieceEntity;

public class JdbcPieceDao implements PieceDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private static final JdbcPieceDao INSTANCE = new JdbcPieceDao();
    private static final long INITIAL_PIECE_ID = 1L;

    private JdbcPieceDao() {
    }

    public static JdbcPieceDao getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long findRecentPieceId() {
        final String query = "SELECT id FROM piece ORDER BY id desc LIMIT 1";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return INITIAL_PIECE_ID;
            }
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void savePiece(PieceEntity pieceEntity) {
        final String query = "INSERT INTO piece(piece_rank, piece_file, piece_type, side, game_id) VALUES(?, ?, ?, ?, ?)";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, pieceEntity.getRank());
            preparedStatement.setString(2, pieceEntity.getFile());
            preparedStatement.setString(3, pieceEntity.getType());
            preparedStatement.setString(4, pieceEntity.getSide());
            preparedStatement.setLong(5, pieceEntity.getGameId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePiecePosition(PieceEntity pieceEntityToUpdate, PieceEntity pieceEntityToFind) {
        final String query =
                "UPDATE piece " +
                "SET piece_rank = ?, piece_file = ? " +
                "WHERE game_id = ? AND piece_rank = ? AND piece_file = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, pieceEntityToUpdate.getRank());
            preparedStatement.setString(2, pieceEntityToUpdate.getFile());
            preparedStatement.setLong(3, pieceEntityToFind.getGameId());
            preparedStatement.setString(4, pieceEntityToFind.getRank());
            preparedStatement.setString(5, pieceEntityToFind.getFile());
            int updateCount = preparedStatement.executeUpdate();
            if (updateCount > 1) {
                throw new SQLException("[ERROR] 포지션 업데이트 되는 기물이 2개 이상입니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePieceByPosition(PieceEntity pieceEntityToDelete) {
        final String query = "DELETE FROM piece WHERE game_id = ? AND piece_rank = ? AND piece_file = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setLong(1, pieceEntityToDelete.getGameId());
            preparedStatement.setString(2, pieceEntityToDelete.getRank());
            preparedStatement.setString(3, pieceEntityToDelete.getFile());
            int deleteCount = preparedStatement.executeUpdate();
            if (deleteCount > 1) {
                throw new SQLException("[ERROR] 삭제되는 기물이 2개 이상입니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PieceEntity> findAllPieceByGameId(Long gameId) {
        final String query = "SELECT * FROM piece WHERE game_id = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setLong(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<PieceEntity> pieceEntities = new ArrayList<>();
            while (resultSet.next()) {
                String pieceRank = resultSet.getString("piece_rank");
                String pieceFile = resultSet.getString("piece_file");
                String pieceType = resultSet.getString("piece_type");
                String side = resultSet.getString("side");
                pieceEntities.add(generatePieceEntity(pieceRank, pieceFile, pieceType, side));
            }
            return pieceEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PieceEntity generatePieceEntity(String pieceRank, String pieceFile, String pieceType, String side) {
        return new PieceEntity.Builder()
                .rank(pieceRank)
                .file(pieceFile)
                .type(pieceType)
                .side(side)
                .build();
    }
}
