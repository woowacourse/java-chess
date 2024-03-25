package service.command;

import domain.chessboard.ChessBoardDao;
import domain.piece.Piece;
import domain.square.Square;
import service.ChessGame;

import java.util.Map;
import java.util.function.BiConsumer;

public class EndCommand implements ChessCommand {

    @Override
    public void execute(final ChessGame chessGame, final BiConsumer<ChessGame, Boolean> callBack) {
        chessGame.end();

        final ChessBoardDao chessBoardDao = new ChessBoardDao();

        final Map<Square, Piece> pieceSquares = chessGame.getPieceSquares();
        pieceSquares.forEach((chessBoardDao::addSquarePiece));
    }
}
