package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;

public class Game {

    private final Board board;
    private final Players players;
    private PieceColor currentColor;
    private boolean isPlaying;

    public Game() {
        board = BoardFactory.initializeBoard();
        players = Players.of(Arrays.asList(
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
        Position source = Position.of(values.get(1));
        Piece chosenPiece = board.findPieceBy(source);
        if (!players.currentPlayer(currentColor).isOwnerOf(chosenPiece)) {
            throw new IllegalArgumentException("말을 움직일 수 없습니다.");
        }
        Position target = Position.of(values.get(2));
//        board.move(players.currentPlayer(currentColor), values);
//        board.move2(source, target);
        board.move3(chosenPiece, target);
        currentColor = currentColor.reversed();
    }

    public void execute(Command command) {
        // input 이 end 일 경우

        // input 이 move position position 일 경우

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
