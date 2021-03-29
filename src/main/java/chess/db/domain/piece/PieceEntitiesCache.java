package chess.db.domain.piece;

import static chess.beforedb.domain.piece.type.PieceType.BISHOP;
import static chess.beforedb.domain.piece.type.PieceType.KING;
import static chess.beforedb.domain.piece.type.PieceType.KNIGHT;
import static chess.beforedb.domain.piece.type.PieceType.PAWN;
import static chess.beforedb.domain.piece.type.PieceType.QUEEN;
import static chess.beforedb.domain.piece.type.PieceType.ROOK;

import chess.beforedb.domain.piece.type.PieceType;
import chess.beforedb.domain.player.type.TeamColor;
import chess.db.dao.PieceDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PieceEntitiesCache {
    private static final PieceDAO PIECE_DAO = new PieceDAO();
    private static final List<PieceEntity> PIECE_ENTITIES = new ArrayList<>();

    private PieceEntitiesCache() {
    }

    static {
        try {
            cachePieces();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void cachePieces() throws SQLException {
        for (TeamColor teamColor : TeamColor.values()) {
            cachePiecesWithColor(teamColor);
        }
    }

    private static void cachePiecesWithColor(TeamColor teamColor) throws SQLException {
        PIECE_ENTITIES.addAll(getPiecesWithColorFromDB(teamColor));
    }

    private static List<PieceEntity> getPiecesWithColorFromDB(TeamColor teamColor)
        throws SQLException {
        return Arrays.asList(
            PIECE_DAO.findByPieceTypeAndTeamColor(PAWN, teamColor),
            PIECE_DAO.findByPieceTypeAndTeamColor(ROOK, teamColor),
            PIECE_DAO.findByPieceTypeAndTeamColor(KNIGHT, teamColor),
            PIECE_DAO.findByPieceTypeAndTeamColor(BISHOP, teamColor),
            PIECE_DAO.findByPieceTypeAndTeamColor(QUEEN, teamColor),
            PIECE_DAO.findByPieceTypeAndTeamColor(KING, teamColor));
    }


    public static PieceEntity find(PieceType pieceType, TeamColor teamColor) {
        return PIECE_ENTITIES.stream()
            .filter(piece -> piece.getPieceType() == pieceType && piece.getTeamColor() == teamColor)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."));
    }

    public static PieceEntity findById(Long pieceId) {
        return PIECE_ENTITIES.stream()
            .filter(piece -> piece.getId().equals(pieceId))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."));
    }
}
