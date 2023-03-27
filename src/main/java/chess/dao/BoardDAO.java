package chess.dao;

import chess.domain.board.*;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.RunningChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.side.Color;

import java.sql.*;
import java.util.Arrays;
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
    public ChessGame select() {
        String query = "SELECT id, turn " +
                "FROM board b " +
                "WHERE id = 1 ";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                long chessGameId = resultSet.getLong(1);
                String turn = resultSet.getString(2);

                Color[] colors = Color.values();
                Color boardColor = Arrays.stream(colors)
                        .filter(colorName -> colorName.name().equals(turn))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("진영 없음"));

                String queryPieces = "SELECT file, ranks, color, role " +
                        "FROM board_piece " +
                        "WHERE board_id = ?";

                PreparedStatement preparedStatement2 = connection.prepareStatement(queryPieces);
                preparedStatement2.setLong(1, 1);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                Map<Square, Piece> board = new LinkedHashMap<>();
                while (resultSet2.next()) {
                    String file = resultSet2.getString(1);
                    int rank = resultSet2.getInt(2);
                    String color = resultSet2.getString(3);
                    String role = resultSet2.getString(4);

                    Color pieceColor = Arrays.stream(colors)
                            .filter(colorName -> colorName.name().equals(color))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("진영 없음"));
                    Square square = Square.from(file + rank);
                    Role[] roles = Role.values();
                    Role pieceRole = Arrays.stream(roles)
                            .filter(roleName -> roleName.name().equals(role))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("피스가 없음"));
                    Piece piece = pieceRole.create(pieceColor);
                    board.put(square, piece);
                }
                Board loadBoard = BoardFactory.create(board, boardColor);
                return new RunningChessGame(loadBoard);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(ChessGame chessGame) {
        String query = "INSERT INTO board(id, turn) " +
                "VALUES(1, ?); ";
        System.out.println(chessGame);
        Board board = chessGame.getBoard();
        System.out.println(chessGame);
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, board.getTurn().name());
            preparedStatement.executeUpdate();

            for (File file : File.values()) {
                for (Rank rank : Rank.values()) {
                    Piece piece = board.findPiece(file, rank);
                    Role role = piece.getRole();
                    Color color = piece.getColor();
                    String queryPiece = "INSERT INTO board_piece(board_id, file, ranks, role, color) " +
                            "VALUES (1, ?, ?, ?, ?); ";
                    PreparedStatement preparedStatementPiece = connection.prepareStatement(queryPiece);
                    preparedStatementPiece.setString(1, file.name());
                    preparedStatementPiece.setInt(2, rank.getPosition());
                    preparedStatementPiece.setString(3, role.name());
                    preparedStatementPiece.setString(4, color.name());
                    preparedStatementPiece.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePiece(Square square, Piece piece) {
        String query = "UPDATE board_piece " +
                "SET role = ?, color = ? " +
                "WHERE ranks = ? AND file = ? ";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
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
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, color.name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {

    }
}
