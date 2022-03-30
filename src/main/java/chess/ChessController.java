package chess;

import chess.piece.Piece;
import chess.square.File;
import chess.square.Rank;
import chess.square.Square;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ChessController {

    public BoardDto startGame() {
        Board board = new Board();
        return new BoardDto(toBoardDto(board));
    }

    private List<List<String>> toBoardDto(Board board) {
        List<List<String>> boardDto = new ArrayList<>();
        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);
        for (Rank rank : ranks) {
            List<String> tempLine = new ArrayList<>();
            for (File file : File.values()) {
                Piece piece = board.get(Square.of(rank, file));
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
