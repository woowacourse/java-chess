package chess.service;

import chess.domain.board.Board;

public class ChessService {

    public Board initialize() {
        return Board.createInitial();
    }
}
