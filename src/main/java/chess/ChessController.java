package chess;

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

    private Board board;

    public BoardDto startGame() {
        board = new Board();
        return new BoardDto(toBoardDto(board));
    }

    public BoardDto move(String source, String target) {
        board.move(source, target);
        return new BoardDto(toBoardDto(board));
    }

    public void score() {
        board.calculateScore();
    }

    private List<List<String>> toBoardDto(Board board) {
        List<List<String>> boardDto = new ArrayList<>();
        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);
        for (Rank rank : ranks) {
            List<String> tempLine = new ArrayList<>();
            for (File file : File.values()) {
                Piece piece = board.get(Square.of(file, rank));
                tempLine.add(toPieceDto(piece));
            }
            boardDto.add(tempLine);
        }
        return boardDto;
    }

    private String toPieceDto(Piece piece) {
        if (piece.isBlack()) {
            return piece.name().toUpperCase(Locale.ROOT);
        }
        return piece.name();
    }
}
