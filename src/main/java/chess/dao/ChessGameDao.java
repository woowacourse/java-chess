package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Square;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.GameState;
import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.side.Color;
import chess.dto.ChessGameDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessGameDao {
    private static final String SERVER = "localhost:3306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "12345678"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<ChessGameDto> selectAll() {
        String query = "SELECT id, turn " +
                "FROM board ";
        try (
                final var connection = getConnection();
                final var preparedStatement = connection.prepareStatement(query);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ChessGameDto> results = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String turnName = resultSet.getString("turn");
                Color turn = Color.findByName(turnName);
                results.add(new ChessGameDto(id, turn));
            }
            return results;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ChessGame select(final Long chessGameId) {
        String queryChessGame = "SELECT id, turn " +
                "FROM board b " +
                "WHERE id = ? ";

        String queryPieces = "SELECT file, ranks, color, role " +
                "FROM board_piece " +
                "WHERE board_id = ?";

        try (
                final var connection = getConnection();
                final var preparedStatementChessGame = connection.prepareStatement(queryChessGame);
                final var preparedStatementPiece = connection.prepareStatement(queryPieces)
        ) {
            preparedStatementChessGame.setLong(1, chessGameId);
            ResultSet resultSet = preparedStatementChessGame.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String turnName = resultSet.getString("turn");
                Color turn = Color.findByName(turnName);

                preparedStatementPiece.setLong(1, id);
                ResultSet resultSet2 = preparedStatementPiece.executeQuery();
                Map<Square, Piece> board = new LinkedHashMap<>();
                while (resultSet2.next()) {
                    String file = resultSet2.getString(1);
                    int rank = resultSet2.getInt(2);
                    String colorName = resultSet2.getString(3);
                    String roleName = resultSet2.getString(4);

                    Role role = Role.findRoleByName(roleName);
                    Color color = Color.findByName(colorName);
                    Square square = Square.from(file + rank);
                    Piece piece = role.create(color);
                    board.put(square, piece);
                }
                Board loadBoard = BoardFactory.create(board, turn);
                return new ChessGame(id, GameState.RUNNING, loadBoard);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long insertNewGame(final Board board) {
        String queryBoard = "INSERT INTO board(turn) " +
                "VALUES(?); ";
        String queryPiece = "INSERT INTO board_piece(board_id, file, ranks, role, color) " +
                "VALUES (?, ?, ?, ?, ?); ";
        try (
                final var connection = getConnection();
                final var preparedStatement = connection.prepareStatement(queryBoard, Statement.RETURN_GENERATED_KEYS);
                final var preparedStatementPiece = connection.prepareStatement(queryPiece)
        ) {
            preparedStatement.setString(1, board.getTurn().name());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            long id = generatedKeys.getLong(1);

            for (Square square : Square.findAllSquare()) {
                Piece piece = board.findPiece(square);
                Role role = piece.getRole();
                Color color = piece.getColor();
                preparedStatementPiece.setLong(1, id);
                preparedStatementPiece.setString(2, square.getFile().name());
                preparedStatementPiece.setInt(3, square.getRank().getPosition());
                preparedStatementPiece.setString(4, role.name());
                preparedStatementPiece.setString(5, color.name());
                preparedStatementPiece.executeUpdate();
            }

            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePiece(final Square square, final Piece piece) {
        String query = "UPDATE board_piece " +
                "SET role = ?, color = ? " +
                "WHERE ranks = ? AND file = ? ";
        try (
                final var connection = getConnection();
                final var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, piece.getRole().name());
            preparedStatement.setString(2, piece.getColor().name());
            preparedStatement.setInt(3, Integer.parseInt(square.toString().substring(1, 2)));
            preparedStatement.setString(4, square.toString().substring(0, 1));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateGame(final ChessGame chessGame) {
        String query = "UPDATE board " +
                "SET turn = ? " +
                "WHERE id = ? ";
        try (
                final var connection = getConnection();
                final var preparedStatement = connection.prepareStatement(query)
        ) {
            Long id = chessGame.getId();
            Board board = chessGame.getBoard();
            preparedStatement.setString(1, board.getTurn().name());
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
