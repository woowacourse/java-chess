package chess.dto;

import chess.domain.game.File;
import chess.domain.game.Position;
import chess.domain.game.Rank;
import chess.domain.piece.Piece;
import chess.view.PieceRender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class BoardDto {

    private final List<String> names;

    private BoardDto(final List<String> names) {
        this.names = names;
    }

    public static BoardDto create(Map<Position, Piece> board) {
        List<String> names = new ArrayList<>();
        for (int rankOrder = Rank.MAX_ORDER; rankOrder >= Rank.MIN_ORDER; rankOrder--) {
            for (int fileOrder = File.MIN_ORDER; fileOrder <= File.MAX_ORDER; fileOrder++) {
                final Position position = Position.of(File.of(fileOrder), Rank.of(rankOrder));
                final Piece piece = board.get(position);
                names.add(PieceRender.renderName(piece));
            }
        }
        return new BoardDto(names);
    }

    public List<String> getNames() {
        return names;
    }
}
