package chess.piece;

import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import chess.validator.QueenMoveValidator;

import java.util.List;
import java.util.stream.Collectors;

public class Queen extends Piece {
    public Queen(Team team) {
        super(team, "Q", new QueenMoveValidator());
    }
}
