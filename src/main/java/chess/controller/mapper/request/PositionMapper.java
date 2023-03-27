package chess.controller.mapper.request;

import chess.domain.position.Position;

public final class PositionMapper {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private PositionMapper() {
    }

    public static Position toPosition(String positionInput) {
        String fileInput = String.valueOf(positionInput.charAt(FILE_INDEX));
        String rankInput = String.valueOf(positionInput.charAt(RANK_INDEX));

        return Position.of(FileMapper.toFile(fileInput), RankMapper.toRank(rankInput));
    }
}
