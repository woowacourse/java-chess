package chess.dto;

import chess.domain.game.File;
import chess.domain.game.Position;
import chess.domain.game.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class BoardDto {

    private final List<String> names;

    private BoardDto(final List<String> names) {
        this.names = names;
    }

    public static BoardDto from(Map<Position, Piece> board) {
        List<String> names = new ArrayList<>();
        for (int rankOrder = Rank.MAX_ORDER; rankOrder >= Rank.MIN_ORDER; rankOrder--) {
            for (int fileOrder = File.MIN_ORDER; fileOrder <= File.MAX_ORDER; fileOrder++) {
                final Position position = Position.of(File.of(fileOrder), Rank.of(rankOrder));
                final Piece piece = board.get(position);
                names.add(render(piece));
            }
        }
        return new BoardDto(names);
    }

    private static String render(final Piece piece) {
        final Team team = piece.getTeam();
        final PieceType pieceType = piece.getPieceType();

        if (team.isBlack() || team.isEmpty()) {
            return pieceType.getValue();
        }
        if (team.isWhite()) {
            return pieceType.getValue().toLowerCase();
        }
        throw new AssertionError();
    }

    public List<String> getNames() {
        return List.copyOf(names);
    }
}
