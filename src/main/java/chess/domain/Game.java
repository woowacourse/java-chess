package chess.domain;

import chess.domain.piece.PieceColor;
import java.util.Arrays;
import java.util.List;

public class Game {

    private final Board board;
    private final Players players;
    private PieceColor currentColor;
    private boolean isPlaying;

    public Game() {
        board = BoardFactory.initializeBoard();
        players = new Players(Arrays.asList(
                Player.of(PieceColor.WHITE),
                Player.of(PieceColor.BLACK)
                ));
        currentColor = PieceColor.WHITE;
        isPlaying = true;
    }

    public void command(String input) {
        List<String> values = Arrays.asList(input.split(" "));
        if (Command.isEnd(values.get(0))) {
            isPlaying = false;
            return;
        }
        board.move(players.currentPlayer(currentColor), values);
        currentColor = currentColor.reversed();
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public Board getBoard() {
        return board;
    }

    public PieceColor getCurrentColor() {
        return currentColor;
    }
}
