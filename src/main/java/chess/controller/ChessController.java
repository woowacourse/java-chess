package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.team.BlackTeam;
import chess.domain.team.WhiteTeam;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PieceNameConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessController {
    private static final String MOVE_COMMAND = "move";
    private static final String STATUS_COMMAND = "status";

    public void run() {
        OutputView.printChessStartMessage();
        final boolean start = InputView.inputChessStartOrEnd();
        if (start) {
            final ChessGame chessGame = new ChessGame(new BlackTeam(), new WhiteTeam());
            playChessGame(chessGame);
        }
    }

    private void playChessGame(ChessGame chessGame) {
        while (!chessGame.isEnd()) {
            printChessBoard(chessGame);
            singleTurn(chessGame);
        }
        showGameStatus(chessGame);
    }

    private void singleTurn(ChessGame chessGame) {
        try {
            List<String> movePositions = InputView.inputUserCommand();
            controlUserCommand(chessGame, movePositions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            singleTurn(chessGame);
        }
    }

    private void controlUserCommand(ChessGame chessGame, List<String> movePositions) {
        if (MOVE_COMMAND.equals(movePositions.get(0))) {
            chessGame.move(Position.of(movePositions.get(1)), Position.of(movePositions.get(2)));
            chessGame.changeTurn();
        }

        if (STATUS_COMMAND.equals(movePositions.get(0))) {
           showGameStatus(chessGame);
        }
    }

    private void printChessBoard(final ChessGame chessGame) {
        final Map<Position, String> chessBoard = convertToBlackPrintName(chessGame);
        chessBoard.putAll(convertToWhitePrintName(chessGame));
        OutputView.printChessBoard(chessBoard);
    }

    private Map<Position, String> convertToBlackPrintName(final ChessGame chessGame) {
        final Map<Position, Piece> blackPosition = chessGame.getBlackTeam().getPiecePosition();
        final Map<Position, String> blackPrintFormat = new HashMap<>();
        for (Position position : blackPosition.keySet()) {
            final Piece piece = blackPosition.get(position);
            blackPrintFormat.put(position, PieceNameConverter.convert(piece).toUpperCase());
        }
        return blackPrintFormat;
    }

    private Map<Position, String> convertToWhitePrintName(final ChessGame chessGame) {
        final Map<Position, Piece> whitePosition = chessGame.getWhiteTeam().getPiecePosition();
        final Map<Position, String> whitePrintFormat = new HashMap<>();
        for (Position position : whitePosition.keySet()) {
            final Piece piece = whitePosition.get(position);
            whitePrintFormat.put(position, PieceNameConverter.convert(piece).toLowerCase());
        }
        return whitePrintFormat;
    }

    private void showGameStatus(ChessGame chessGame) {
        final double blackTeamScore = chessGame.calculateScoreByTeam(ChessGame.BLACK_TEAM);
        final double whiteTeamScore = chessGame.calculateScoreByTeam(ChessGame.WHITE_TEAM);
        OutputView.printChessGameResult(blackTeamScore, whiteTeamScore);
    }
}