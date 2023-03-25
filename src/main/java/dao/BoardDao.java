package dao;

import domain.game.Board;
import domain.game.ChessGame;
import domain.game.GameState;
import domain.game.PieceType;
import domain.piece.*;
import dto.ChessGameResponseDto;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {
    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

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

    public void save(Map<Position, Piece> board, Side currentTurn) {
        final String saveQuery = "INSERT INTO chess_board(piece_type, side, last_turn, piece_rank, piece_file) VALUES(?,?,?,?,?)";
        for (Map.Entry<Position, Piece> pieces : board.entrySet()) {
            File file = pieces.getKey().getFile();
            Rank rank = pieces.getKey().getRank();
            PieceType pieceType = pieces.getValue().getPieceType();
            Side pieceSide = pieces.getValue().getSide();

            try (Connection connection = getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(saveQuery);

                preparedStatement.setString(1, pieceType.name());
                preparedStatement.setString(2, pieceSide.name());
                preparedStatement.setString(3, currentTurn.name());
                preparedStatement.setString(4, rank.getText());
                preparedStatement.setString(5, file.getText());

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public ChessGameResponseDto loadGame() {
        Map<Position, Piece> board = new HashMap<>();
        final String loadQuery = "select piece_type, side, last_turn, piece_rank, piece_file from chess_board";
        Side lastTurn = null;

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(loadQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Position position = Position.of(resultSet.getString("piece_file"), resultSet.getString("piece_rank"));
                Piece piece = new PieceFactory().create(PieceType.valueOf(resultSet.getString("piece_type")), Side.valueOf(resultSet.getString("side")));
                board.put(position, piece);
                lastTurn = Side.valueOf(resultSet.getString("last_turn"));
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return ChessGameResponseDto.from(new ChessGame(new Board(board), lastTurn, GameState.RUN));
    }

    public void delete() {
        String deleteQuery = "DELETE FROM chess_board";
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean hasGame() {
        final String loadQuery = "select last_turn from chess_board";

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(loadQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
