package database.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import domain.ChessBoard;
import domain.ChessColumn;
import domain.Rank;
import domain.Square;
import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.PieceLocations;
import domain.piece.PieceType;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.TeamColor;

public class DatabaseDao implements ChessBoardDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChessBoard read() {
        Map<Square, Piece> pieceLocations = getPieceLocations();
        if (pieceLocations.isEmpty()) {
            return new ChessBoard();
        }
        return new ChessBoard(new PieceLocations(pieceLocations));
    }

    private Map<Square, Piece> getPieceLocations() {
        String query = "SELECT piece_type, piece_color, piece_column, piece_rank FROM chess_game";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return loadPieceLocations(resultSet);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Square, Piece> loadPieceLocations(ResultSet resultSet) throws SQLException {
        Map<Square, Piece> pieceLocations = new HashMap<>();
        while (resultSet.next()) {
            PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));
            TeamColor teamColor = TeamColor.valueOf(resultSet.getString("piece_color"));
            ChessColumn chessColumn = ChessColumn.valueOf(resultSet.getString("piece_column"));
            Rank rank = Rank.valueOf(resultSet.getString("piece_rank"));
            Piece piece = getPiece(pieceType, teamColor);
            Square square = Square.of(chessColumn, rank);
            pieceLocations.put(square, piece);
        }
        return pieceLocations;
    }

    private Piece getPiece(PieceType pieceType, TeamColor teamColor) {
        switch (pieceType) {
            case KNIGHT:
                return new Knight(teamColor);
            case PAWN:
                return new Pawn(teamColor);
            case ROOK:
                return new Rook(teamColor);
            case KING:
                return new King(teamColor);
            case QUEEN:
                return new Queen(teamColor);
            case BISHOP:
                return new Bishop(teamColor);
        }
        throw new UnsupportedOperationException();
    }

    public void save(ChessBoard chessBoard) {
        Map<Square, Piece> pieceLocations = chessBoard.getPieceLocations().getPieceLocations();
        for (Map.Entry<Square, Piece> pieceLocation : pieceLocations.entrySet()) {
            savePieceLocation(pieceLocation);
        }
    }

    private void savePieceLocation(Map.Entry<Square, Piece> pieceLocation) {
        String query = "INSERT INTO chess_game VALUES(?,?,?,?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pieceLocation.getValue().pieceType().name());
            preparedStatement.setString(2, pieceLocation.getValue().teamColor().name());
            preparedStatement.setString(3, pieceLocation.getKey().getChessColumn().name());
            preparedStatement.setString(4, pieceLocation.getKey().getRank().name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ChessBoard chessBoard) {
        delete();
        save(chessBoard);
    }

    @Override
    public void delete() {
        final var query = "DELETE FROM chess_game";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
