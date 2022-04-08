package chess.dao.util;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.position.Position;
import chess.dto.ChessPieceMapper;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

public class StatementUtil {

    private StatementUtil() {
    }

    public static void setAllParameter(final String roomName,
                                       final Map<Position, ChessPiece> pieceByPosition,
                                       final PreparedStatement statement) throws SQLException {
        int index = 1;
        for (final Entry<Position, ChessPiece> entry : pieceByPosition.entrySet()) {
            final Position position = entry.getKey();
            final ChessPiece chessPiece = entry.getValue();

            statement.setString(index++, roomName);
            statement.setString(index++, position.getValue());
            statement.setString(index++, ChessPieceMapper.toPieceType(chessPiece));
            statement.setString(index++, chessPiece.color().getValue());
        }
    }

    public static void setParameter(PreparedStatement statement, String... params) throws SQLException {
        int index = 1;
        for (String parameter : params) {
            statement.setString(index++, parameter);
        }
    }
}
