package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.Piece;
import chess.domain.team.BlackTeam;
import chess.domain.team.WhiteTeam;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PieceNameConverter;

import java.util.HashMap;
import java.util.Map;

public class ChessController {

    public void run() {
        OutputView.printChessStartMessage();
        final boolean start = InputView.inputChessStartOrEnd();
        if (!start) {
            return;
        }
        final ChessGame chessGame = new ChessGame(new BlackTeam(), new WhiteTeam());
        final Map<Position, Piece> blackPosition = chessGame.getBlackTeam().getPiecePosition();
        final Map<Position, Piece> whitePosition = chessGame.getWhiteTeam().getPiecePosition();
        final Map<Position, String> chessBoard = convertToBlackPrintName(blackPosition);
        chessBoard.putAll(convertToWhitePrintName(whitePosition));
        OutputView.printChessBoard(chessBoard);
    }

    private Map<Position, String> convertToBlackPrintName(final Map<Position, Piece> blackPosition) {
        final Map<Position, String> blackPrintFormat = new HashMap<>();
        for (Position position : blackPosition.keySet()) {
            final Piece piece = blackPosition.get(position);
            blackPrintFormat.put(position, PieceNameConverter.convert(piece).toUpperCase());
        }
        return blackPrintFormat;
    }


    private Map<Position, String> convertToWhitePrintName(final Map<Position, Piece> whitePosition) {
        final Map<Position, String> whitePrintFormat = new HashMap<>();
        for (Position position : whitePosition.keySet()) {
            final Piece piece = whitePosition.get(position);
            whitePrintFormat.put(position, PieceNameConverter.convert(piece).toLowerCase());
        }
        return whitePrintFormat;
    }
}
