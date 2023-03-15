package chess.controller;

import chess.domain.dto.PiecesResponse;
import chess.domain.Pieces;
import chess.domain.Player;
import chess.domain.Players;
import chess.ui.OutputView;

public final class ChessGameController {

    public void execute() {
        Players players = initializeChessBoard();

    }

    private Players initializeChessBoard() {
        Pieces whitePieces = Pieces.createWhitePieces();
        Pieces blackPieces = Pieces.createBlackPieces(whitePieces);

        Player whitePlayer = Player.fromWhitePlayer(whitePieces);
        Player blackPlayer = Player.fromBlackPlayer(blackPieces);

        PiecesResponse piecesResponse = new PiecesResponse(whitePlayer, blackPlayer);

        OutputView.printInitializedChessBoard(piecesResponse);

        return Players.from(whitePlayer, blackPlayer);
    }

}
