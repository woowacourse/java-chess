package techcourse.fp.chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import techcourse.fp.chess.domain.Board;
import techcourse.fp.chess.domain.ChessGame;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Piece;
import techcourse.fp.chess.domain.piece.PieceFactory;
import techcourse.fp.chess.domain.piece.PieceType;
import techcourse.fp.chess.domain.piece.Turn;
import techcourse.fp.chess.dto.request.ChessGameRequest;
import techcourse.fp.chess.dto.response.ChessGameInfo;

public class LocalMysqlChessGameDao implements ChessGameDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
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

    @Override
    public void save(final ChessGameRequest chessGameRequest) {
        try (final Connection connection = getConnection()) {
            int chessGameId = saveChessGame(chessGameRequest, connection);
            savePiece(chessGameRequest.getBoard(), connection, chessGameId);

        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private int saveChessGame(final ChessGameRequest chessGameRequest, final Connection connection) throws SQLException {
        final String query = "INSERT INTO chess_game(name, turn) VALUES (?,?);";
        final PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, chessGameRequest.getName());
        ps.setString(2, chessGameRequest.getTurn());
        ps.executeUpdate();

        return findChessGameId(ps);
    }

    private int findChessGameId(final PreparedStatement ps) throws SQLException {
        final ResultSet rs = ps.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        throw new IllegalStateException();
    }

    private void savePiece(final Map<Position, Piece> board,
                                  final Connection connection, final int chessGameId) throws SQLException {
        final String query = "INSERT INTO piece(chess_game_id, piece_file, piece_rank, color, type) VALUES (?, ?, ?, ?, ?);";

        for (Position position : board.keySet()) {
            final PreparedStatement ps = connection.prepareStatement(query);

            final Piece piece = board.get(position);

            ps.setInt(1, chessGameId);
            ps.setString(2, position.getFile().name());
            ps.setString(3, position.getRank().name());
            ps.setString(4, piece.getColor().name());
            ps.setString(5, piece.getPieceType().name());

            ps.executeUpdate();
        }
    }

    @Override
    public ChessGame findById(final long chessGameId) {
        try (final Connection connection = getConnection()) {
            Map<Position, Piece> board = createBoard(chessGameId, connection);
            final Turn turn = getTurn(chessGameId, connection);

            return new ChessGame(new Board(board, turn));
        } catch (SQLException e) {
            return null;
        }
    }

    private Map<Position, Piece> createBoard(final long chessGameId, final Connection connection) throws SQLException {
        final String query = "SELECT piece_file, piece_rank, color, type " +
                "FROM piece " +
                "where chess_game_id = ?";

        final PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, chessGameId);
        final ResultSet resultSet = ps.executeQuery();

        Map<Position, Piece> board = new HashMap<>();

        while (resultSet.next()) {
            final String file = resultSet.getString("piece_file");
            final String rank = resultSet.getString("piece_rank");
            final String color = resultSet.getString("color");
            final String type = resultSet.getString("type");

            final Piece piece = PieceFactory.generate(Color.createByName(color), PieceType.createByName(type));

            board.put(Position.createByName(file, rank), piece);
        }

        return board;
    }

    private Turn getTurn(final long chessGameId, final Connection connection) throws SQLException {
        final String query = "SELECT turn FROM chess_game WHERE id = ?";

        final PreparedStatement ps = connection.prepareStatement(query);
        ps.setLong(1, chessGameId);
        final ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            final String color = resultSet.getString(1);
            return Turn.createByStartTurn(Color.createByName(color));
        }

        throw new IllegalStateException();
    }


    @Override
    public List<ChessGameInfo> findInfos() {
        final String query = "SELECT * FROM chess_game;";

        try (final Connection connection = getConnection()) {
            final PreparedStatement ps = connection.prepareStatement(query);
            final ResultSet resultSet = ps.executeQuery();

            List<ChessGameInfo> chessGameInfos = new ArrayList<>();

            while (resultSet.next()) {
                final long id = resultSet.getLong("id");
                final String name = resultSet.getString("name");

                final String turn = resultSet.getString("turn");
                final Timestamp createAt = resultSet.getTimestamp("created_at");

                chessGameInfos.add(new ChessGameInfo(id, name, turn, createAt.toLocalDateTime()));
            }

            return chessGameInfos;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
