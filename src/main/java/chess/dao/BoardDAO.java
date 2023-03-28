package chess.dao;

import chess.domain.board.*;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.RunningChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.side.Color;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoardDAO {
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

    // TODO: 게임방 여러개 만들기
    public ChessGame select(final Long chessGameId) {
        String queryChessGame = "SELECT turn " +
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
                String turnName = resultSet.getString("turn");
                Color turn = Color.findByName(turnName);

                preparedStatementPiece.setLong(1, 1);
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
                return new RunningChessGame(loadBoard);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(ChessGame chessGame) {
        String queryBoard = "INSERT INTO board(id, turn) " +
                "VALUES(1, ?); ";
        String queryPiece = "INSERT INTO board_piece(board_id, file, ranks, role, color) " +
                "VALUES (1, ?, ?, ?, ?); ";
        Board board = chessGame.getBoard();
        try (
                final var connection = getConnection();
                final var preparedStatement = connection.prepareStatement(queryBoard);
                final var preparedStatementPiece = connection.prepareStatement(queryPiece)
        ) {
            preparedStatement.setString(1, board.getTurn().name());
            preparedStatement.executeUpdate();

            for (Square square : Square.findAllSquare()) {
                Piece piece = board.findPiece(square);
                Role role = piece.getRole();
                Color color = piece.getColor();
                preparedStatementPiece.setString(1, square.getFile().name());
                preparedStatementPiece.setInt(2, square.getRank().getPosition());
                preparedStatementPiece.setString(3, role.name());
                preparedStatementPiece.setString(4, color.name());
                preparedStatementPiece.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePiece(Square square, Piece piece) {
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

    public void updateBoard(Color color) {
        String query = "UPDATE board " +
                "SET turn = ? " +
                "WHERE id = 1 ";
        try (
                final var connection = getConnection();
                final var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, color.name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
    }
}
