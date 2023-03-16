package chess.domain.dto.res;

import chess.domain.Player;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PiecesResponse {

    private final List<PieceResponse> pieceResponse;

    public PiecesResponse (Player whitePlayer, Player blackPlayer) {
        List<PieceResponse> whitePieces = getPiecesByColor(whitePlayer);
        List<PieceResponse> blackPieces = getPiecesByColor(blackPlayer);

        this.pieceResponse = Stream.concat(whitePieces.stream(), blackPieces.stream())
                .collect(toList());
    }

    private List<PieceResponse> getPiecesByColor(Player player) {
        return player.getPieces().stream()
                .map(piece -> new PieceResponse(piece, player.getColor()))
                .collect(toList());
    }

    public boolean isExistPosition(int rank, int file) {
        return pieceResponse.stream()
                .anyMatch(response -> response.samePosition(rank, file));
    }

    public String findNameByRankAndFile(int rank, int file) {
        return pieceResponse.stream()
                .filter(response -> response.samePosition(rank, file))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다."))
                .getName();
    }

}
