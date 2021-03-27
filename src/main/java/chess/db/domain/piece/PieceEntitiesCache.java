package chess.db.domain.piece;

import static chess.domain.piece.type.PieceType.BISHOP;
import static chess.domain.piece.type.PieceType.KING;
import static chess.domain.piece.type.PieceType.KNIGHT;
import static chess.domain.piece.type.PieceType.PAWN;
import static chess.domain.piece.type.PieceType.QUEEN;
import static chess.domain.piece.type.PieceType.ROOK;

import chess.db.dao.PieceDAO;
import chess.domain.piece.type.PieceType;
import chess.domain.player.type.TeamColor;
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
        List<PieceEntity> piecesWithColor = getPiecesWithColorFromDB(teamColor);
        for (PieceEntity pieceEntity : piecesWithColor) {
            PIECE_ENTITIES.add(castToConcretePieceObject(pieceEntity, teamColor));
        }
        PIECE_ENTITIES.addAll(piecesWithColor);
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

    private static PieceEntity castToConcretePieceObject(PieceEntity pieceEntity,
        TeamColor teamColor) {
        PieceEntity castedPieceEntity = getPieceEntityHalf(pieceEntity, teamColor);
        if (castedPieceEntity != null) {
            return castedPieceEntity;
        }
        return getPieceEntityTheOtherHalf(pieceEntity, teamColor);
    }

    private static PieceEntity getPieceEntityHalf(PieceEntity pieceEntity, TeamColor teamColor) {
        if (pieceEntity.getPieceType() == PAWN) {
            return new PawnEntity(pieceEntity.getId(), teamColor);
        }
        if (pieceEntity.getPieceType() == ROOK) {
            return new RookEntity(pieceEntity.getId(), teamColor);
        }
        if (pieceEntity.getPieceType() == KNIGHT) {
            return new KingEntity(pieceEntity.getId(), teamColor);
        }
        return null;
    }

    private static PieceEntity getPieceEntityTheOtherHalf(PieceEntity pieceEntity,
        TeamColor teamColor) {
        if (pieceEntity.getPieceType() == BISHOP) {
            return new BishopEntity(pieceEntity.getId(), teamColor);
        }
        if (pieceEntity.getPieceType() == QUEEN) {
            return new QueenEntity(pieceEntity.getId(), teamColor);
        }
        if (pieceEntity.getPieceType() == KING) {
            return new KingEntity(pieceEntity.getId(), teamColor);
        }
        throw new IllegalStateException("데이터베이스로부터 기물들을 가져와 캐싱하는데에 실패했습니다.");
    }

    public static PieceEntity find(PieceType pieceType, TeamColor teamColor) {
        return PIECE_ENTITIES.stream()
            .filter(piece -> piece.getPieceType() == pieceType && piece.getTeamColor() == teamColor)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."));
    }
}
