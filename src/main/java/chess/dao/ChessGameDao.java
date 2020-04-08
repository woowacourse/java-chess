package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.game.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.game.state.Finished;
import chess.domain.game.state.Playing;
import chess.domain.game.state.Ready;
import chess.domain.game.state.State;
import chess.dto.BoardDto;
import chess.dto.TurnDto;

public class ChessGameDao implements JdbcTemplateDao {

    private static final String STATE = "state";
    private static final String READY = "READY";
    private static final String PLAYING = "PLAYING";
    private static final String FINISHED = "FINISHED";

    public ChessGame save() throws SQLException {
        String query = "INSERT INTO chess_game(state) VALUES (?)";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query, new String[] {"id"});
        pstmt.setString(1, READY);
        pstmt.executeUpdate();
        ResultSet resultSet = pstmt.getGeneratedKeys();
        if (!resultSet.next()) {
            closeConnection(connection);
            return null;
        }
        int id = resultSet.getInt(1);
        closeConnection(connection);
        return new ChessGame(id, new Ready());
    }

    public List<Integer> selectAll() throws SQLException {
        String query = "SELECT id FROM chess_game";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        List<Integer> ids = new ArrayList<>();
        while (rs.next()) {
            ids.add(rs.getInt("id"));
        }
        closeConnection(connection);
        return ids;
    }

    public ChessGame findById(int id) throws SQLException {
        String query = "SELECT * FROM chess_game WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            closeConnection(connection);
            return null;
        }

        State state = new Ready();
        if (rs.getString(STATE).equals(PLAYING)) {
            state = new Playing(Board.from(rs.getString("board")), Turn.from(rs.getString("turn")));
        }
        if (rs.getString(STATE).equals(FINISHED)) {
            state = new Finished(Board.from(rs.getString("board")), Turn.from(rs.getString("turn")));
        }
        closeConnection(connection);
        return new ChessGame(id, state);
    }

    public void update(ChessGame chessGame) throws SQLException {
        String query = "UPDATE chess_game SET state=?,board=?,turn=? WHERE id=?";
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(query);
        BoardDto boardDto = new BoardDto(chessGame.board());
        TurnDto turnDto = new TurnDto(chessGame.turn());
        String state = chessGame.getState().toString();
        pstmt.setString(1, state);
        pstmt.setString(2, String.join("", boardDto.getBoard()));
        pstmt.setString(3, turnDto.getTurn().getColor().toString());
        pstmt.setInt(4, chessGame.getId());
        pstmt.executeUpdate();
        closeConnection(connection);
    }
}
