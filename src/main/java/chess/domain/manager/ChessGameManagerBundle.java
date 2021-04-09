package chess.domain.manager;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ChessGameManagerBundle {
    private final List<ChessGameManager> chessGameManagers;

    public ChessGameManagerBundle(List<ChessGameManager> chessGameManagers) {
        this.chessGameManagers = chessGameManagers;
    }

    public Map<Long, String> getIdAndNextTurn() {
        return chessGameManagers.stream()
                .collect(toMap(ChessGameManager::getId, game -> game.nextColor().name()));
    }
}
