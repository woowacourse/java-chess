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

import static chess.service.TeamFormat.BLACK_TEAM;
import static chess.service.TeamFormat.WHITE_TEAM;

public class ChessGameDAO {
    public Connection getConnection() {
        Connection con = null;
        final String server = "localhost:13306"; // MySQL 서버 주소
        final String database = "chess_db"; // MySQL DATABASE 이름
        final String option = "?useSSL=false&serverTimezone=UTC";
        final String userName = "root"; //  MySQL 서버 아이디
        final String password = "root"; // MySQL 서버 비밀번호

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

    public void createChessGame(final ChessGame chessGame, final String currentTurnTeam) throws SQLException {
        createTeamInfo(chessGame.currentWhitePiecePosition(), WHITE_TEAM.asDAOFormat());
        createTeamInfo(chessGame.currentBlackPiecePosition(), BLACK_TEAM.asDAOFormat());
        final String query = "INSERT INTO chess_game VALUES (?, ?)";
        final Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, currentTurnTeam);
        pstmt.setBoolean(2, chessGame.isPlaying());
        pstmt.executeUpdate();
        pstmt.close();
        connection.close();
    }

    private void createTeamInfo(final Map<Position, Piece> teamPiecePosition, final String team) throws SQLException {
        final String query = "INSERT INTO team_info VALUES (?, ?)";
        final Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, team);
        pstmt.setString(2, PieceInfoToDAO.convert(teamPiecePosition));
        pstmt.executeUpdate();
        pstmt.close();
        connection.close();
    }

    public ChessGame readChessGame() throws SQLException {
        final Team blackTeam = readTeamInfo(BLACK_TEAM.asDAOFormat());
        final Team whiteTeam = readTeamInfo(WHITE_TEAM.asDAOFormat());
        return generateChessGame(blackTeam, whiteTeam);
    }

    private Team readTeamInfo(final String team) throws SQLException {
        final String teamQuery = "SELECT * FROM team_info where team = (?)";
        final Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(teamQuery);
        pstmt.setString(1, team);
        ResultSet ResultSet = pstmt.executeQuery();
        final Team generatedTeam = generateTeam(ResultSet, team);
        pstmt.close();
        connection.close();
        return generatedTeam;
    }

    private Team generateTeam(final ResultSet resultSet, final String team) throws SQLException {
        Map<Position, Piece> piecePosition = new HashMap<>();
        while (resultSet.next()) {
            final String pieceInfo = resultSet.getString(2);
            System.out.println("pieceInfo = " + pieceInfo);
            piecePosition = DAOtoPieceInfo.convert(pieceInfo, team);
        }
        final PiecePosition PiecePositionByTeam = new PiecePosition(piecePosition);
        return new Team(PiecePositionByTeam, new PieceCaptured(), new Score());
    }

    private ChessGame generateChessGame(final Team blackTeam, final Team whiteTeam) throws SQLException {
        final String chessGameQuery = "SELECT * FROM chess_game";
        final Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(chessGameQuery);
        ResultSet ResultSet = pstmt.executeQuery();
        String currentTurnTeam = "";
        boolean isPlaying = true;
        while (ResultSet.next()) {
            currentTurnTeam = ResultSet.getString(1);
            isPlaying = ResultSet.getBoolean(2);
        }
        pstmt.close();
        connection.close();
        return generateChessGameAccordingToDB(blackTeam, whiteTeam, currentTurnTeam, isPlaying);
    }

    private ChessGame generateChessGameAccordingToDB(final Team blackTeam, final Team whiteTeam,
                                                     final String currentTurnTeam, final boolean isPlaying) {
        if (WHITE_TEAM.asDAOFormat().equals(currentTurnTeam)) {
            return new ChessGame(blackTeam, whiteTeam, whiteTeam, isPlaying);
        }
        return new ChessGame(blackTeam, whiteTeam, blackTeam, isPlaying);
    }

    public void updateChessGame(final ChessGame chessGame, final String currentTurnTeam) throws SQLException {
        updateTeamInfo(chessGame.currentWhitePiecePosition(), WHITE_TEAM.asDAOFormat());
        updateTeamInfo(chessGame.currentBlackPiecePosition(), BLACK_TEAM.asDAOFormat());
        final String query = "UPDATE chess_game SET current_turn_team = (?), is_playing = (?)";
        final Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, currentTurnTeam);
        pstmt.setBoolean(2, chessGame.isPlaying());
        pstmt.executeUpdate();
        System.out.println("pstmt = " + pstmt);
        pstmt.close();
        connection.close();
    }

    private void updateTeamInfo(final Map<Position, Piece> teamPiecePosition, final String team) throws SQLException {
        final String query = "UPDATE team_info SET piece_info = (?) WHERE team = (?)";
        final Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, PieceInfoToDAO.convert(teamPiecePosition));
        pstmt.setString(2, team);
        System.out.println("pstmt = " + pstmt);
        pstmt.executeUpdate();
        pstmt.close();
        connection.close();
    }

    public void deleteChessGame() throws SQLException {
        final String deletePiecePositionQuery = "DELETE FROM team_info";
        final String deleteChessGameQuery = "DELETE FROM chess_game";
        final Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(deletePiecePositionQuery);
        pstmt.executeUpdate();
        pstmt.close();
        pstmt = connection.prepareStatement(deleteChessGameQuery);
        pstmt.executeUpdate();
        pstmt.close();
        connection.close();
    }
}
