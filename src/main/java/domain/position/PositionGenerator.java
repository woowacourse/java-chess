package domain.position;

import java.util.List;
import view.mapper.FileInput;
import view.mapper.RankInput;

public class PositionGenerator {

    public Position generate(String rawCommand) {
        List<String> tokens = List.of(rawCommand.split(""));
        File file = FileInput.asFile(tokens.get(0));
        Rank rank = RankInput.asRank(tokens.get(1));
        return new Position(file, rank);
    }
}
