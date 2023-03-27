package chess.dto;

import chess.domain.position.Position;
import chess.view.ViewFile;
import chess.view.ViewRank;

public class MoveDto {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final String fromPosition;
    private final String toPosition;

    private MoveDto(String fromPosition, String toPosition) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    public static MoveDto of(String fromPosition, String toPosition) {
        return new MoveDto(fromPosition, toPosition);
    }

    public Position getFromPosition() {
        return getPosition(fromPosition);
    }

    public Position getToPosition() {
        return getPosition(toPosition);
    }

    private Position getPosition(final String toPosition) {
        String fileInput = String.valueOf(toPosition.charAt(FILE_INDEX));
        String rankInput = String.valueOf(toPosition.charAt(RANK_INDEX));

        return Position.of(ViewFile.from(fileInput), ViewRank.from(rankInput));
    }

    public String getViewFromPosition() {
        return fromPosition;
    }

    public String getViewToPosition() {
        return toPosition;
    }
}
