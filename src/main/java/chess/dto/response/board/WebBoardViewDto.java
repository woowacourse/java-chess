package chess.dto.response.board;

import chess.domain.board.Board;
import chess.domain.board.piece.Piece;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WebBoardViewDto {

    private final List<RowDto> boardDisplay;

    public WebBoardViewDto(Board board) {
        boardDisplay = toBoardDisplay(board.toMap());
    }

    private static List<RowDto> toBoardDisplay(Map<Position, Piece> board) {
        return Rank.allRanksDescending()
                .stream()
                .map(rank -> new RowDto(board, rank))
                .collect(Collectors.toList());
    }

    public List<RowDto> toDisplay() {
        return boardDisplay;
    }
}
