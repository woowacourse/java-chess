package chess.domain.piece.cache;

import static chess.domain.piece.type.PieceType.BISHOP;
import static chess.domain.piece.type.PieceType.KING;
import static chess.domain.piece.type.PieceType.KNIGHT;
import static chess.domain.piece.type.PieceType.PAWN;
import static chess.domain.piece.type.PieceType.QUEEN;
import static chess.domain.piece.type.PieceType.ROOK;

import chess.dao.PieceDAO;
import chess.domain.piece.Piece;
import chess.domain.piece.type.PieceType;
import chess.domain.player.type.TeamColor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PiecesCache {
    private static final PieceDAO PIECE_DAO = new PieceDAO();
    private static final List<Piece> PIECES = new ArrayList<>();
    private static final String PIECE_NOT_FOUND_ERROR_MESSAGE = "존재하지 않는 기물입니다.";

    private PiecesCache() {
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
        PIECES.addAll(getPiecesWithColorFromDB(teamColor));
    }

    private static List<Piece> getPiecesWithColorFromDB(TeamColor teamColor)
        throws SQLException {
        return Arrays.asList(
            PIECE_DAO.findByPieceTypeAndTeamColor(PAWN, teamColor),
            PIECE_DAO.findByPieceTypeAndTeamColor(ROOK, teamColor),
            PIECE_DAO.findByPieceTypeAndTeamColor(KNIGHT, teamColor),
            PIECE_DAO.findByPieceTypeAndTeamColor(BISHOP, teamColor),
            PIECE_DAO.findByPieceTypeAndTeamColor(QUEEN, teamColor),
            PIECE_DAO.findByPieceTypeAndTeamColor(KING, teamColor));
    }


    public static Piece find(PieceType pieceType, TeamColor teamColor) {
        return PIECES.stream()
            .filter(piece -> piece.getPieceType() == pieceType && piece.getTeamColor() == teamColor)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(PIECE_NOT_FOUND_ERROR_MESSAGE));
    }

    public static Piece findById(Long pieceId) {
        return PIECES.stream()
            .filter(piece -> piece.getId().equals(pieceId))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(PIECE_NOT_FOUND_ERROR_MESSAGE));
    }
}
