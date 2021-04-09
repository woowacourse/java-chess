package chess.dao;

import chess.dto.ChessGameDto;
import chess.dto.CommandsDto;
import chess.dto.CreateRequestDto;
import chess.dto.PlayerIdsDto;
import chess.dto.SquareDto;
import chess.dto.UserIdsDto;
import chess.exception.DataAccessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessDao {

    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306"; // MySQL 서버 주소
        String database = "db_name"; // MySQL DATABASE 이름
        String option = "?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
        String userName = "root"; //  MySQL 서버 아이디
        String password = "root"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            throw new DataAccessException("데이터베이스 드라이버 로딩에 에러가 발생했습니다.");
        }

        // 드라이버 연결
        try {
            con = DriverManager
                .getConnection("jdbc:mysql://" + server + "/" + database + option, userName,
                    password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            throw new DataAccessException("데이터베이스 드라이버 연결에 에러가 발생했습니다.");
        }

        return con;
    }

    // 드라이버 연결해제
    public void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            throw new DataAccessException("데이터베이스 드라이버 연결해제에 에러가 발생했습니다.");
        }
    }

    public List<String> runningGameNames() throws SQLException {
        List<String> gameNames = new ArrayList<>();
        String query = "SELECT game_name FROM game WHERE state='흑색 차례' OR state='백색 차례' ORDER BY game_id DESC";

        try (PreparedStatement pstmt = getConnection().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()) {
            gameNames(gameNames, rs);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }

        return gameNames;
    }

    private void gameNames(final List<String> ids, final ResultSet rs) throws SQLException {
        while (rs.next()) {
            ids.add(rs.getString("game_name"));
        }
    }

    public void createGameByName(final CreateRequestDto createRequestDto,
        final ChessGameDto chessGameDto) {
        String query = "INSERT INTO game(game_name, state) VALUES(?, '게임 시작 전')";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, createRequestDto.getGameName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

        insertBoard(findGameIdByName(createRequestDto.getGameName()), chessGameDto.getSquareDtos());
    }

    public void updateGameByName(final String gameName, final ChessGameDto chessGameDto)
        throws SQLException {
        String gameId = findGameIdByName(gameName);
        updateGameState(gameId, chessGameDto.getState());
        deleteBoard(gameId);
        insertBoard(gameId, chessGameDto.getSquareDtos());
    }

    private void updateGameState(final String gameId, final String state) throws SQLException {
        String query = "UPDATE game SET state=? WHERE game_id=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, state);
            pstmt.setString(2, gameId);
            pstmt.executeUpdate();
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteBoard(final String gameId) throws SQLException {
        String query = "DELETE FROM board WHERE game_id=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, gameId);
            pstmt.executeUpdate();
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertBoard(final String gameId, final List<SquareDto> squareDtos) {
        for (SquareDto squareDto : squareDtos) {
            insertSquare(gameId, squareDto);
        }
    }

    private void insertSquare(final String gameId, final SquareDto squareDto) {
        String query = "INSERT INTO board(game_id, symbol, position) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, gameId);
            pstmt.setString(2, squareDto.getPiece());
            pstmt.setString(3, squareDto.getPosition());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public String findStateByName(final String gameName) {
        String query = "SELECT state FROM game WHERE game_id=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, findGameIdByName(gameName));
            ResultSet rs = pstmt.executeQuery();
            validateResultSet(rs);
            return rs.getString("state");
        } catch (DataAccessException | SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public List<SquareDto> findSquaresByName(final String gameName) {
        String query = "SELECT symbol, position FROM board WHERE game_id=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, findGameIdByName(gameName));
            try (ResultSet rs = pstmt.executeQuery()) {
                return squareDtos(rs);
            }
        } catch (DataAccessException | SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    private List<SquareDto> squareDtos(final ResultSet resultSet) throws SQLException {
        List<SquareDto> squareDtos = new ArrayList<>();
        while (resultSet.next()) {
            String symbol = resultSet.getString("symbol");
            String position = resultSet.getString("position");
            squareDtos.add(new SquareDto(position, symbol));
        }
        return squareDtos;
    }

    public ChessGameDto findGameByName(final String gameName) {
        List<SquareDto> squareDtos = findSquaresByName(gameName);
        String state = findStateByName(gameName);
        return new ChessGameDto(squareDtos, state);
    }

    public void updatePlayerIds(final PlayerIdsDto playerIdsDto, final String gameName)
        throws SQLException {
        String query = "UPDATE game SET white_user=?, black_user=? WHERE game_id=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, playerIdsDto.getWhiteUserId());
            pstmt.setString(2, playerIdsDto.getBlackUserId());
            pstmt.setString(3, findGameIdByName(gameName));
            pstmt.executeUpdate();
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    private String findGameIdByName(final String gameName) {
        String query = "SELECT game_id FROM game WHERE game_name=?";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, gameName);
            ResultSet rs = pstmt.executeQuery();
            validateResultSet(rs);
            return rs.getString("game_id");
        } catch (DataAccessException | SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    private void validateResultSet(final ResultSet rs) throws SQLException {
        if (!rs.next()) {
            throw new SQLException("game id 검색 결과가 존재하지 않습니다.");
        }
    }

    private List<String> findGameIdByUserId(final String userId) throws SQLException {
        String query = "SELECT game_id FROM game WHERE white_user=? OR black_user=? ORDER BY game_id DESC";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, userId);
        pstmt.setString(2, userId);
        ResultSet rs = pstmt.executeQuery();

        List<String> gameIds = new ArrayList<>();
        while (rs.next()) {
            gameIds.add(rs.getString("game_id"));
        }

        return gameIds;
    }

    private void findCommandsByUserId(String gameId, List<CommandsDto> commandsDtos)
        throws SQLException {
        String query = "SELECT command FROM history WHERE game_id=? ORDER BY history_id";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, gameId);
        ResultSet rs = pstmt.executeQuery();

        List<String> commands = new ArrayList<>();
        while (rs.next()) {
            commands.add(rs.getString("command"));
        }

        commandsDtos.add(new CommandsDto(commands));
    }

    public List<UserIdsDto> findUserIdsByUserId(final UserIdsDto userIdsDto) throws SQLException {
        String query = "SELECT white_user, black_user FROM game WHERE white_user=? OR black_user=? ORDER BY game_id DESC";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, userIdsDto.getWhiteUserId());
        pstmt.setString(2, userIdsDto.getBlackUserId());
        ResultSet rs = pstmt.executeQuery();

        List<UserIdsDto> userIdsDtos = new ArrayList<>();
        while (rs.next()) {
            userIdsDtos.add(new UserIdsDto(rs.getString("white_user"), rs.getString("black_user")));
        }

        return userIdsDtos;
    }

    public List<ChessGameDto> findGamesByUserId(final UserIdsDto userIdsDto) throws SQLException {
        String query = "SELECT game_name FROM game WHERE white_user=? OR black_user=? ORDER BY game_id DESC";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, userIdsDto.getWhiteUserId());
        pstmt.setString(2, userIdsDto.getBlackUserId());
        ResultSet rs = pstmt.executeQuery();

        List<ChessGameDto> chessGameDtos = new ArrayList<>();
        while (rs.next()) {
            chessGameDtos.add(findGameByName(rs.getString("game_name")));
        }

        return chessGameDtos;
    }
}
