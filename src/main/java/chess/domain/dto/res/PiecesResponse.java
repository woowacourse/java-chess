package chess.domain.dto.res;

import chess.domain.Color;
import chess.domain.Piece;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PiecesResponse {

    private final List<PieceResponse> pieceResponse;

    public PiecesResponse(final List<Piece> whitePieces, final List<Piece> blackPieces) {
        List<PieceResponse> whitePiecesResponse = getPiecesByColor(whitePieces, Color.WHITE);
        List<PieceResponse> blackPiecesResponse = getPiecesByColor(blackPieces, Color.BLACK);
        this.pieceResponse = Stream.concat(whitePiecesResponse.stream(), blackPiecesResponse.stream())
                .collect(toList());
    }

    private List<PieceResponse> getPiecesByColor(final List<Piece> pieces, final Color color) {
        return pieces.stream()
                .map(piece -> new PieceResponse(piece, color))
                .collect(toList());
    }

    public boolean isExistPosition(final int rank, final int file) {
        return pieceResponse.stream()
                .anyMatch(response -> response.samePosition(rank, file));
    }

    public String findNameByRankAndFile(final int rank, final int file) {
        return pieceResponse.stream()
                .filter(response -> response.samePosition(rank, file))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다."))
                .getName();
    }

}
