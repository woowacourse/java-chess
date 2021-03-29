package chess.dto;

import chess.domain.board.*;
import chess.domain.piece.TeamType;

import java.util.*;
import java.util.stream.Collectors;

public class BoardDTO {

    private final List<RowDTO> rows;
    private final TeamType currentTeamType;

    private BoardDTO(List<RowDTO> rows, TeamType currentTeamType) {
        this.rows = rows;
        this.currentTeamType = currentTeamType;
    }

    public static BoardDTO from(ChessBoard chessBoard, TeamType currentTeamType) {
        Map<Coordinate, Cell> cells = chessBoard.getCells();
        List<RowDTO> rows = new ArrayList<>();
        List<Rank> ranks = Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        for (Rank rank : ranks) {
            List<PieceDTO> piecesByRow = writePiecesByRow(rank, cells);
            RowDTO rowDTO = new RowDTO(piecesByRow);
            rows.add(rowDTO);
        }
        return new BoardDTO(rows, currentTeamType);
    }

    private static List<PieceDTO> writePiecesByRow(Rank rank, Map<Coordinate, Cell> cells) {
        List<PieceDTO> piecesByRow = new ArrayList<>();
        for (File file : File.values()) {
            Coordinate coordinate = new Coordinate(file, rank);
            Cell cell = cells.get(coordinate);
            PieceDTO pieceDTO = PieceDTO.from(cell);
            piecesByRow.add(pieceDTO);
        }
        return piecesByRow;
    }

    public List<RowDTO> getRows() {
        return rows;
    }

    public TeamType getCurrentTeamType() {
        return currentTeamType;
    }
}
