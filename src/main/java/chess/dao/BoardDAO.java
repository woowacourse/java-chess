package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Row;
import chess.domain.chesspiece.*;
import chess.domain.game.GameStatus;
import chess.domain.game.Team;
import chess.domain.move.Coordinate;
import chess.domain.move.Position;
import chess.factory.BoardFactory;

import java.sql.*;
import java.util.List;

public class BoardDAO {
    Connection connection = getConnection();

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "chess"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    // 드라이버 연결해제
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
        String query = "truncate table board";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.executeUpdate();
    }

    private void initializeGameStatus() throws SQLException {
        String query = "truncate table game_status";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.executeUpdate();
    }

    public void addToBoard(ChessPiece chessPiece, Position position) throws SQLException {
        String query = "INSERT INTO board VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, chessPiece.getName());
        pstmt.setString(2, chessPiece.getTeam().getTeamName());
        pstmt.setString(3, String.valueOf(position.getX()));
        pstmt.setString(4, String.valueOf(position.getY()));
        pstmt.executeUpdate();
    }

    public Board loadBoard() throws SQLException {
        String query = "SELECT * FROM board";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        Board loadBoard = BoardFactory.createBlankBoard(loadGameStatus());

        while (rs.next()) {
            loadBoard.setPosition(getNewChessPiece(rs.getString("piece_name"), rs.getString("team"), rs.getInt("x_position")), Position.of(Coordinate.of(rs.getInt("x_position")), Coordinate.of(rs.getInt("y_position"))));
        }
        return loadBoard;
    }

    private ChessPiece getNewChessPiece(String piece_name, String team, int x_position) {
        if (piece_name.toLowerCase().equals("k")) {
            return new King(Team.of(team));
        }
        if (piece_name.toLowerCase().equals("q")) {
            return new Queen(Team.of(team));
        }
        if (piece_name.toLowerCase().equals("r")) {
            return new Rook(Team.of(team));
        }
        if (piece_name.toLowerCase().equals("b")) {
            return new Bishop(Team.of(team));
        }
        if (piece_name.toLowerCase().equals("n")) {
            return new Knight(Team.of(team));
        }
        if (piece_name.toLowerCase().equals("p")) {
            Pawn pawn = new Pawn(Team.of(team));
            if (!((Team.BLACK.getTeamName().equals(team) && x_position == 7) || (Team.WHITE.getTeamName().equals(team) && x_position == 2))) {
                pawn.firstMoveComplete();
            }
            return pawn;
        }
        return new Blank();
    }

    public GameStatus loadGameStatus() throws SQLException {
        String query = "SELECT * FROM game_status";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        rs.next();
        return new GameStatus(Team.of(rs.getString("now_playing_team")), Boolean.valueOf(rs.getString("is_game_end")));
    }

    public void updateDB(Board board) throws SQLException {
        initialize();
        updateBoard(board);
        updateGameStatus(board.getGameStatus());
    }

    private void updateBoard(Board board) throws SQLException {
        List<Row> rows = board.getBoard();
        for (int i = 0; i < rows.size(); i++) {
            List<ChessPiece> chessPieces = rows.get(i).getChessPieces();
            for (int j = 0; j < chessPieces.size(); j++) {
                ChessPiece chessPiece = chessPieces.get(j);
                if (!(chessPiece instanceof Blank)) {
                    String query = "INSERT INTO board VALUES (?, ?, ?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, chessPiece.getName());
                    pstmt.setString(2, chessPiece.getTeam().getTeamName());
                    pstmt.setString(3, String.valueOf(i + 1));
                    pstmt.setString(4, String.valueOf(j + 1));
                    pstmt.executeUpdate();
                }
            }
        }
    }

    private void updateGameStatus(GameStatus gameStatus) throws SQLException {
        String query = "INSERT INTO game_status VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, gameStatus.getNowPlayingTeam().getTeamName());
        pstmt.setString(2, String.valueOf(gameStatus.isGameEnd()));
        pstmt.executeUpdate();
    }
}
