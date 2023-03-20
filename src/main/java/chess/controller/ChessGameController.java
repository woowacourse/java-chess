package chess.controller;

import chess.domain.Position;
import chess.domain.dto.res.PiecesResponse;
import chess.domain.Pieces;
import chess.domain.Player;
import chess.domain.Players;
import chess.ui.InputView;
import chess.ui.OutputView;

import java.util.List;

public final class ChessGameController {

    public void execute() {
        Players players = initializeChessBoard();
        List<String> command;
        while (true) {
            command = InputView.getCommand();
            if(command.get(0).equals("end")) {
                break;
            }
            String inputMovablePiece = command.get(1);
            String inputTargetPosition = command.get(2);

            try {
                Position findPosition = players.findPositionByInputPoint(inputMovablePiece);
                Player findPlayer = players.findPlayerByPosition(findPosition);
                Player anotherPlayer = players.getAnotherPlayer(findPlayer);
                Position changedPosition = findPlayer.movePieceByInput(players.getAllPosition(), findPosition, inputTargetPosition);
                players.validateAlreadyExistPieceMovingRoute(findPosition, changedPosition);
                anotherPlayer.removePiece(changedPosition);

                OutputView.printChessBoardStatus(new PiecesResponse(findPlayer, anotherPlayer));
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }

    }

    private Players initializeChessGame() {
        Pieces whitePieces = Pieces.createWhitePieces();
        Pieces blackPieces = Pieces.createBlackPieces(whitePieces);

        Player whitePlayer = Player.fromWhitePlayer(whitePieces);
        Player blackPlayer = Player.fromBlackPlayer(blackPieces);

        PiecesResponse piecesResponse = new PiecesResponse(whitePlayer, blackPlayer);
        OutputView.printInitializedChessBoard(piecesResponse);

        return Players.from(whitePlayer, blackPlayer);
    }

}
