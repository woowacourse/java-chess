package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.boardstrategy.InjectBoardStrategy;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Team;
import chess.dto.ChessGameDto;
import chess.dto.PieceDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BoardDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void create(final ChessGameDto chessGameDto) {
        final Connection connection = getConnection();
        if (checkInDB(chessGameDto)) {
            return;
        }
        try {
            final String board_sql = "INSERT into board (name, turn) values (?, ?);";
            PreparedStatement board_statement = connection.prepareStatement(board_sql);
            board_statement.setString(1, chessGameDto.getName());
            board_statement.setString(2, chessGameDto.getChessGame().getTurn().name());
            board_statement.executeUpdate();
            createSquares(chessGameDto, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createSquares(ChessGameDto chessGameDto, Connection connection) throws SQLException {
        final String squares_sql = "INSERT into squares (board_name, position, piece) values (?, ?, ?);";
        for (Entry<String, String> entry : chessGameDto.getSquaresOfDB().entrySet()) {
            PreparedStatement squares_statement = connection.prepareStatement(squares_sql);
            squares_statement.setString(1, chessGameDto.getName());
            squares_statement.setString(2, entry.getKey());
            squares_statement.setString(3, entry.getValue());
            squares_statement.executeUpdate();
        }
    }

    private boolean checkInDB(ChessGameDto chessGameDto) {
        if (findTurnByName(chessGameDto.getName()) != null) {
            System.out.println("이미 해당 이름의 정보가 있습니다.");
            return true;
        }
        return false;
    }

    public ChessGameDto findByName(String name) {
        Team turn = findTurnByName(name);
        Map<Position, Piece> squares = findSquaresByName(name);
        return new ChessGameDto(name,new ChessGame(turn, new Board(squares)));
    }

    private Team findTurnByName(String name) {
        final Connection connection = getConnection();
        final String sql = "select * from board where name = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return Team.valueOf(resultSet.getString("turn"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<Position, Piece> findSquaresByName(String name){
        Map<Position, Piece> squares = new HashMap<>();
        final Connection connection = getConnection();
        final String sql = "select piece from squares where board_name = ? AND position = ?";
        try {
            for (Position position : getAblePositions()) {
                final PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, name);
                statement.setString(2, position.toString());
                final ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    return null;
                }
                String pieceStr = resultSet.getString("piece");
                squares.put(position, PieceDto.getPiece(pieceStr));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return squares;
    }

    private List<Position> getAblePositions() {
        List<Position> positions = new ArrayList<>();
        for (Column column : Column.values()) {
            for (Rank rank : Rank.values()) {
                positions.add(new Position(column, rank));
            }
        }
        return positions;
    }

    public void save(final ChessGameDto chessGameDto) {
        saveBoard(chessGameDto);
        saveSquares(chessGameDto);
    }

    private void saveBoard(final ChessGameDto chessGameDto) {
        try {
            final Connection connection = getConnection();
            final String sql = "update board set turn = ? where name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, chessGameDto.getChessGame().getTurn().name());
            statement.setString(2, chessGameDto.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveSquares(ChessGameDto chessGameDto) {
        try {
            final Connection connection = getConnection();
            final String sql = "update squares set piece = ? where board_name = ? AND position = ?";
            for (Position position : getAblePositions()) {
                final PreparedStatement statement = connection.prepareStatement(sql);
                Piece piece = chessGameDto.getSquares().get(position);
                statement.setString(1, piece.getName().getValue(piece.getTeam()));
                statement.setString(2, chessGameDto.getName());
                statement.setString(3, position.toString());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(final String name) {
        deleteSquares(name);
        deleteBoard(name);
    }

    private void deleteBoard(final String name) {
        final Connection connection = getConnection();
        final String sql = "DELETE FROM board WHERE name = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteSquares(String name) {
        final Connection connection = getConnection();
        final String sql = "DELETE FROM squares WHERE board_name = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
