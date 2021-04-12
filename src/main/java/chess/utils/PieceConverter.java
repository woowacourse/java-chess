package chess.utils;

import chess.domain.location.Location;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.team.Team;
import chess.dto.piece.PieceDto;

public class PieceConverter {

    public static Piece run(final PieceDto pieceDto) {
        final Team team = pieceDto.getTeam();
        final Location location = Location.of(pieceDto.getX(), pieceDto.getY());
        switch (pieceDto.getPieceLetter()) {
            case 'k':
                return King.of(location, team);
            case 'q':
                return Queen.of(location, team);
            case 'b':
                return Bishop.of(location, team);
            case 'n':
                return Knight.of(location, team);
            case 'r':
                return Rook.of(location, team);
            default:
                return Pawn.of(location, team);
        }
    }
}
