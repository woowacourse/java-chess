package chess.view.command;

import chess.domain.attribute.File;
import chess.domain.attribute.Rank;
import chess.domain.attribute.Square;
import java.util.List;

public class MoveCommand {

    private final Square source;
    private final Square target;

    public MoveCommand(final String input) {
        List<String> command = List.of(input.split(" "));
        String source = command.get(1);
        String target = command.get(2);
        this.source = Square.of(File.of(parseFile(source)), Rank.of(parseRank(source)));
        this.target = Square.of(File.of(parseFile(target)), Rank.of(parseRank(target)));
    }

    private Character parseRank(String square) {
        return square.charAt(1);
    }

    private Character parseFile(String square) {
        return square.charAt(0);
    }

    public Square source() {
        return source;
    }

    public Square target() {
        return target;
    }
}
