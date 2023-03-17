package chess.dto;

import chess.board.ChessBoard;
import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.piece.Piece;

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

        for (final Rank rank : Rank.values()) {
            for (final File file : File.values()) {
                final Piece piece = piecePosition.get(new Position(file, rank));
                pieceDtoList.add(new PieceDto(piece));
            }
        }
        return pieceDtoList;
    }

    public List<PieceDto> getPieceDtos() {
        return pieceDtos;
    }
}
