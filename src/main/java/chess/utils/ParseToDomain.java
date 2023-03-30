package chess.utils;

import chess.domain.game.File;
import chess.domain.game.PieceMapper;
import chess.domain.game.Position;
import chess.domain.game.Rank;
import chess.domain.game.Turn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.dto.game.ChessGameLoadDto;

import java.util.HashMap;
import java.util.Map;

public final class ParseToDomain {

    private ParseToDomain() {
    }

    public static Map<Position, Piece> parseToBoard(final ChessGameLoadDto dto) {
        final Map<Position, Piece> result = new HashMap<>();

        for (int i = 0; i < dto.getPieceTypes().size(); i++) {
            final PieceType pieceType = PieceType.valueOf(dto.getPieceTypes().get(i));
            final Team team = Team.valueOf(dto.getPieceTeams().get(i));
            final File file = File.valueOf(dto.getPieceFiles().get(i));
            final Rank rank = Rank.valueOf(dto.getPieceRanks().get(i));

            final Position position = Position.of(file, rank);
            final Piece piece = PieceMapper.get(pieceType, team);

            result.put(position, piece);
        }
        return result;
    }

    public static Turn parseToTurn(final ChessGameLoadDto turn) {
        final Team team = Team.valueOf(turn.getLastTurn());
        return Turn.of(team);
    }
}
