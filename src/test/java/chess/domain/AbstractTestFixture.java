package chess.domain;

import java.util.Map;

import chess.domain.board.BoardFactory;
import chess.domain.game.Team;
import chess.domain.move.Direction;
import chess.domain.move.Directions;
import chess.domain.move.Move;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public abstract class AbstractTestFixture {

    public static final Map<Position, Piece> BOARD_WITH_THREE_PAWNS_IN_A_FILE = Map.ofEntries(
            Map.entry(new Position(File.A, Rank.ONE), new Pawn(Team.WHITE)),
            Map.entry(new Position(File.D, Rank.ONE), new Pawn(Team.WHITE)),
            Map.entry(new Position(File.C, Rank.ONE), new Pawn(Team.BLACK)),
            Map.entry(new Position(File.D, Rank.TWO), new Pawn(Team.WHITE)),
            Map.entry(new Position(File.D, Rank.FOUR), new Pawn(Team.WHITE)),
            Map.entry(new Position(File.D, Rank.FIVE), new Knight(Team.WHITE))
    );

    public static final Map<Position, Piece> INITIAL_BOARD = BoardFactory.createBoard().getPieces();

    public static Move createMove(Direction... directions) {
        return new Move(Directions.of(directions));
    }

    public static Position createPosition(String position) {
        String[] split = position.split(",");
        File file = File.valueOf(split[0]);
        Rank rank = Rank.valueOf(split[1]);
        return new Position(file, rank);
    }
}
