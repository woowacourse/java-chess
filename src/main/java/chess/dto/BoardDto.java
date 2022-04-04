package chess.dto;

import chess.model.Board;
import chess.model.piece.Piece;
import chess.model.square.File;
import chess.model.square.Rank;
import chess.model.square.Square;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class BoardDto {

    private final List<List<PieceDto>> dto;

    private BoardDto(List<List<PieceDto>> dto) {
        this.dto = dto;
    }

    public static BoardDto of(Board board) {
        List<List<PieceDto>> boardDto = new ArrayList<>();
        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);
        for (Rank rank : ranks) {
            boardDto.add(makeLineByFile(board, rank));
        }
        return new BoardDto(boardDto);
    }

    public List<List<PieceDto>> getDto() {
        return dto;
    }

    private static List<PieceDto> makeLineByFile(Board board, Rank rank) {
        List<PieceDto> tempLine = new ArrayList<>();
        for (File file : File.values()) {
            Piece piece = board.get(Square.of(file, rank));
            tempLine.add(toPieceDto(piece, rank, file));
        }
        return tempLine;
    }

    private static PieceDto toPieceDto(Piece piece, Rank rank, File file) {
        if (piece.isBlack()) {
            return PieceDto.of(piece.name().toUpperCase(Locale.ROOT), rank, file);
        }
        return PieceDto.of(piece.name(), rank, file);
    }
}
