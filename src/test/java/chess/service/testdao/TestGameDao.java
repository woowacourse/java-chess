package chess.service.testdao;

import chess.dao.GameDaoInterface;
import chess.domain.game.Game;
import chess.domain.team.Team;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestGameDao implements GameDaoInterface {

    private final List<Game> games;
    private long generatedKey;

    public TestGameDao() {
        this.generatedKey = 1;
        this.games = new ArrayList<>();
    }

    @Override
    public long insert(final Game game) {
        games.add(
            new Game(
                generatedKey,
                game.getWhiteId(),
                game.getBlackId(),
                Team.WHITE,
                false,
                LocalDateTime.now()
            )
        );
        return generatedKey++;
    }

    @Override
    public Optional<Game> selectById(final long id) {
        return games.stream()
            .filter(game -> game.getId() == id)
            .findAny();
    }

    @Override
    public void update(final Game game) {
        final long id = game.getId();
        final Game oldGame = selectById(id).get();
        deleteById(oldGame.getId());
        games.add(
            new Game(
                oldGame.getId(),
                oldGame.getWhiteId(),
                oldGame.getBlackId(),
                Team.from(game.getTurnValue()),
                game.isFinished(),
                oldGame.getCreatedTime()
            )
        );
    }

    @Override
    public void deleteById(final long id) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId() == id) {
                games.remove(i);
                break;
            }
        }
    }
}
