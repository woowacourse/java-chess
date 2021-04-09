package chess.utils;

import chess.domain.dto.PieceDto;
import chess.domain.location.Location;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.team.Team;
import java.util.List;
import java.util.stream.Collectors;

public class PieceUtil {

    private PieceUtil() {}

    public static Piece generatePiece(long id, long roomId, char signature, String team, String location) {
        Team pieceTeam = Team.of(team);
        Location pieceLocation = Location.of(location);
        if (signature == 'r') {
            return Rook.of(id, roomId, pieceTeam, pieceLocation);
        }
        if (signature == 'k') {
            return King.of(id, roomId, pieceTeam, pieceLocation);
        }
        if (signature == 'q') {
            return Queen.of(id, roomId, pieceTeam, pieceLocation);
        }
        if (signature == 'p') {
            return Pawn.of(id, roomId, pieceTeam, pieceLocation);
        }
        if (signature == 'n') {
            return Knight.of(id, roomId, pieceTeam, pieceLocation);
        }
        if (signature == 'b') {
            return Bishop.of(id, roomId, pieceTeam, pieceLocation);
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 기물입니다.");
    }
}
