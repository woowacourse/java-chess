package chess.persistance;

import chess.model.AbstractChessPiece;
import chess.model.ChessPieceColor;
import chess.model.ChessPieceType;
import chess.model.Point;

import java.util.*;

public class ChessPieceDAOImpl implements ChessPieceDAO {
    private static final JDBCTemplate JDBC_TEMPLATE = JDBCTemplate.getInstance();
    private static final ChessPieceDAOImpl INSTANCE = new ChessPieceDAOImpl();

    private ChessPieceDAOImpl() {
    }

    public static ChessPieceDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public int countPieces(int gameId) {
        String query = "SELECT COUNT(*) as num FROM chess_piece WHERE gid = ?";
        List<String> args = new ArrayList<>(Arrays.asList(
                String.valueOf(gameId)
        ));
        List<Map<String, String>> result = JDBC_TEMPLATE.selectQuery(query, args);
        return Integer.valueOf(result.get(0).get("num"));
    }

    @Override
    public void addPieces(final Map<Point, AbstractChessPiece> board, final int gameId) {
        for (Map.Entry<Point, AbstractChessPiece> entry : board.entrySet()) {
            addPiece(entry.getKey(), entry.getValue(), gameId);
        }
    }

    @Override
    public void removePiece(final Point point, final int gameId) {
        String query = "DELETE FROM chess_piece WHERE gid = ? AND x = ? AND y = ?";
        List<String> args = new ArrayList<>(Arrays.asList(
                String.valueOf(gameId),
                String.valueOf(point.getX()),
                String.valueOf(point.getY())
        ));
        JDBC_TEMPLATE.updateQuery(query, args);
    }

    @Override
    public void updatePiece(final Point source, final Point target, final int gameId) {
        String query = "UPDATE chess_piece SET x = ?, y = ? WHERE gid = ? AND x = ? AND y = ?";
        List<String> args = new ArrayList<>(Arrays.asList(
                String.valueOf(target.getX()),
                String.valueOf(target.getY()),
                String.valueOf(gameId),
                String.valueOf(source.getX()),
                String.valueOf(source.getY())
        ));
        JDBC_TEMPLATE.updateQuery(query, args);
    }

    @Override
    public void addPiece(final Point point, final AbstractChessPiece piece, final int gameId) {
        String query = "INSERT INTO chess_piece (x, y, type, gid, color) VALUES (?, ?, ?, ?, ?)";
        List<String> args = new ArrayList<>(Arrays.asList(
                String.valueOf(point.getX()),
                String.valueOf(point.getY()),
                piece.getType().name(),
                String.valueOf(gameId),
                piece.getColor().name()));
        JDBC_TEMPLATE.updateQuery(query, args);
    }

    @Override
    public Map<Point, AbstractChessPiece> getAll(final int gameId) {
        String query = "SELECT x, y, color, type FROM chess_piece WHERE gid = ?";
        List<String> args = new ArrayList<>(Collections.singletonList(String.valueOf(gameId)));
        List<Map<String, String>> result = JDBC_TEMPLATE.selectQuery(query, args);
        Map<Point, AbstractChessPiece> allChess = new HashMap<>();

        for (Map<String, String> chessPiece : result) {
            int x = Integer.valueOf(chessPiece.get("x"));
            int y = Integer.valueOf(chessPiece.get("y"));
            ChessPieceColor color = ChessPieceColor.valueOf(chessPiece.get("color"));
            ChessPieceType type = ChessPieceType.valueOf(chessPiece.get("type"));
            allChess.put(new Point(x, y), AbstractChessPiece.getInstance(type, color));
        }

        return allChess;
    }


}
