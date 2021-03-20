package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.player.Player;
import chess.domain.player.Players;
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
        if (Command.isStatus(values.get(0))) {

            return;
        }
        Position source = Position.ofName(values.get(1));
        Piece chosenPiece = board.findPieceBy(source);
        if (!players.currentPlayer(currentColor).isOwnerOf(chosenPiece)) {
            throw new IllegalArgumentException("말을 움직일 수 없습니다.");
        }
        Position target = Position.ofName(values.get(2));
        board.move(chosenPiece, target);
        currentColor = currentColor.reversed();

        if (board.kingDead()) {
            isPlaying = false;
        }
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
