package chess.domain;

import chess.domain.piece.character.Character;
import chess.domain.piece.character.Team;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void run(Supplier<List<Position>> supplier, Consumer<Map<Position, Character>> consumer) {
        List<Position> positions;
        Team currentTeam = Team.BLACK;
        while (!(positions = supplier.get()).isEmpty()) {
            board.validateOppositeTeamByPosition(positions.get(0), currentTeam);
            board.move(positions.get(0), positions.get(1));

            currentTeam = currentTeam.opponent();
            consumer.accept(board.mapPositionToCharacter());
        }
    }
}
