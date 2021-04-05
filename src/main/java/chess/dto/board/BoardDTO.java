package chess.dto.board;

import chess.domain.board.*;
import chess.domain.piece.TeamType;

import java.util.*;
import java.util.stream.Collectors;

public class BoardDTO {

    private final List<RowDTO> rows;
    private final TeamType currentTeamType;
    private final boolean isCheckmate;

    private BoardDTO(List<RowDTO> rows, TeamType currentTeamType, boolean isCheckmate) {
        this.rows = rows;
        this.currentTeamType = currentTeamType;
        this.isCheckmate = isCheckmate;
    }

    public static BoardDTO of(ChessBoard chessBoard, TeamType currentTeamType) {
        Map<Coordinate, Cell> cells = chessBoard.getCells();
        List<RowDTO> rows = new ArrayList<>();
        List<Rank> ranks = Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        for (Rank rank : ranks) {
            List<PieceDTO> piecesByRow = aggregatePiecesByRow(rank, cells);
            RowDTO rowDTO = new RowDTO(piecesByRow);
            rows.add(rowDTO);
        }
        return new BoardDTO(rows, currentTeamType, chessBoard.isKingCheckmate());
    }

    private static List<PieceDTO> aggregatePiecesByRow(Rank rank, Map<Coordinate, Cell> cells) {
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

    public boolean isCheckmate() {
        return isCheckmate;
    }
}
