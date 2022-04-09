package chess.web.dto;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PiecesDto {

    private final List<PieceDto> value;

    private PiecesDto(List<PieceDto> value) {
        this.value = value;
    }

    public static PiecesDto of(Board board) {
        List<PieceDto> pieceDtos = new ArrayList<>();
        for (Position position : getAllPosition()) {
            addPieceDto(pieceDtos, position, board);
        }
        return new PiecesDto(pieceDtos);
    }

    private static List<Position> getAllPosition() {
        List<Position> positions = new ArrayList<>();
        for (Rank rank : getReverseOrderRanks()) {
            positions.addAll(Arrays.stream(File.values())
                    .map(file -> Position.of(file, rank))
                    .collect(Collectors.toList()));
        }
        return positions;
    }

    private static void addPieceDto(List<PieceDto> pieces, Position position, Board board) {
        try {
            pieces.add(PieceDto.of(board.findPieceInPosition(position)));
        } catch (IllegalArgumentException e) {
            pieces.add(new PieceDto("", ""));
        }
    }

    private static List<Rank> getReverseOrderRanks() {
        return Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public List<PieceDto> getValue() {
        return value;
    }
}
