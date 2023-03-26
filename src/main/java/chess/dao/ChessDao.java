package chess.dao;

import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.Square;
import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.piece.Role;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ChessDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void save(final ChessBoard chessBoard) {
        clearBoard();
        clearState();
        final List<Square> squares = chessBoard.getSquares();
        for (Square square : squares) {
            saveOneSquare(square);
        }
        final int turn = chessBoard.getTurn().getTurn();
        saveTurn(turn);
    }

    private void clearBoard() {
        final String query = "DELETE from board";
        try (final Connection connection = getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(
                query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearState() {
        final String query = "DELETE from state";
        try (final Connection connection = getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(
                query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveOneSquare(final Square square) {
        String rank = square.getPosition().getRank().name();
        String file = square.getPosition().getFile().name();
        String role = square.getPiece().getRole().name();
        String team = square.getPiece().getTeam().name();
        final String query = "INSERT INTO board(piece_rank, piece_file, piece_role, piece_team) VALUES (?, ?, ?, ?)";
        try (final Connection connection = getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(
                query)) {
            preparedStatement.setString(1, rank);
            preparedStatement.setString(2, file);
            preparedStatement.setString(3, role);
            preparedStatement.setString(4, team);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveTurn(final int turn) {
        final String query = "INSERT INTO state (name) VALUES (?)";
        try (final Connection connection = getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(
                query)) {
            preparedStatement.setString(1, String.valueOf(turn));
            preparedStatement.execute();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ChessGame load() {
        final Turn turn = fetchTurn();
        if (turn.isFirst()) {
            return null;
        }
        final List<Square> squares = new ArrayList<>();
        fetchBoard(squares);
        return new ChessGame(new ChessBoard(squares, turn));
    }

    private Turn fetchTurn() {
        final String stateSelectQuery = "SELECT * FROM state";
        try (final Connection connection = getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(
                stateSelectQuery)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Turn(Integer.parseInt(resultSet.getString(2)));
            }
            return new Turn();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void fetchBoard(final List<Square> squares) {
        final String boardSelectQuery = "SELECT * FROM board";
        try (final Connection connection = getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(
                boardSelectQuery)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                fetchOneSquare(squares, resultSet);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void fetchOneSquare(final List<Square> squares, final ResultSet resultSet) throws SQLException {
        final Rank rank = Rank.valueOf(resultSet.getString(2));
        final File file = File.valueOf(resultSet.getString(3));
        final Role role = Role.valueOf(resultSet.getString(4));
        final Team team = Team.valueOf(resultSet.getString(5));
        squares.add(new Square(Position.of(file, rank), role.createPiece(team)));
    }
}