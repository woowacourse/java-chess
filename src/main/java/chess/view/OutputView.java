package chess.view;

import chess.domain.board.BoardOutput;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void writeBoard(BoardOutput boardOutput) {
        List<String> piecesByRank = boardOutput.pieces().stream()
                .map(row -> row.stream()
                        .map(PieceView::toView)
                        .collect(Collectors.joining()))
                .toList();

        System.out.println(String.join("\n", piecesByRank));
    }
}
