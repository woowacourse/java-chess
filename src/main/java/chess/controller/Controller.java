package chess.controller;

import chess.domain.Board;

public interface Controller {
    public Board execute(RequestInfo requestInfo, Board board);
}
