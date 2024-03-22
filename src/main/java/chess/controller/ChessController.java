package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.Positions;
import chess.domain.piece.character.Character;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;

public class ChessController {
    public void play() {
        InputView.inputStartCommand();
        Board board = new Board(BoardFactory.generateStartBoard());
        ChessGame chessGame = new ChessGame(board);

        OutputView.printChessBoard(board.mapPositionToCharacter());

        while (InputView.inputNextMove()) {
            Positions positions = InputView.inputPositions();
            Map<Position, Character> chessBoard = chessGame.movePiece(positions, OutputView::printCheck);
            OutputView.printChessBoard(chessBoard);
        }
    }
}
