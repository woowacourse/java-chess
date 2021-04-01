package chess.webdao;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.team.PieceCaptured;
import chess.domain.team.PiecePosition;
import chess.domain.team.Score;
import chess.domain.team.Team;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ChessGameDAO {
    private static final String WHITE_TEAM = "white";
    private static final String BLACK_TEAM = "black";

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "chess_db"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    public void createChessGame(final ChessGame chessGame, final String currentTurnTeam) {
        try {
            createPiecePosition(chessGame.currentWhitePiecePosition(), WHITE_TEAM);
            createPiecePosition(chessGame.currentBlackPiecePosition(), BLACK_TEAM);
            String query = "INSERT INTO chess_game VALUES (?, ?)";
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, currentTurnTeam);
            pstmt.setBoolean(2, chessGame.isPlaying());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("DB 오류:" + e.getMessage());
        }
    }

    private void createPiecePosition(final Map<Position, Piece> piecePosition, final String team) throws SQLException {
        for (Position position : piecePosition.keySet()) {
            final Piece pieceToSave = piecePosition.get(position);
            String query = "INSERT INTO piece_position VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, team);
            pstmt.setString(2, PieceToDAO.convert(pieceToSave));
            pstmt.setString(3, position.getPositionInitial());
            pstmt.setBoolean(4, pieceToSave.isFirstMove());
            pstmt.executeUpdate();
        }
    }

    public ChessGame readChessGame() {
        try {
            final String blackTeamQuery = "SELECT * FROM piece_position where team = 'black'";
            Team blackTeam = readPiecePositionByTeam(blackTeamQuery);
            final String whiteTeamQuery = "SELECT * FROM piece_position where team = 'white'";
            Team whiteTeam = readPiecePositionByTeam(whiteTeamQuery);
            return generateChessGame(blackTeam, whiteTeam);
        } catch (SQLException e) {
            System.err.println("DB 오류:" + e.getMessage());
            return null;
        }
    }

    private Team readPiecePositionByTeam(String teamQuery) throws SQLException {
        PreparedStatement pstmt = getConnection().prepareStatement(teamQuery);
        ResultSet ResultSet = pstmt.executeQuery();
        final Map<Position, Piece> piecePosition = new HashMap<>();
        while (ResultSet.next()) {
            final String team = ResultSet.getString(1);
            final String pieceAsString = ResultSet.getString(2);
            final String positionAsString = ResultSet.getString(3);
            final boolean isFirstMove = ResultSet.getBoolean(4);
            piecePosition.put(Position.of(positionAsString), DAOtoPiece.generatePiece(team, pieceAsString, isFirstMove));
        }
        final PiecePosition PiecePositionByTeam = new PiecePosition(piecePosition);
        return new Team(PiecePositionByTeam, new PieceCaptured(), new Score());
    }

    private ChessGame generateChessGame(final Team blackTeam, final Team whiteTeam) throws SQLException {
        final String chessGameQuery = "SELECT * FROM chess_game";
        PreparedStatement pstmt = getConnection().prepareStatement(chessGameQuery);
        ResultSet ResultSet = pstmt.executeQuery();
        String currentTurnTeam = "";
        boolean isPlaying = true;
        while (ResultSet.next()) {
            currentTurnTeam = ResultSet.getString(1);
            isPlaying = ResultSet.getBoolean(2);
        }
        return generateChessGameAccordingToDB(blackTeam, whiteTeam, currentTurnTeam, isPlaying);
    }

    private ChessGame generateChessGameAccordingToDB(final Team blackTeam, final Team whiteTeam,
                                                     final String currentTurnTeam, final boolean isPlaying) {
        if ("white".equals(currentTurnTeam)) {
            return new ChessGame(blackTeam, whiteTeam, whiteTeam, isPlaying);
        }
        return new ChessGame(blackTeam, whiteTeam, blackTeam, isPlaying);
    }

    public void deleteChessGameDB() {
        final String deletePiecePositionQuery = "DELETE FROM piece_position";
        final String deleteChessGameQuery = "DELETE FROM chess_game";
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(deletePiecePositionQuery);
            pstmt.executeUpdate();
            pstmt = getConnection().prepareStatement(deleteChessGameQuery);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
