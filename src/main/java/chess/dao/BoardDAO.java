package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Row;
import chess.domain.chesspiece.*;
import chess.domain.game.GameStatus;
import chess.domain.game.Team;
import chess.domain.move.Coordinate;
import chess.domain.move.Position;

import java.sql.*;
import java.util.List;

import static chess.dao.ServerInfo.*;
import static chess.domain.chesspiece.ChessPieceInfo.*;

public class BoardDAO {
    public Connection getConnection() {
        loadDriver();
        return connectDriver();
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connectDriver() {
        Connection connection = null;

        try {
            String url = "jdbc:mysql://" + SERVER.getData() + "/" + DATABASE.getData() + OPTION.getData();
            connection = DriverManager.getConnection(url, USER_NAME.getData(), PASSWORD.getData());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void initialize() throws SQLException {
        initializeBoard();
        initializeGameStatus();
    }

    private void initializeBoard() throws SQLException {
        Connection connection = getConnection();
        String query = "truncate table board";
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.executeUpdate();
        pstmt.close();
        closeConnection(connection);
    }

    private void initializeGameStatus() throws SQLException {
        Connection connection = getConnection();
        String query = "truncate table game_status";
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.executeUpdate();
        pstmt.close();
        closeConnection(connection);
    }

    public Board loadBoard() throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT * FROM board";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        Board board = BoardFactory.createBlankBoard(loadGameStatus());

        loadChessPieces(rs, board);
        pstmt.close();
        closeConnection(connection);
        return board;
    }

    //should extract
    private void loadChessPieces(ResultSet rs, Board board) throws SQLException {
        while (rs.next()) {
            String pieceName = rs.getString("piece_name");
            String teamName = rs.getString("team");
            int x = rs.getInt("x_position");
            int y = rs.getInt("y_position");
            Position position = Position.of(Coordinate.of(x), Coordinate.of(y));
            ChessPiece chessPiece = getNewChessPiece(pieceName, teamName, position);

            board.setPosition(chessPiece, position);
        }
    }

    //should extract
    private ChessPiece getNewChessPiece(String pieceName, String teamName, Position position) {
        String lowerPieceName = pieceName.toLowerCase();

        if (lowerPieceName.equals(KING.getName())) {
            return new King(Team.of(teamName));
        }
        if (lowerPieceName.equals(QUEEN.getName())) {
            return new Queen(Team.of(teamName));
        }
        if (lowerPieceName.equals(ROOK.getName())) {
            return new Rook(Team.of(teamName));
        }
        if (lowerPieceName.equals(BISHOP.getName())) {
            return new Bishop(Team.of(teamName));
        }
        if (lowerPieceName.equals(KNIGHT.getName())) {
            return new Knight(Team.of(teamName));
        }
        if (lowerPieceName.equals(PAWN.getName())) {
            Pawn pawn = new Pawn(Team.of(teamName));

            pawn.updateFirstMove(teamName, position);
            return pawn;
        }
        return new Blank();
    }

    //should extract some part
    public GameStatus loadGameStatus() throws SQLException {
        Connection connection = getConnection();
        String query = "SELECT * FROM game_status";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        Team nowPlayingTeam;
        boolean isGameEnd;

        rs.next();
        nowPlayingTeam = Team.of(rs.getString("now_playing_team"));
        isGameEnd = Boolean.parseBoolean(rs.getString("is_game_end"));
        pstmt.close();
        closeConnection(connection);
        return new GameStatus(nowPlayingTeam, isGameEnd);
    }

    //should extract
    public void updateDB(Board board) throws SQLException {
        initialize();
        updateBoard(board);
        updateGameStatus(board.getGameStatus());
    }

    //should extract
    private void updateBoard(Board board) throws SQLException {
        List<Row> rows = board.getBoard();

        for (int i = 0; i < rows.size(); i++) {
            List<ChessPiece> chessPieces = rows.get(i).getChessPieces();

            updateRow(chessPieces, i);
        }
    }

    //should extract
    private void updateRow(List<ChessPiece> chessPieces, int i) throws SQLException {
        for (int j = 0; j < chessPieces.size(); j++) {
            ChessPiece chessPiece = chessPieces.get(j);

            updateColumn(chessPiece, i, j);
        }
    }

    //check
    private void updateColumn(ChessPiece chessPiece, int i, int j) throws SQLException {
        Connection connection = getConnection();
        String query = "INSERT INTO board VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);

        if (!(chessPiece instanceof Blank)) {
            pstmt.setString(1, chessPiece.getName());
            pstmt.setString(2, chessPiece.getTeam().getTeamName());
            pstmt.setString(3, String.valueOf(i + 1));
            pstmt.setString(4, String.valueOf(j + 1));
            pstmt.executeUpdate();
        }
        pstmt.close();
        closeConnection(connection);
    }

    private void updateGameStatus(GameStatus gameStatus) throws SQLException {
        Connection connection = getConnection();
        String query = "INSERT INTO game_status VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);

        pstmt.setString(1, gameStatus.getNowPlayingTeam().getTeamName());
        pstmt.setString(2, String.valueOf(gameStatus.isGameEnd()));
        pstmt.executeUpdate();
        pstmt.close();
        closeConnection(connection);
    }
}
