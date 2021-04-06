package chess.webdao;

import chess.domain.Position;
import chess.domain.piece.Piece;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PiecePositionDAOConverter {
    private static final String FIELD_SEPARATOR = ",";
    private static final String PIECE_SEPARATOR = "/";

    private PiecePositionDAOConverter() {
    }

    public static String asDAO(final Map<Position, Piece> teamPiecePosition) {
        StringBuilder pieceInfo = new StringBuilder();
        for (Position position : teamPiecePosition.keySet()) {
            pieceInfo.append(position.getPositionInitial());
            pieceInfo.append(FIELD_SEPARATOR);
            final Piece piece = teamPiecePosition.get(position);
            pieceInfo.append(PieceToDAO.convert(piece));
            pieceInfo.append(FIELD_SEPARATOR);
            pieceInfo.append(booleanToString(piece.isFirstMove()));
            pieceInfo.append(PIECE_SEPARATOR);
        }
        return pieceInfo.toString();
    }

    private static String booleanToString(final boolean value) {
        if (value) {
            return "true";
        }
        return "false";
    }

    public static Map<Position, Piece> asPiecePosition(final String teamPieceInfo, final String team) throws SQLException {
        final Map<Position, Piece> piecePosition = new HashMap<>();
        final String[] teamPieceInfos = teamPieceInfo.split(PIECE_SEPARATOR);
        for (String singlePieceInfo : teamPieceInfos) {
            final String[] SinglePieceInfos = singlePieceInfo.split(FIELD_SEPARATOR);
            final Position position = Position.of(SinglePieceInfos[0]);
            final Piece piece = DAOtoPiece.generatePiece(team, SinglePieceInfos[1], stringToBoolean(SinglePieceInfos[2]));
            piecePosition.put(position, piece);
        }
        return piecePosition;
    }

    private static boolean stringToBoolean(final String value) {
        return "true".equals(value);
    }
}
