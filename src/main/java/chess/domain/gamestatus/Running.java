package chess.domain.gamestatus;

import java.util.List;

public class Running extends Started {
    @Override
    public GameStatus move(String keyFromPosition, String keyToPosition) {
        board.move(keyFromPosition, keyToPosition);
        System.out.println(keyFromPosition);
        System.out.println(keyToPosition);

        return this; // Todo: 체크메이트일 경우 Finished를 리턴하도록
    }

    @Override
    public List<List<String>> getBoard() {
        return board.getBoard();
    }
}
