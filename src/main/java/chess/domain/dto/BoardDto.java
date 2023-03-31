package chess.domain.dto;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.board.Board;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardDto {
    private final List<List<PieceDto>> pieces;

    private BoardDto(List<List<PieceDto>> pieces) {
        this.pieces = pieces;
    }

    public static BoardDto of(Board board) {
        Map<Position, Piece> boardData = board.getBoardData();
        List<List<PieceDto>> response = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            List<PieceDto> pieceRespons = Arrays.stream(File.values())
                    .map(file -> Position.of(file, rank))
                    .map(boardData::get)
                    .map(PieceDto::from)
                    .collect(Collectors.toList());
            response.add(pieceRespons);
        }
        return new BoardDto(response);
    }

    public List<List<PieceDto>> getPieces() {
        return pieces;
    }
}
