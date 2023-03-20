package chess.controller;

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
            command = InputView.getCommands();
            if(command.get(0).equals("end")) {
                break;
            }
            String inputMovablePiece = command.get(1);
            String inputTargetPosition = command.get(2);

            try {
                players.movePiece(inputMovablePiece, inputTargetPosition);
                OutputView.printChessBoardStatus(new PiecesResponse(players.getWhitePlayer(), players.getBlackPlayer()));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                OutputView.printErrorMessage(e.getMessage());
            }
        }

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
