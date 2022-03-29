package chess2.dto2;

import static chess.util.PositionUtil.FILES_TOTAL_SIZE;
import static chess.util.PositionUtil.RANKS_TOTAL_SIZE;

import chess2.domain2.board2.Board;
import chess2.domain2.board2.position.Position;
import chess2.domain2.board2.piece2.Piece;
import chess2.util2.PieceDisplayUtil;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardViewDto {

    private final List<String> boardDisplay;

    public BoardViewDto(Board board) {
        boardDisplay = toBoardDisplay(board.toMap());
    }

    private static List<String> toBoardDisplay(Map<Position, Piece> board) {
        List<String> boardDisplay = IntStream.range(0, RANKS_TOTAL_SIZE)
                .mapToObj(rankIdx -> toRowDisplay(board, rankIdx))
                .collect(Collectors.toList());

        Collections.reverse(boardDisplay);
        return boardDisplay;
    }

    private static String toRowDisplay(Map<Position, Piece> board, int rankIdx) {
        return IntStream.range(0, FILES_TOTAL_SIZE)
                .mapToObj(fileIdx -> Position.of(fileIdx, rankIdx))
                .map(board::get)
                .map(PieceDisplayUtil::toDisplay)
                .collect(Collectors.joining());
    }

    public List<String> display() {
        return boardDisplay;
    }
}
