package chess.service;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class ChessService {

    public Board initialize() {
        return Board.createInitial();
    }

    public Board move(Board board, String sourceInput, String targetInput) {
        Position source = Position.from(sourceInput);
        Position target = Position.from(targetInput);

        return board.move(source, target);
    }
}
