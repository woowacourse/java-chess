package domain.jdbc;

import domain.ChessGame;
import domain.chessboard.ChessBoard;
import domain.chessboard.Empty;
import domain.chessboard.EmptyType;
import domain.chessboard.Square;
import domain.chessboard.SquareStatus;
import domain.chessboard.Type;
import domain.coordinate.Position;
import domain.piece.Bishop;
import domain.piece.Color;
import domain.piece.InitPawn;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.PieceType;
import domain.piece.Queen;
import domain.piece.Rook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ChessGameDao implements JdbcDao {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final Map<Type, Function<Color, SquareStatus>> squareStatusMapper = Map.of(
            EmptyType.EMPTY, color -> new Empty(),
            PieceType.KING, King::new,
            PieceType.PAWN, Pawn::new,
            PieceType.INIT_PAWN, InitPawn::new,
            PieceType.BISHOP, Bishop::new,
            PieceType.KNIGHT, Knight::new,
            PieceType.QUEEN, Queen::new,
            PieceType.ROOK, Rook::new
    );

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public String save(ChessGame chessGame) {
        try (final Connection connection = getConnection()) {
            saveChessGame(connection);
            String currentId = getLastInsertId(connection);
            savePieces(chessGame.getChessBoard(), connection, currentId);
            return currentId;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getLastInsertId(Connection connection) {
        try {
            return getId(connection);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void saveChessGame(Connection connection) throws SQLException {
        PreparedStatement chessGameSave = connection.prepareStatement(
                "INSERT INTO chess_game(turn) VALUES(?)"
        );

        chessGameSave.setString(1, Color.WHITE.name());
        chessGameSave.executeUpdate();
    }

    private String getId(Connection connection) throws SQLException {
        PreparedStatement lastInsertIdSelect = connection.prepareStatement("SELECT MAX(id) AS id FROM chess_game");
        ResultSet resultSet = lastInsertIdSelect.executeQuery();
        String lastInsertId = null;

        if (resultSet.next()) {
            lastInsertId = resultSet.getString("id");
        }

        return lastInsertId;
    }

    private void savePieces(ChessBoard chessBoard, Connection connection, String currentId) throws SQLException {
        PreparedStatement pieceSave = connection.prepareStatement(
                "INSERT INTO chess_board(x, y, piece_type, piece_color, game_id) VALUES(?, ?, ?, ?, ?)"
        );
        for (int x = 0; x < 8; x++) {
            saveColumnPieces(chessBoard, pieceSave, x, currentId);
        }
    }

    private void saveColumnPieces(ChessBoard chessBoard, PreparedStatement pieceSave, int x, String currentId) throws SQLException {
        for (int y = 0; y < 8; y++) {
            SquareStatus squareStatus = chessBoard.findSquare(Position.of(x, y)).getSquareStatus();
            pieceSave.setString(1, Integer.toString(x));
            pieceSave.setString(2, Integer.toString(y));
            pieceSave.setString(3, squareStatus.getType().name());
            pieceSave.setString(4, squareStatus.getColor().name());
            pieceSave.setString(5, currentId);
            pieceSave.executeUpdate();
        }
    }

    @Override
    public ChessGame selectNewGame(ChessGame chessGame) {
        String id = save(chessGame);
        return select(id);
    }

    @Override
    public ChessGame select(String id) { // join 해서, 가져온다
        String query1 = "SELECT turn FROM chess_game where id = ?";
        String query2 = "SELECT x, y, piece_type, piece_color FROM chess_board WHERE game_id = ?";
        try (Connection connection = getConnection()){
            PreparedStatement p1 = getConnection().prepareStatement(query1); // chess game 을 가져옴
            PreparedStatement p2 = getConnection().prepareStatement(query2); // chess piece 를 가져옴
            ChessBoard chessBoard = ChessBoard.generateEmptyBoard();
            p1.setString(1, id);
            p2.setString(1, id);
            ResultSet resultSet = p2.executeQuery();

            while (resultSet.next()) {
                int x = Integer.parseInt(resultSet.getString("x"));
                int y = Integer.parseInt(resultSet.getString("y"));
                String pieceType = resultSet.getString("piece_type");
                String pieceColor = resultSet.getString("piece_color");
                Color color = Color.fromName(pieceColor);
                List<Type> types = new ArrayList<>(List.of(PieceType.values()));
                types.addAll(List.of(EmptyType.values()));
                Type resultType = types.stream()
                        .filter(type -> pieceType.equals(type.name()))
                        .findFirst()
                        .orElseThrow(IllegalAccessError::new);
                chessBoard.findSquare(Position.of(x, y)).bePiece(new Square(squareStatusMapper.get(resultType).apply(color)));
            }

            resultSet = p1.executeQuery();
            String turn = null;

            if (resultSet.next()) {
                turn = resultSet.getString("turn");
            }

            return new ChessGame(Color.fromName(turn), chessBoard);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void update() { // update 는 이제, turn, (Position, Square)

    }

    @Override
    public void delete() { // cascade 로 생성해서, 그냥 delete game id

    }

}
