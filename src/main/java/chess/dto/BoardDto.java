package chess.dto;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
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

        for (int rankOrder = 8; rankOrder >= 1; rankOrder--) {
            for (int fileOrder = 1; fileOrder <= 8; fileOrder++) {
                final Position position = Position.of(File.of(fileOrder), Rank.of(rankOrder));
                final Piece piece = board.get(position);

                names.add(PieceRender.renderName2(piece.getPiece(), piece.getColor()));
            }
        }

        return new BoardDto(names);
    }

    public List<String> getNames() {
        return names;
    }
}
