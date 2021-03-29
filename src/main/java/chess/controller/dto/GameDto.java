package chess.controller.dto;

import chess.domain.game.ChessGame;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GameDto {

    private final String status;
    private final List<PieceDto> pieceDtos;

    public GameDto(ChessGame chessGame) {
        this.pieceDtos = chessGame.getBoard()
                .getAllPieces().stream()
                .map(PieceDto::new)
                .collect(toList());

        this.status = getStatus(chessGame);
    }

    private String getStatus(ChessGame chessGame) {
        return chessGame.getStatus();
    }

    public List<PieceDto> getPieceDtos() {
        return pieceDtos;
    }

    public String getStatus() {
        return status;
    }

}
