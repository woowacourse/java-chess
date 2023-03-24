package techcourse.fp.chess.dto.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import techcourse.fp.chess.domain.File;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.Rank;
import techcourse.fp.chess.domain.piece.Piece;
import techcourse.fp.chess.view.PieceRender;

public final class BoardResponse {

    private static final int START_OF_BOARD = 1;
    private static final int END_OF_BOARD = 8;

    private final List<String> names;

    private BoardResponse(final List<String> names) {
        this.names = names;
    }

    public static BoardResponse create(Map<Position, Piece> board) {
        List<String> names = new ArrayList<>();

        for (int rankOrder = END_OF_BOARD; rankOrder >= START_OF_BOARD; rankOrder--) {
            for (int fileOrder = START_OF_BOARD; fileOrder <= END_OF_BOARD; fileOrder++) {
                final Position position = Position.of(File.of(fileOrder), Rank.of(rankOrder));
                final Piece piece = board.get(position);

                names.add(PieceRender.renderName(piece));
            }
        }

        return new BoardResponse(names);
    }

    public List<String> getNames() {
        return names;
    }
}
