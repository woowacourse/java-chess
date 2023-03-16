package techcourse.fp.chess.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import techcourse.fp.chess.domain.File;
import techcourse.fp.chess.domain.Piece;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.Rank;
import techcourse.fp.chess.view.PieceRender;

public class PieceDto {

    private final String name;

    public PieceDto(final String name) {
        this.name = name;
    }

    public static List<PieceDto> create(Map<Position, Piece> board) {
        List<PieceDto> pieceDtos = new ArrayList<>();

        for (int rankOrder = 8; rankOrder >= 1; rankOrder--) {
            for (int fileOrder = 1; fileOrder <= 8; fileOrder++) {
                final Position position = Position.of(File.of(fileOrder), Rank.of(rankOrder));
                final Piece piece = board.get(position);
                pieceDtos.add(new PieceDto(PieceRender.renderName(piece)));
            }

        }

        return pieceDtos;
    }

    public String getName() {
        return name;
    }
}
