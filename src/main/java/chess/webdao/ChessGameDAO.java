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
        createPiecePosition(chessGame.currentWhitePiecePosition(), WHITE_TEAM.asDAOFormat());
        createPiecePosition(chessGame.currentBlackPiecePosition(), BLACK_TEAM.asDAOFormat());
        final String query = "INSERT INTO chess_game VALUES (?, ?)";
        final Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, currentTurnTeam);
        pstmt.setBoolean(2, chessGame.isPlaying());
        pstmt.executeUpdate();
        pstmt.close();
        connection.close();
    }

    private void createPiecePosition(final Map<Position, Piece> piecePosition, final String team) throws SQLException {
        for (Position position : piecePosition.keySet()) {
            final Piece pieceToSave = piecePosition.get(position);
            String query = "INSERT INTO piece_position VALUES (?, ?, ?, ?)";
            final Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, team);
            pstmt.setString(2, PieceToDAO.convert(pieceToSave));
            pstmt.setString(3, position.getPositionInitial());
            pstmt.setBoolean(4, pieceToSave.isFirstMove());
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        }
    }

    public ChessGame readChessGame() throws SQLException {
        final Team blackTeam = readPiecePositionByTeam(BLACK_TEAM.asDAOFormat());
        final Team whiteTeam = readPiecePositionByTeam(WHITE_TEAM.asDAOFormat());
        return generateChessGame(blackTeam, whiteTeam);
    }

    private Team readPiecePositionByTeam(final String teamAsString) throws SQLException {
        final String teamQuery = "SELECT * FROM piece_position where team = (?)";
        final Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(teamQuery);
        pstmt.setString(1, teamAsString);
        ResultSet ResultSet = pstmt.executeQuery();
        final Team team = generateTeam(ResultSet);
        pstmt.close();
        connection.close();
        return team;
    }

    private Team generateTeam(final ResultSet resultSet) throws SQLException {
        final Map<Position, Piece> piecePosition = new HashMap<>();
        while (resultSet.next()) {
            final String team = resultSet.getString(1);
            final String pieceAsString = resultSet.getString(2);
            final String positionAsString = resultSet.getString(3);
            final boolean isFirstMove = resultSet.getBoolean(4);
            piecePosition.put(Position.of(positionAsString), DAOtoPiece.generatePiece(team, pieceAsString, isFirstMove));
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

    public void deleteChessGame() throws SQLException {
        final String deletePiecePositionQuery = "DELETE FROM piece_position";
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
