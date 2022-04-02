package chess.controller;

import chess.dto.BoardDto;
import chess.dto.ScoreDto;
import chess.model.Board;
import chess.model.piece.Piece;
import chess.model.square.File;
import chess.model.square.Rank;
import chess.model.square.Square;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ChessController {

    private final Board board;

    public ChessController() {
        this.board = new Board();
    }

    public BoardDto startGame() {
        board.startGame();
        return BoardDto.of(board);
    }

    public BoardDto move(String source, String target) {
        board.move(source, target);
        return BoardDto.of(board);
    }

    public ScoreDto score() {
        return ScoreDto.of(board.calculateScore());
    }

    public boolean isEnd() {
        return board.isEnd();
    }

    public void finishGame() {
        board.finishGame();
    }
}
