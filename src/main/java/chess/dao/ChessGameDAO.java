package chess.dao;

import chess.domain.game.ChessGameEntity;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Position;
import chess.exception.NotFoundChessGameException;
import chess.exception.NotFoundPieceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDAO {

    private final ConnectionFactory factory;

    public ChessGameDAO() {
        factory = new ConnectionFactory();
    }

    public ChessGameEntity findLatestOne() throws SQLException {
        try (Connection con = factory.getConnection()) {
            String query = "SELECT * FROM chess_games ORDER BY id DESC LIMIT 1";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                throw new NotFoundChessGameException();
            }

            return new ChessGameEntity(rs.getLong("id"),rs.getString("state"));

            //todo: 나중에 코드 가져오기!!
//            long id = rs.getLong("id");
//            Board board = new Board(findAllPiecesByChessGame(id));
//            ChessGame chessGame = new ChessGame(board);
//            chessGame.changeState(StateFactory.valueOf(rs.getString("state"), chessGame));
//            return chessGame;
        }

    }

    public void create() throws SQLException {
        System.out.println("실행전");
        try (Connection con = factory.getConnection()) {
            String query = "INSERT INTO chess_games(state) VALUES(?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, "Ready");
            preparedStatement.executeUpdate();
        }
        System.out.println("실행후");
    }

    public void deleteById(Long id) throws SQLException {
        try (Connection con = factory.getConnection()) {
            String query = "DELETE FROM chess_games WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

}
