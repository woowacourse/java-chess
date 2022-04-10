package chess.dto.board;

import chess.domain.board.Board;
import chess.domain.board.piece.Piece;
import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.util.PieceDisplayUtil;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleBoardViewDto {

    private final List<String> boardDisplay;

    public ConsoleBoardViewDto(Board board) {
        boardDisplay = toBoardDisplay(board.toMap());
    }

    private static List<String> toBoardDisplay(Map<Position, Piece> board) {
        return Rank.allRanksDescending()
                .stream()
                .map(rank -> toRowDisplay(board, rank))
                .collect(Collectors.toList());
    }

    private static String toRowDisplay(Map<Position, Piece> board, Rank rank) {
        return File.allFilesAscending()
                .stream()
                .map(file -> Position.of(file, rank))
                .map(board::get)
                .map(PieceDisplayUtil::toDisplay)
                .collect(Collectors.joining());
    }

    public List<String> toDisplay() {
        return boardDisplay;
    }
}
