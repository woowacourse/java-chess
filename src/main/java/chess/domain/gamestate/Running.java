package chess.domain.gamestate;

import java.util.List;

public class Running extends Started {

    private static final String IMPOSSIBLE_TO_CALCULATE_SCORE_MESSAGE = "게임 진행 중에는 점수를 계산할 수 없습니다.";

    @Override
    public GameState move(String keyFromPosition, String keyToPosition) {
        board.move(keyFromPosition, keyToPosition);
        return this; // Todo: 체크메이트일 경우 Finished를 리턴하도록
    }

    @Override
    public List<List<String>> getBoard() {
        return board.getBoard();
    }

    @Override
    public GameState finish() {
        return new Finished(board);
    }

    @Override
    public double getWhiteTeamScore() {
        throw new UnsupportedOperationException(IMPOSSIBLE_TO_CALCULATE_SCORE_MESSAGE);
    }

    @Override
    public double getBlackTeamScore() {
        throw new UnsupportedOperationException(IMPOSSIBLE_TO_CALCULATE_SCORE_MESSAGE);

    }
}
