package chess.controller;

import static chess.domain.Command.END;
import static chess.domain.Command.MOVE;
import static chess.domain.Command.START;

import chess.domain.Board;
import chess.domain.Command;
import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Square;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameStart();

        List<String> gameCommand = inputView.readGameCommand();
        Command command = Command.from(gameCommand.get(0));

        if (command == MOVE) {
            throw new IllegalArgumentException("start 또는 end를 입력해주세요.");
        }

        if (command == START) {
            Board board = new Board();
            Color color = Color.WHITE;
            Map<Square, Piece> pieces = board.getPieces();
            outputView.printChessBoard(pieces);

            while (true) {
                gameCommand = inputView.readGameCommand();
                command = Command.from(gameCommand.get(0));

                if (command == END) {
                    break;
                }

                if (command == START) {
                    throw new IllegalArgumentException("move 또는 end를 입력해주세요.");
                }

                if (gameCommand.size() != 3) {
                    throw new IllegalArgumentException("move source위치 target위치 형태로 입력해주세요.");
                }

                List<String> srcPosition = Arrays.asList(gameCommand.get(1).split(""));
                List<String> dstPosition = Arrays.asList(gameCommand.get(2).split(""));

                if (srcPosition.size() != 2 && dstPosition.size() != 2) {
                    throw new IllegalArgumentException("source위치, target 위치는 알파벳(a~h)과 숫자(1~8)로 입력해주세요. 예) a1");
                }

                Square src = Square.of(File.findFileBy(srcPosition.get(0)), Rank.findRankBy(srcPosition.get(1)));
                Square dst = Square.of(File.findFileBy(dstPosition.get(0)), Rank.findRankBy(dstPosition.get(1)));

                if (!board.isSameColor(src, color)) {
                    throw new IllegalArgumentException("다른 색 말을 움직여 주세요.");
                }
                board.move(src, dst);
                outputView.printChessBoard(pieces);
                color = Color.change(color);
            }

        }
    }
}
