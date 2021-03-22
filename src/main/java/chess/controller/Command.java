package chess.controller;

import chess.domain.Game;
import chess.domain.position.Position;
import chess.domain.result.Result;
import chess.domain.state.End;
import chess.domain.state.Running;
import chess.view.OutputView;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public enum Command {
    START("start", Command::start),
    MOVE("move ([a-h][1-8]) ([a-h][1-8])", Command::move),
    END("end", Command::end),
    STATUS("status", Command::showStatus);

    private final String message;
    private final BiConsumer<String, Game> consumer;

    Command(String message, BiConsumer<String, Game> consumer) {
        this.message = message;
        this.consumer = consumer;
    }

    private static void start(String input, Game game) {
        if(game.isRunning()){
            throw new IllegalArgumentException("이미 게임을 진행중입니다.");
        }
        game.changeState(new Running());
        OutputView.printBoard(game.getBoard());
        OutputView.printTurn(game.getTurn());
    }

    private static void move(String input, Game game) {
        if(!game.isRunning()){
            throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
        }
        game.move(positionOf(input, 1), positionOf(input, 2));
        OutputView.printBoard(game.getBoard());
    }

    private static void end(String input, Game game) {
        showStatus(input, game);
        OutputView.printFinishedMessage();
        game.changeState(new End());
    }

    private static void showStatus(String input, Game game) {
        if(!game.isRunning()){
            throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
        }
        Result result = game.getResult();
        OutputView.printWinner(result.findWinner());
    }

    public static Command of(String value) {
        return Stream.of(values())
            .filter(command -> command.is(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("없는 명령어입니다."));
    }

    private static Position positionOf(String input, int pieceIndex) {
        Pattern pattern = Pattern.compile(MOVE.message);
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()){
            return Position.of(matcher.group(pieceIndex));
        }
        throw new IllegalArgumentException("유효하지 않은 체스말을 입력했습니다.");
    }

    private boolean is(String value) {
        return value.matches(this.message);
    }

    public void execute(String input, Game game) {
        consumer.accept(input, game);
    }
}
