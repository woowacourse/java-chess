package chess.dao;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public final class DBChessGameDao implements ChessGameDao {

    /*

    CREATE TABLE chess_game(
        position_column INT not null,
        position_row INT not null,
        piece_type VARCHAR(255) not null,
        piece_team VARCHAR(255) not null,
        turn VARCHAR(255) not null,
        constraint chess_game_pk primary key(position_column, position_row)
    );

    INSERT INTO chess_game(position_column, position_row, piece_type, piece_team, turn) VALUES(?,?,?,?,?);
    SELECT * FROM chess_game;
    DELETE FROM chess_game;

    */

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void save(final ChessGame chessGame) {
        final Map<Position, Piece> board = chessGame.getBoard().getBoard();
        for (final Map.Entry<Position, Piece> entry : board.entrySet()) {
            final Position position = entry.getKey();
            final Piece piece = entry.getValue();

            final var query = "INSERT INTO chess_game(position_column, position_row, piece_type, piece_team, turn) VALUES(?,?,?,?,?);";
            try (final var connection = getConnection();
                 final var preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, position.getColumn());
                preparedStatement.setInt(2, position.getRow());
                preparedStatement.setString(3, piece.getPieceType().name());
                preparedStatement.setString(4, piece.getTeam().name());
                preparedStatement.setString(5, chessGame.getTurn().name());

                preparedStatement.executeUpdate();
            } catch (final SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public ChessGame select() {
        final Map<Position, Piece> board = new TreeMap<>();
        Team turn = null;

        final var query = "SELECT * FROM chess_game;";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query);
             final ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                final int column = resultSet.getInt("position_column");
                final int row = resultSet.getInt("position_row");

                final Position position = new Position(column, row);
                final PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));
                final Team team = Team.valueOf(resultSet.getString("piece_team"));

                if(turn == null){
                    turn = Team.valueOf(resultSet.getString("turn"));
                }

                final Piece piece = PieceMapper.toPiece(pieceType, team);
                board.put(position, piece);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        if (board.isEmpty()) {
            return null;
        }

        return new ChessGame(new Board(board), turn);
    }

    @Override
    public void update(final ChessGame chessGame) {
        delete();
        save(chessGame);
    }

    @Override
    public void delete() {
        final var query = "DELETE FROM chess_game;";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
