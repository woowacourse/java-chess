package chess.controller.mapper;

import java.util.Map;
import java.util.Objects;

import chess.controller.dto.PieceResponse;
import chess.domain.game.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public class PieceResponseMapper {

    private static final Map<PieceType, Character> PIECE_TYPE_MAP = Map.ofEntries(
            Map.entry(PieceType.KING, 'K'),
            Map.entry(PieceType.QUEEN, 'Q'),
            Map.entry(PieceType.BISHOP, 'B'),
            Map.entry(PieceType.ROOK, 'R'),
            Map.entry(PieceType.PAWN, 'P'),
            Map.entry(PieceType.KNIGHT, 'N')
    );

    public static PieceResponse map(Position position, Piece piece) {
        int fileIndex = position.getFile().getIndex();
        int rankIndex = position.getRank().getIndex();
        return new PieceResponse(fileIndex, rankIndex, mapLetter(piece), piece.hasTeam(Team.WHITE));
    }

    private static Character mapLetter(Piece piece) {
        Character letter = PIECE_TYPE_MAP.get(piece.getType());
        if (Objects.isNull(letter)) {
            throw new IllegalArgumentException("알 수 없는 기물이 있습니다");
        }
        return letter;
    }
}
