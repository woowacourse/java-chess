package chess.repository.exchanger;

import chess.domain.chessGame.ChessBoard;
import chess.domain.chessGame.Turn;

public class ChessBoardSpliter implements ObjectSpliter<ChessBoard, StringSpaceGenerator, Turn> {

    @Override
    public ChessBoard combine(StringSpaceGenerator spaceGenerator, Turn turn) {
        return ChessBoard.of(spaceGenerator, turn);
    }

    @Override
    public StringSpaceGenerator splitFirst(ChessBoard chessBoard) {
        return new StringSpaceGenerator(String.join("", chessBoard.showBoard()));
    }

    @Override
    public Turn splitSecond(ChessBoard chessBoard) {
        return new TurnConverter().convertToObject(chessBoard.showTurn());
    }
}
