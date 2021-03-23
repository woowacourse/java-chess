package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.team.Team;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PiecePrintFormat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessController {
    private static final String MOVE_PIECE = "move";
    private static final String SHOW_SCORE = "status";
    private static final String END_GAME = "end";

    public void run() {
        OutputView.printChessStartMessage();
        final boolean start = InputView.inputChessStartOrEnd();
        if (start) {
            final ChessGame chessGame = new ChessGame(Team.blackTeam(), Team.whiteTeam());
            playChessGame(chessGame);
            printChessScore(chessGame);
        }
    }

    private void playChessGame(final ChessGame chessGame) {
        while (chessGame.isPlaying()) {
            printChessBoard(chessGame);
            progressSingleTurn(chessGame);
        }
    }

    private void printChessBoard(final ChessGame chessGame) {
        final Map<Position, String> chessBoard = convertBlackTeamPiece(chessGame);
        chessBoard.putAll(convertWhiteTeamPiece(chessGame));
        OutputView.printChessBoard(chessBoard);
    }

    private Map<Position, String> convertBlackTeamPiece(final ChessGame chessGame) {
        final Map<Position, Piece> blackPosition = chessGame.currentBlackPiecePosition();
        return blackPosition.keySet().stream()
                .collect(Collectors.toMap(
                        position -> position,
                        position -> PiecePrintFormat.convert(blackPosition.get(position)).toUpperCase()));
    }

    private Map<Position, String> convertWhiteTeamPiece(final ChessGame chessGame) {
        final Map<Position, Piece> whitePosition = chessGame.currentWhitePiecePosition();
        return whitePosition.keySet().stream()
                .collect(Collectors.toMap(
                        position -> position,
                        position -> PiecePrintFormat.convert(whitePosition.get(position)).toLowerCase()));
    }

    private void progressSingleTurn(final ChessGame chessGame) {
        try {
            final List<String> turnOption = InputView.inputTurnOption();
            controlUserCommand(chessGame, turnOption);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            progressSingleTurn(chessGame);
        }
    }

    private void controlUserCommand(final ChessGame chessGame, final List<String> turnOption) {
        if (MOVE_PIECE.equals(turnOption.get(0))) {
            chessGame.move(Position.of(turnOption.get(1)), Position.of(turnOption.get(2)));
        }
        if (SHOW_SCORE.equals(turnOption.get(0))) {
            printChessScore(chessGame);
            progressSingleTurn(chessGame);
        }
        if (END_GAME.equals(turnOption.get(0))) {
            chessGame.finish();
        }
    }

    private void printChessScore(final ChessGame chessGame) {
        final double blackTeamScore = chessGame.calculateBlackTeamScore();
        final double whiteTeamScore = chessGame.calculateWhiteTeamScore();
        OutputView.printChessGameResult(blackTeamScore, whiteTeamScore);
    }
}
