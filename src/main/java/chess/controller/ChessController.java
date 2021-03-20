package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.team.BlackTeam;
import chess.domain.team.WhiteTeam;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PiecePrintFormat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessController {
    private static final String MOVE_COMMAND = "move";
    private static final String STATUS_COMMAND = "status";

    public void run() {
        OutputView.printChessStartMessage();
        final boolean start = InputView.inputChessStartOrEnd();
        if (start) {
            final ChessGame chessGame = new ChessGame(new BlackTeam(), new WhiteTeam());
            playChessGame(chessGame);
            printChessResult(chessGame);
        }
    }

    private void playChessGame(final ChessGame chessGame) {
        while (chessGame.isPlaying()) {
            printChessBoard(chessGame);
            progressSingleTurn(chessGame);
            chessGame.changeTurn();
        }
    }

    private void printChessBoard(final ChessGame chessGame) {
        final Map<Position, String> chessBoard = convertBlackTeamPiece(chessGame);
        chessBoard.putAll(convertWhiteTeamPiece(chessGame));
        OutputView.printChessBoard(chessBoard);
    }

    private Map<Position, String> convertBlackTeamPiece(final ChessGame chessGame) {
        final Map<Position, Piece> blackPosition = chessGame.getBlackTeam().getPiecePosition();
        return blackPosition.keySet().stream()
                .collect(Collectors.toMap(
                        position -> position,
                        position -> PiecePrintFormat.convert(blackPosition.get(position)).toUpperCase()));
    }

    private Map<Position, String> convertWhiteTeamPiece(final ChessGame chessGame) {
        final Map<Position, Piece> whitePosition = chessGame.getWhiteTeam().getPiecePosition();
        return whitePosition.keySet().stream()
                .collect(Collectors.toMap(
                        position -> position,
                        position -> PiecePrintFormat.convert(whitePosition.get(position)).toLowerCase()));
    }

    private void progressSingleTurn(final ChessGame chessGame) {
        try {
            final List<String> turnOption = InputView.inputTurnOption();
            controlUserCommand(chessGame, turnOption);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
            progressSingleTurn(chessGame);
        }
    }

    private void controlUserCommand(final ChessGame chessGame, final List<String> movePositions) {
        if (MOVE_COMMAND.equals(movePositions.get(0))) {
            final Position start = Position.of(movePositions.get(1));
            final Position destination = Position.of(movePositions.get(2));
            chessGame.move(start, destination);
        }
        if (STATUS_COMMAND.equals(movePositions.get(0))) {
            chessGame.finish();
        }
    }

    private void printChessResult(ChessGame chessGame) {
        final double blackTeamScore = chessGame.calculateBlackTeamScore();
        final double whiteTeamScore = chessGame.calculateWhiteTeamScore();
        OutputView.printChessGameResult(blackTeamScore, whiteTeamScore);
    }
}