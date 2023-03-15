package chess.controller;

import chess.domain.dto.PiecesResponse;
import chess.domain.Pieces;
import chess.domain.Player;
import chess.domain.Players;
import chess.ui.InputView;
import chess.ui.OutputView;

import java.util.List;

public final class ChessGameController {

    public void execute() {
        Players players = initializeChessBoard();
        List<String> movePieceCommand = InputView.getMovePieceCommand();
        String movablePiece = movePieceCommand.get(1);
        String targetPosition = movePieceCommand.get(2);
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
