package chess;

import chess.model.Side;
import chess.model.board.*;
import chess.model.unit.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

class BoardDAO {
    private static final Map<String, Function<Side, Piece>> STRING_PIECE_MAP = new HashMap<>();
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int EMPTY_VALUE = 0;
    private static final String EMPTY_STRING = "";
    private static final String UNDER_BAR = "_";
    private static final String PIECE_SIDE_WHITE = "w";
    private static final String PIECE_SIDE_BLACK = "b";
    private static final String SERVERS = " 서버의 ";
    private static final String CONNECT_SUCCESS = " DB와 정상적으로 연결되었습니다.";
    private static final String CONNECT_FAILED = " DB와 연결하지 못했습니다: ";
    private static final String ALREADY_CLOSED = "이미 닫힌 DB 연결입니다.";
    private static final String CLOSE_FAILED = "DB와 연결을 해제하지 못했습니다: ";
    private static final String JDBC_FAILED = "JDBC 드라이버를 불러오지 못했습니다: ";
//    private static final String INIT_SQL_DATABASE =
//            "CREATE DATABASE IF NOT EXISTS chess;";
    private static final String INIT_SQL_TABLE =
            "CREATE TABLE IF NOT EXISTS board (" +
            "square CHAR(2) NOT NULL," +
            "piece CHAR(2) NOT NULL);";

    static {
        STRING_PIECE_MAP.put("K", King::new);
        STRING_PIECE_MAP.put("Q", Queen::new);
        STRING_PIECE_MAP.put("R", Rook::new);
        STRING_PIECE_MAP.put("B", Bishop::new);
        STRING_PIECE_MAP.put("N", Knight::new);
        STRING_PIECE_MAP.put("P", Pawn::new);
    }

    private Connection connection;

    BoardDAO(final String server, final String database, final String username, final String password) {
        connection = tryConnect(server, database, username, password);
    }

    public Connection getConnection() throws Exception {
        if (connection != null) {
            return connection;
        }
        throw new Exception(ALREADY_CLOSED);
    }

    private Connection tryConnect(final String server, final String database, final String username, final String password) {
        Connection connection = null;

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(JDBC_FAILED + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            String url = "jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println(server + SERVERS + database + CONNECT_SUCCESS);
        } catch (SQLException e) {
            System.err.println(server + SERVERS + database + CONNECT_FAILED + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
            e.printStackTrace();
        }

        return connection;
    }

    // 드라이버 연결 해제
    public void closeConnection() {
        try {
            connection.close();
            this.connection = null;
        } catch (SQLException e) {
            System.err.println(CLOSE_FAILED + e.getMessage());
        }
    }

    private Position parsePosition(final String squareString, final String pieceString) {
        return new Position(parseSquare(squareString), parsePiece(pieceString));
    }

    private Square parseSquare(final String squareString) {
        final String[] args = squareString.strip().toUpperCase().split(EMPTY_STRING);
        final Column col = Column.valueOf(args[FIRST]);
        final Row row = Row.valueOf(UNDER_BAR + args[SECOND]);
        return new Square(col, row);
    }

    private Piece parsePiece(final String pieceString) {
        final String[] args = pieceString.strip().split(EMPTY_STRING);
        final Side side
                = args[FIRST].toLowerCase().equals(PIECE_SIDE_WHITE)
                ? Side.WHITE
                : Side.BLACK;
        return STRING_PIECE_MAP.get(args[SECOND].toUpperCase()).apply(side);
    }

    List<Position> getAllPositions() throws SQLException {
        final String query = "SELECT * FROM board";
        final PreparedStatement statement = connection.prepareStatement(query);
        final ResultSet set = statement.executeQuery();
        final List<Position> positionList = new ArrayList<>();
        while (set.next()) {
            positionList.add(parsePosition(
                    set.getString("square"),
                    set.getString("piece")
            ));
        }
        return positionList;
    }

    List<Position> initBoardPositions() throws SQLException {
        makeFirstBoardTable();
        List<Position> existingPositions = getAllPositions();
        if (existingPositions.size() == EMPTY_VALUE) {
            existingPositions = Board.makeInitialBoard().getAllPositions();
            setBoard(existingPositions);
        }
        return existingPositions;
    }

    private void makeFirstBoardTable() throws SQLException {
        connection.prepareStatement(INIT_SQL_TABLE).executeUpdate();
    }

    private void setBoard(final List<Position> positionList) throws SQLException {
        resetBoard();
        String query = "INSERT INTO board (square, piece) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        for (Position position : positionList) {
            statement.setString(1, position.getSquare().toString());
            statement.setString(2, position.getPiece().toString());
            statement.addBatch();
            statement.clearParameters();
        }
        statement.executeBatch();
    }

    void resetBoard() throws SQLException {
        final String query = "DELETE FROM board";
        final PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
    }

    public void movePiece(final Square source, final Square target) throws SQLException {
        final String movingPiece = getPiece(source);
        deletePiece(source);
        setPiece(target, movingPiece);
    }

    private String getPiece(final Square square) throws SQLException {
        final String query = "SELECT * FROM board WHERE square = ?";
        final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, square.toString());
        final ResultSet set = statement.executeQuery();
        set.next();
        return set.getString("piece");
    }

    private void deletePiece(final Square square) throws SQLException {
        final String query = "DELETE FROM board WHERE square = ?";
        final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, square.toString());
        statement.executeUpdate();
    }

    private void setPiece(final Square square, final String pieceString) throws SQLException {
        deletePiece(square);
        final String query = "INSERT INTO board (square, piece) VALUES (?, ?)";
        final PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, square.toString());
        statement.setString(2, pieceString);
        statement.executeUpdate();
    }
}
