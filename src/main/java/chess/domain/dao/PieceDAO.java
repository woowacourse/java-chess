package chess.domain.dao;

import chess.domain.board.Board;
import chess.domain.dto.PieceDto;
import chess.domain.dto.PiecesDto;
import chess.domain.game.BlackTurn;
import chess.domain.game.ChessGame;
import chess.domain.game.State;
import chess.domain.game.WhiteTurn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PieceDAO {

    private static Connection con = getConnection();

    public static Connection getConnection() {
    Connection con = null;
    String server = "localhost:13306"; // MySQL 서버 주소
    String database = "chess_game"; // MySQL DATABASE 이름
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

    public static boolean save(final Piece piece) {
        String color = piece.getColor().toString();
        String shape = piece.getShape().toString();
        if("WHITE".equals(color)){
            shape = shape.toLowerCase();
        }
        try{
            String query = "INSERT INTO pieces(color, shape, position) VALUES(?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, color);
            preparedStatement.setString(2, shape);
            preparedStatement.setString(3, piece.getPosition().toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean saveAll(List<Piece> pieces) {
        deleteAll();
        for(Piece piece : pieces) {
            if(!save(piece)){
                return false;
            }
        }
        return true;
    }

    private static void deleteAll() {
        try{
            String query = "DELETE FROM pieces";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static Board loadPieces() {
        try{
            String query = "SELECT * FROM pieces";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            List<Piece> pieces = new ArrayList<>();

            while(rs.next()) {
                String color = rs.getString("color");
                String shape = rs.getString("shape");
                String position = rs.getString("position");
                System.out.println("color : " + color + " shape : " + shape + " position : " + position);

                pieces.add(PieceFactory.createPiece(color, shape, new Position(position)));
            }
            return new Board(pieces);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static State loadTurn(ChessGame chessGame) {
        try{
            String query = "SELECT * FROM turn";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            String turn = "";
            while(rs.next()) {
                turn = rs.getString("turn");
            }
            if("black".equals(turn)){
                return new BlackTurn(chessGame);
            }
            return new WhiteTurn(chessGame);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static boolean saveTurn(String status) {
        deleteTurn();
        try {
            String query = "INSERT INTO turn(turn) values (?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    private static void deleteTurn() {
        try{
            String query = "DELETE FROM turn";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
