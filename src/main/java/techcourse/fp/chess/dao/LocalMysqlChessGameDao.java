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
        final String query = "INSERT INTO chess_game(name, turn) VALUES (?,?);";
        final String query2 = "INSERT INTO piece(chess_game_id, piece_file, piece_rank, color, type) VALUES (?, ?, ?, ?, ?);";
        try (final Connection connection = getConnection()) {
            final PreparedStatement pstmt1 = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt1.setString(1, chessGameRequest.getName());
            pstmt1.setString(2, chessGameRequest.getTurn());
            pstmt1.executeUpdate();

            final ResultSet rs = pstmt1.getGeneratedKeys();
            int chessGameId = 0;
            if (rs.next()) {
                chessGameId = rs.getInt(1);
            }

            final Map<Position, Piece> board = chessGameRequest.getBoard();

            for (Position position : board.keySet()) {
                final PreparedStatement pstmt2 = connection.prepareStatement(query2);

                final Piece piece = board.get(position);

                pstmt2.setInt(1, chessGameId);
                pstmt2.setString(2, position.getFile().name());
                pstmt2.setString(3, position.getRank().name());
                pstmt2.setString(4, piece.getColor().name());
                pstmt2.setString(5, piece.getPieceType().name());
                pstmt2.executeUpdate();
            }


        } catch (final SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ChessGame findById(final long chessGameId) {
        final String query = "SELECT piece_file, piece_rank, color, type " +
                "FROM piece " +
                "where chess_game_id = ?";

        final String query2 = "SELECT turn FROM chess_game WHERE id = ?";

        try (final Connection connection = getConnection()) {
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

            final PreparedStatement ps2 = connection.prepareStatement(query2);
            ps2.setLong(1, chessGameId);
            final ResultSet resultSet2 = ps2.executeQuery();


            if (resultSet2.next()) {
                String turn = resultSet2.getString(1);
                return new ChessGame(new Board(board, Turn.createByStartTurn(Color.createByName(turn))));
            }

            throw new IllegalStateException();

        } catch (SQLException e) {
            return null;
        }
    }


    @Override
    public List<ChessGameInfo> findInfo() {
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
