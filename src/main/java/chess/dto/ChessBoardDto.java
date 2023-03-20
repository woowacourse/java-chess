package chess.dto;

import chess.board.ChessBoard;
import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.piece.Piece;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ChessBoardDto {

    private final List<PieceDto> pieceDtos;

    public static ChessBoardDto toView(final ChessBoard board) {
        final List<PieceDto> pieces = pieceToView(board.getPiecePosition());
        return new ChessBoardDto(pieces);
    }

    private ChessBoardDto(final List<PieceDto> pieceDtos) {
        this.pieceDtos = pieceDtos;
    }

    private static List<PieceDto> pieceToView(final Map<Position, Piece> piecePosition) {
        final List<PieceDto> pieceDtoList = new LinkedList<>();

        Arrays.stream(Rank.values())
                .forEach(rank -> Arrays.stream(File.values())
                .map(file -> piecePosition.get(new Position(file, rank)))
                .map(PieceDto::new)
                .forEach(pieceDtoList::add));

        return pieceDtoList;
    }

    public List<PieceDto> getPieceDtos() {
        return pieceDtos;
    }
}
