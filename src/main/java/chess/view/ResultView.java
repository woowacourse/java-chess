package chess.view;

import static chess.view.command.StartCommand.END;

import java.util.List;

import chess.view.dto.ChessboardDto;

public class ResultView {

    public void printGameEnd() {
        System.out.printf(END.getMessage());
    }

    public void printBoard(final ChessboardDto chessboardDto) {
        List<List<String>> chessboard = chessboardDto.getChessboard();
        chessboard.forEach(squares -> System.out.println(String.join("", squares)));
        System.out.println();
    }
}

