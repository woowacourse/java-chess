package chess.dto;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {
    private final List<PieceOfRankDto> board;

    private BoardDto(final List<PieceOfRankDto> board) {
        this.board = board;
    }

    public static BoardDto from(final Board board) {
        List<PieceOfRankDto> pieceOfRankDtos = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            List<Piece> piecesOfRank = Arrays.stream(File.values())
                    .map(file -> board.findPiece(file, rank))
                    .collect(Collectors.toList());
            pieceOfRankDtos.add(PieceOfRankDto.from(piecesOfRank));
        }
        return new BoardDto(pieceOfRankDtos);
    }

    public List<PieceOfRankDto> getBoard() {
        return board;
    }
}
