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
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ChessGameDao implements JdbcChessGameDao {

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
            saveChessGame(connection, chessGame.getColorTurn());
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

    private void saveChessGame(Connection connection, Color color) throws SQLException {
        PreparedStatement chessGameSave = connection.prepareStatement(
                "INSERT INTO chess_game(turn) VALUES(?)"
        );

        chessGameSave.setString(1, color.name());
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
    public ChessGame select(String id) {
        try (Connection connection = getConnection()){
            Color turn = getTurn(id, connection);
            ChessBoard chessBoard = getChessBoard(id, connection);
            return new ChessGame(turn, chessBoard);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Color getTurn(String id, Connection connection) throws SQLException {
        PreparedStatement selectChessGame = connection.prepareStatement("SELECT turn FROM chess_game where id = ?");
        selectChessGame.setString(1, id);
        ResultSet selectChessGameSet = selectChessGame.executeQuery();
        String turn = null;

        if (selectChessGameSet.next()) {
            turn = selectChessGameSet.getString("turn");
        }

        return Color.fromName(turn);
    }

    private ChessBoard getChessBoard(String id, Connection connection) throws SQLException {
        PreparedStatement selectChessBoard = connection.prepareStatement(
                "SELECT x, y, piece_type, piece_color FROM chess_board WHERE game_id = ?"
        );
        ChessBoard chessBoard = ChessBoard.generateEmptyBoard();
        selectChessBoard.setString(1, id);
        ResultSet selectChessBoardSet = selectChessBoard.executeQuery();
        return getChessBoardByQueryResult(chessBoard, selectChessBoardSet);
    }

    private ChessBoard getChessBoardByQueryResult(ChessBoard chessBoard, ResultSet selectChessBoardSet) throws SQLException {
        while (selectChessBoardSet.next()) {
            int x = Integer.parseInt(selectChessBoardSet.getString("x"));
            int y = Integer.parseInt(selectChessBoardSet.getString("y"));
            String pieceType = selectChessBoardSet.getString("piece_type");
            String pieceColor = selectChessBoardSet.getString("piece_color");
            Position position = Position.of(x, y);
            chessBoard.findSquare(position).bePiece(getSquare(pieceType, pieceColor));
        }

        return chessBoard;
    }

    private Square getSquare(String pieceType, String pieceColor) {
        Color color = Color.fromName(pieceColor);

        List<Type> types = new ArrayList<>(List.of(PieceType.values()));
        types.addAll(List.of(EmptyType.values()));

        Type resultType = types.stream()
                .filter(type -> pieceType.equals(type.name()))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
        return new Square(squareStatusMapper.get(resultType).apply(color));
    }

    @Override
    public void update(String id, ChessGame chessGameAfterProcess) {
        ChessGame chessGameBySelect = select(id);
        String q1 = "UPDATE chess_game SET turn = ? WHERE id = ?";
        String q2 = "UPDATE chess_board SET piece_type = ?, piece_color = ? WHERE x = ? and y = ? and game_id = ?";

        try (Connection connection = getConnection()){
            PreparedStatement p1 = connection.prepareStatement(q1);
            PreparedStatement p2 = connection.prepareStatement(q2);
            p1.setString(1, chessGameAfterProcess.getColorTurn().name());
            p1.setString(2, id);
            p1.executeUpdate();
            p2.setString(5, id);

            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    Position findPosition = Position.of(x, y);
                    Square square1 = new Square(chessGameBySelect.getChessBoard()
                            .findSquare(findPosition)
                            .getSquareStatus());
                    Square square2 = new Square(chessGameAfterProcess.getChessBoard()
                            .findSquare(findPosition)
                            .getSquareStatus());

                    if (isNotSameSquare(square1, square2)) {
                        p2.setString(1, square2.getType().name());
                        p2.setString(2, square2.getColor().name());
                        p2.setString(3, Integer.toString(x));
                        p2.setString(4, Integer.toString(y));
                        p2.executeUpdate();
                    }
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private boolean isNotSameSquare(Square insertionSquareStatus, Square squareStatus) {
        return isNotSameColor(insertionSquareStatus, squareStatus)
                || isNotSameType(insertionSquareStatus, squareStatus);
    }

    private boolean isNotSameType(Square insertionSquareStatus, Square squareStatus) {
        return insertionSquareStatus.isNotSameType(squareStatus.getType());
    }

    private boolean isNotSameColor(Square insertionSquareStatus, Square squareStatus) {
        return insertionSquareStatus.isNotSameColor(squareStatus.getColor());
    }

    @Override
    public void delete() { // cascade 로 생성해서, 그냥 delete game id

    }

}
