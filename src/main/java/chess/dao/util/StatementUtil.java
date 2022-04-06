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
        for (final Entry<Position, ChessPiece> entry : pieceByPosition.entrySet()) {
            final Position position = entry.getKey();
            final ChessPiece chessPiece = entry.getValue();

            StatementUtil.setParameter(statement,
                    roomName,
                    position.getValue(),
                    ChessPieceMapper.toPieceType(chessPiece),
                    chessPiece.color().getValue());
        }
    }

    public static void setParameter(PreparedStatement statement, String... params) throws SQLException {
        int index = 1;
        for (String parameter : params) {
            statement.setString(index++, parameter);
        }
    }
}
