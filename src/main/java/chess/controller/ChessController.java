package chess.controller;

import chess.controller.dto.PieceResponseDTO;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.stream.Collectors;

public class ChessController {

    private final ChessGame chessGame;

    public ChessController(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public List<PieceResponseDTO> startGame() {
        List<Piece> pieces = chessGame.currentPieces().asList();
        return pieces.stream()
            .map(PieceResponseDTO::new)
            .collect(Collectors.toList());
    }
}
