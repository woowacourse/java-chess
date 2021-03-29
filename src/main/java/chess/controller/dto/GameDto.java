package chess.controller.dto;

import chess.domain.game.ChessGame;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GameDto {
    private static String BLACK = "black";
    private static String WHITE = "white";

    private final String turn;
    private final List<PieceDto> pieceDtos;

    public GameDto(ChessGame chessGame) {
        this.pieceDtos = chessGame.getBoard()
                .getAllPieces().stream()
                .map(PieceDto::new)
                .collect(toList());

        turn = getTurn(chessGame);
    }

    private String getTurn(ChessGame chessGame) {
        if(chessGame.isBlackTurn()) {
            return BLACK;
        }

        return WHITE;
    }

    public List<PieceDto> getPieceDtos() {
        return pieceDtos;
    }

    public String getTurn() {
        return turn;
    }

}
