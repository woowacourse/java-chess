package chess.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import chess.domain.command.Turn;
import chess.domain.piece.dto.FindPiecePositionDto;
import chess.domain.piece.dto.GeneratePieceDto;
import chess.domain.piece.dto.SavePieceDto;
import chess.domain.piece.dto.UpdatePiecePositionDto;
import chess.domain.service.dto.ChessGameDto;
import chess.domain.service.dto.UpdateTurnDto;
import chess.exception.NotFoundChessGameException;

public class JdbcChessGameDao implements ChessGameDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private static final JdbcChessGameDao INSTANCE = new JdbcChessGameDao();

    private JdbcChessGameDao() {
    }

    public static JdbcChessGameDao getInstance() {
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
    public Long saveNewChessGame() {
        final String query = "INSERT INTO chess_game(turn) VALUES(?)";
        Long autoIncrementValue = 0L;
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, Turn.WHITE.getDisplayName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                autoIncrementValue = generatedKeys.getLong(1);
            }
            return autoIncrementValue;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isExistPreviousChessGame(Long gameId) {
        final String query = "SELECT * FROM chess_game WHERE game_id = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setLong(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.isBeforeFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void savePiece(SavePieceDto savePieceDto) {
        final String query = "INSERT INTO piece(piece_rank, piece_file, piece_type, side, game_id) VALUES(?, ?, ?, ?, ?)";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, savePieceDto.getRank());
            preparedStatement.setString(2, savePieceDto.getFile());
            preparedStatement.setString(3, savePieceDto.getType());
            preparedStatement.setString(4, savePieceDto.getSide());
            preparedStatement.setLong(5, savePieceDto.getGameId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GeneratePieceDto> findAllPieceByGameId(Long gameId) {
        final String query = "SELECT * FROM piece WHERE game_id = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setLong(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<GeneratePieceDto> generatePieceDtos = new ArrayList<>();
            while (resultSet.next()) {
                String pieceRank = resultSet.getString("piece_rank");
                String pieceFile = resultSet.getString("piece_file");
                String pieceType = resultSet.getString("piece_type");
                String side = resultSet.getString("side");
                generatePieceDtos.add(new GeneratePieceDto(pieceRank, pieceFile, pieceType, side));
            }
            return generatePieceDtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ChessGameDto findChessGameByGameId(Long gameId) {
        final String query = "SELECT * FROM chess_game WHERE game_id = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setLong(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String turnName = resultSet.getString("turn");
                return new ChessGameDto(gameId, turnName, findAllPieceByGameId(gameId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new NotFoundChessGameException("[ERROR] 체스 게임을 찾지 못했습니다.");
    }

    @Override
    public void updatePiecePosition(UpdatePiecePositionDto updatePiecePositionDto, FindPiecePositionDto findPiecePositionDto) {
        final String query =
                "UPDATE piece " +
                "SET piece_rank = ?, piece_file = ? " +
                "WHERE game_id = ? AND piece_rank = ? AND piece_file = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, updatePiecePositionDto.getRank());
            preparedStatement.setString(2, updatePiecePositionDto.getFile());
            preparedStatement.setLong(3, findPiecePositionDto.getGameId());
            preparedStatement.setString(4, findPiecePositionDto.getRank());
            preparedStatement.setString(5, findPiecePositionDto.getFile());
            int updateCount = preparedStatement.executeUpdate();
            if (updateCount > 1) {
                throw new SQLException("[ERROR] 포지션 업데이트 되는 기물이 2개 이상입니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePieceByPosition(FindPiecePositionDto findPiecePositionDto) {
        final String query =
                "DELETE FROM piece WHERE game_id = ? AND piece_rank = ? AND piece_file = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setLong(1, findPiecePositionDto.getGameId());
            preparedStatement.setString(2, findPiecePositionDto.getRank());
            preparedStatement.setString(3, findPiecePositionDto.getFile());

            int updateCount = preparedStatement.executeUpdate();
            if (updateCount > 1) {
                throw new SQLException("[ERROR] 삭제하려는 포지션에 해당하는 기물이 2개 이상입니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTurn(UpdateTurnDto updateTurnDto) {
        final String query = "UPDATE chess_game SET turn = ? WHERE game_id = ?";

        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, updateTurnDto.getTurn());
            preparedStatement.setLong(2, updateTurnDto.getGameId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
