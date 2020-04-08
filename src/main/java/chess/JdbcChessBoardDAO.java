package chess;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.ChessPieceCache;
import chess.domain.position.Position;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class JdbcChessBoardDAO {

    private static final String POSITION_COLUMN = "position";
    private static final String CHESS_PIECE_COLUMN = "chessPiece";
    private static final String WHITE_INITIAL_BOARD = "('a2','P')," +
            "('b2','P')," +
            "('c2','P')," +
            "('d2','P')," +
            "('e2','P')," +
            "('f2','P')," +
            "('g2','P')," +
            "('h2','P')," +
            "('a1','R')," +
            "('b1','N')," +
            "('c1','B')," +
            "('d1','Q')," +
            "('e1','K')," +
            "('f1','B')," +
            "('g1','N')," +
            "('h1','R')";
    private static final String BLACK_INITIAL_BOARD = "('a7','p')," +
            "('b7','p')," +
            "('c7','p')," +
            "('d7','p')," +
            "('e7','p')," +
            "('f7','p')," +
            "('g7','p')," +
            "('h7','p')," +
            "('a8','r')," +
            "('b8','n')," +
            "('c8','b')," +
            "('d8','q')," +
            "('e8','k')," +
            "('f8','b')," +
            "('g8','n')," +
            "('h8','r')";

    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public Connection getConnection() {
        String server = "localhost:3306";
        String database = "chessBoard";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "1234";

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

    private void closeConnection() {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

    private void closeResultSet() {
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            System.err.println("ResultSet 오류:" + e.getMessage());
        }
    }

    private void closeStatement() {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            System.err.println("Statement 오류:" + e.getMessage());
        }
    }

    private void closePrepareStatement() {
        try {
            if (preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("preparedStatement 오류:" + e.getMessage());
        }
    }

    public void initialChessBoard() {
        deleteWhiteTable();
        deleteBlackTable();
        deletePlayerTurn();
        initialPlayerTurn();
        insertBoard();
    }

    private void deletePlayerTurn() {
        try {
            String dropQuery = "DELETE FROM playerTurn";
            statement = getConnection().createStatement();
            statement.executeUpdate(dropQuery);

        } catch (SQLException e) {
            System.err.println("deletePlayerTurn 오류:" + e.getMessage());
        } finally {
            closeStatement();
        }
    }

    private void deleteWhiteTable() {
        try {
            String dropQuery = "DELETE FROM white";
            statement = getConnection().createStatement();
            statement.executeUpdate(dropQuery);

        } catch (SQLException e) {
            System.err.println("deleteWhiteTable 오류:" + e.getMessage());
        } finally {
            closeStatement();
        }
    }

    private void deleteBlackTable() {
        try {
            String dropQuery = "DELETE FROM black";
            statement = getConnection().createStatement();
            statement.executeUpdate(dropQuery);

        } catch (SQLException e) {
            System.err.println("deleteBlackTable 오류:" + e.getMessage());
        } finally {
            closeStatement();
        }
    }

    private void insertBoard() {
        String setWhitePieceQuery = "INSERT INTO white VALUES " +
                WHITE_INITIAL_BOARD;

        String setBlackPieceQuery = "INSERT INTO black VALUES " +
                BLACK_INITIAL_BOARD;

        try {
            con = getConnection();
            statement = con.createStatement();
            statement.executeUpdate(setWhitePieceQuery);
            statement = con.createStatement();
            statement.executeUpdate(setBlackPieceQuery);

        } catch (SQLException e) {
            System.err.println("insertBoard 오류: " + e.getMessage());
        } finally {
            closeConnection();
            closeStatement();
        }

    }

    public Map<Position, ChessPiece> setBoard() {
        Map<Position, ChessPiece> chessBoard = new HashMap<>();
        selectBlackBoard(chessBoard);
        selectWhiteBoard(chessBoard);

        return chessBoard;
    }

    private void selectWhiteBoard(Map<Position, ChessPiece> chessBoard) {
        String setWhiteQuery = "SELECT * FROM white";

        try {
            con = getConnection();
            statement = con.createStatement();

            resultSet = statement.executeQuery(setWhiteQuery);
            while (resultSet.next()) {
                chessBoard.put(Position.of(resultSet.getString(POSITION_COLUMN)),
                        ChessPieceCache.getChessPiece(resultSet.getString(CHESS_PIECE_COLUMN)
                                , resultSet.getString(POSITION_COLUMN)));
            }
        } catch (SQLException e) {
            System.err.println("selectWhiteBoard 오류: " + e.getMessage());

        } finally {
            closeConnection();
            closeResultSet();
            closeStatement();
        }
    }

    private void selectBlackBoard(Map<Position, ChessPiece> chessBoard) {
        String setBlackQuery = "SELECT * FROM black";

        try {
            con = getConnection();
            statement = con.createStatement();

            resultSet = statement.executeQuery(setBlackQuery);
            while (resultSet.next()) {
                chessBoard.put(Position.of(resultSet.getString(POSITION_COLUMN)),
                        ChessPieceCache.getChessPiece(resultSet.getString(CHESS_PIECE_COLUMN)
                                , resultSet.getString(POSITION_COLUMN)));
            }
        } catch (SQLException e) {
            System.err.println("selectBlackBoard 오류: " + e.getMessage());

        } finally {
            closeConnection();
            closeStatement();
            closeResultSet();
        }
    }


    public void updatePiece(ChessBoardDTO chessBoardDTO) {
        String query = "UPDATE " + chessBoardDTO.getPieceColor().getColor() + " SET position = ? WHERE position = ?";
        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, chessBoardDTO.getTargetPosition());
            preparedStatement.setString(2, chessBoardDTO.getSourcePosition());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("updatePiece 오류: " + e.getMessage());
        } finally {
            closeConnection();
            closePrepareStatement();
        }
    }

    public void deleteCaughtPiece(ChessBoardDTO chessBoardDTO) {
        String query = "DELETE  FROM " + chessBoardDTO.getPieceColor().getOppositeColor() + " WHERE position = ?";
        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, chessBoardDTO.getTargetPosition());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("deleteCaughtPiece 오류: " + e.getMessage());
        } finally {
            closeConnection();
            closePrepareStatement();
        }
    }

    private void initialPlayerTurn() {
        String query = "INSERT INTO playerTurn VALUES ('WHITE')";
        try {
            con = getConnection();
            statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("initialPlayerTurn 오류: " + e.getMessage());
        } finally {
            closeConnection();
            closePrepareStatement();
        }
    }

    public String selectPlayerTurn() {
        String query = "SELECT * FROM playerTurn";
        String playerTurn = null;
        try {
            con = getConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                playerTurn = resultSet.getString("turn");
            }
        } catch (SQLException e) {
            System.err.println("selectPlayerTurn 오류: " + e.getMessage());
        } finally {
            closeConnection();
            closeStatement();
            closeResultSet();
        }
        return playerTurn;
    }

    public void updatePlayerTurn(ChessBoardDTO chessBoardDTO) {
        String query = "UPDATE playerTurn SET turn = ?";
        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(query);
            String playerTurn = chessBoardDTO.getPieceColor().getColor();
            preparedStatement.setString(1, playerTurn.toUpperCase());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updatePlayerTurn 오류: " + e.getMessage());
        } finally {
            closeConnection();
            closePrepareStatement();
        }
    }
}
