package chess.controller;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.dto.BoardDto;
import chess.domain.piece.Piece;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChessController {

    public void startChess() {
        Board board = makeBoard();
        OutputView.printBoard(makeBoardDto(board.getBoard()));
    }

    private Board makeBoard() {
        return Board.initialize();
    }

    private BoardDto makeBoardDto(Map<Position, Piece> board) {
        List<List<String>> rawBoard = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            List<String> row = new ArrayList<>(Collections.nCopies(8, "."));
            rawBoard.add(row);
        }

        for (var entrySet : board.entrySet()) {
            Position position = entrySet.getKey();
            Piece piece = entrySet.getValue();

            // TODO: 무슨 piece인지 검사해서 원하는 출력값 출력하게 하기

            rawBoard.get(position.getY() - 1).set(position.getX() - 1, "p");
        }
        return new BoardDto(rawBoard);
    }
}
