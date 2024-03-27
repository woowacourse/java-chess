package domain.position;

import view.mapper.FileInput;
import view.mapper.RankInput;

import java.util.List;

public class PositionParser {

    public Position parse(String rawCommand) {
        List<String> tokens = List.of(rawCommand.split(""));
        File file = FileInput.asFile(tokens.get(0));
        Rank rank = RankInput.asRank(tokens.get(1));
        return new Position(file, rank);
    }
}
