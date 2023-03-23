package chess.controller.command;

import chess.domain.chessboard.Coordinate;
import java.util.List;

@FunctionalInterface
public interface CommandOptionAction {

    List<Coordinate> action(final List<String> options);
}
