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
        List<PieceDto> pieces = pieceToView(board.getPiecePosition());
        return new ChessBoardDto(pieces);
    }

    private ChessBoardDto(final List<PieceDto> pieceDtos) {
        this.pieceDtos = pieceDtos;
    }

    private static List<PieceDto> pieceToView(final Map<Position, Piece> piecePosition) {
        List<PieceDto> pieceDtoList = new LinkedList<>();

        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                Piece piece = piecePosition.get(new Position(file, rank));
                pieceDtoList.add(new PieceDto(piece));
            }
        }
        return pieceDtoList;
    }

    public List<PieceDto> getPieceDtos() {
        return pieceDtos;
    }
}
