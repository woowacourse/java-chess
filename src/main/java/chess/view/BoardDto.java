package chess.view;

import chess.domain.Board;
import chess.domain.location.Rank;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {

    private final List<List<String>> boardData;

    private BoardDto(List<List<String>> boardData) {
        this.boardData = boardData;
    }

    public static BoardDto of(Board board) {
        List<List<String>> data = Rank.reverseValues().stream()
                .map(board::collectRankPiece)
                .map(rankPiece -> rankPiece.stream()
                        .map(Piece::getName)
                        .collect(Collectors.toList())).collect(Collectors.toList());
        return new BoardDto(data);
    }

    public List<List<String>> getBoardData() {
        return boardData;
    }
}
