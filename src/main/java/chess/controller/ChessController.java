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
        return new BoardDto(toBoardDto(board));
    }

    public BoardDto move(String source, String target) {
        board.move(source, target);
        return new BoardDto(toBoardDto(board));
    }

    public ScoreDto score() {
        return new ScoreDto(board.calculateScore());
    }

    public boolean isEnd() {
        return board.isEnd();
    }

    public void finishGame() {
        board.finishGame();
    }

    private List<List<String>> toBoardDto(Board board) {
        List<List<String>> boardDto = new ArrayList<>();
        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);
        for (Rank rank : ranks) {
            boardDto.add(makeLineByFile(board, rank));
        }
        return boardDto;
    }

    private List<String> makeLineByFile(Board board, Rank rank) {
        List<String> tempLine = new ArrayList<>();
        for (File file : File.values()) {
            Piece piece = board.get(Square.of(file, rank));
            tempLine.add(toPieceDto(piece));
        }
        return tempLine;
    }

    private String toPieceDto(Piece piece) {
        if (piece.isBlack()) {
            return piece.name().toUpperCase(Locale.ROOT);
        }
        return piece.name();
    }
}
