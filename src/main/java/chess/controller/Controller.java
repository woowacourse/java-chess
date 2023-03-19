package chess.controller;

import chess.boardstrategy.BoardStrategy;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Controller {

    private static final int COMMAND_INDEX_IN_COMMANDLINE =0;
    private static final int START_SOURCE_INDEX_IN_COMMANDLINE = 1;
    private static final int TARGET_SOURCE_INDEX_IN_COMMANDLINE = 2;

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Command, Consumer<List<String>>> actionsByCommand;

    /**
     * todo: 질문 1
     *
     * 컨트롤러 생성시, 인자로 받는 chessGame과 boardStrategy는
     * Command의 value값인, Consumer 람다식에 쓰이는 것외에는 쓰이지 않습니다.
     * 이러한 경우, 1) 컨트롤러 생성시 의존성 주입으로 ChessGame과 BoardStrategy를 주입하는 것이 맞는지.
     * (체스게임과 보드초기화클래스의 변화에 따라 컨트롤러도 영향을 받는다고 생각하여 의존성 주입으로 객체를 연결하였습니다)
     *
     * 2) 람다식, 체스게임과 같은 객체(불변객체가 아닌 객체)를 연결하여, 컨트롤러에는 특정 필드를 두지 않아도 되는지.
     * (ChessGame, BoardStragtey 객체의 사용은 람다식에서만 사용되므로, 컨트롤러에 필드로 따로 둘 필요가 없다고 생각했습니다)
     *
     * 리뷰어님의 의견이 궁금합니다!
     */

    public Controller(InputView inputView, OutputView outputView,
                      ChessGame chessGame, BoardStrategy boardStrategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.actionsByCommand = putActionsByCommand(chessGame, outputView, boardStrategy);
    }


    /**
     * todo : 질문2
     * 이번 주 백엔드 강의에서 함수형 프로그래밍에 대해서 공부하면서
     * 함수형 인터페이스를 사용하고 싶어서 Consumer형식의 인터페이스를 사용하여
     * 각 명령어(Command)에 해당하는 람다식을 EnumMap에 저장하였습니다.
     *
     * 하지만 start와 move 명령어에 해당하는 경우,
     * 람다식 내부에서 참조객체를 사용하는데 불변객체가 아니고, 객체 내부의 필드도 전부 final이 아닙니다.
     * 즉, 람다식이 항상 다른 결과를 반환하는데요.
     * 이러한 경우에도 함수형 인터페이스를 사용해도 되는지 궁금합니다.
     *
     * 아니면 원래대로 if 분기문에 따라 일반 메서드를 실행하는 것이 나을까요?
     */
    private Map<Command, Consumer<List<String>>> putActionsByCommand(ChessGame chessGame,
                                                                     OutputView outputView,
                                                                     BoardStrategy boardStrategy) {
        EnumMap<Command, Consumer<List<String>>> actions = new EnumMap<>(Command.class);
        actions.put(Command.START, findStartCommandConsumer(chessGame, outputView, boardStrategy));
        actions.put(Command.MOVE, findMoveCommandConsumer(chessGame, outputView));
        actions.put(Command.END, findEndCommandConsumer());
        return actions;
    }

    private Consumer<List<String>> findStartCommandConsumer(ChessGame chessGame,
                                                            OutputView outputView,
                                                            BoardStrategy boardStrategy) {
        return (commandLine) -> {
            chessGame.start(boardStrategy);
            outputView.printBoard(chessGame.getChessBoard());
        };
    }


    /**todo :질문3
     * 뷰로 부터 받은 문자열(예:a1, b2 등)을 Position객체로 바꾸는 역할을 컨트롤러에게 맡겼는데, (방법 1)
     * 뷰에서 넘어온 자료를 도메인 객체로 가공하는 일은 컨트롤러의 역할이라고 판단했기 때문입니다.
     *
     * 하지만, 한편으로는 도메인 객체에 해당하는 Position과, Column, Rank를 컨트롤러가 알게 하는 것이 맞나 하는 생각이 들었습니다. (방법 2)
     * 컨트롤러는 일종의 서비스(다른 도메인 객체를 모두 총괄)에 해당하는 ChessGame객체만 알고,
     * 나머지 객체들은 ChessGame을 통해서만 다루도록 하여,
     * ChessGame에서 문자열을 가지고, 비즈니스 로직에 필요한 Position 객체를 생성하는 것이 더 적합한 구조인가 싶습니다.
     * (즉, createPositionByCommand(String sourceCommand)를 ChessGame에 넘겨주는 방법이 맞을까요?
     *
     * 방법 1과 방법 2 중에 어떤 것이 더 mvc 패턴에 적합한 구조인지 리뷰어님의 의견 궁금합니다!
     */
    private Consumer<List<String>> findMoveCommandConsumer(ChessGame chessGame, OutputView outputView) {
        return commandLine -> {
            String startSource = commandLine.get(START_SOURCE_INDEX_IN_COMMANDLINE);
            String targetSource = commandLine.get(TARGET_SOURCE_INDEX_IN_COMMANDLINE);
            chessGame.move(createPositionByCommand(startSource), createPositionByCommand(targetSource));
            outputView.printBoard(chessGame.getChessBoard());
        };
    }

    private Position createPositionByCommand(String sourceCommand) {
        List<String> columnAndRank = List.of(sourceCommand.split(""));
        Column column = Column.findColumnByValue(columnAndRank.get(0));
        Rank rank = Rank.findRankByValue(columnAndRank.get(1));

        return Position.of(column, rank);
    }

    private Consumer<List<String>> findEndCommandConsumer() {
        return commandLine -> {
        };
    }

    public void playChessGame() {
        outputView.printStartGuideMessage();
        Command command = Command.START;
        while (command != Command.END) {
            command = playChessByCommand();
        }
    }

    private Command playChessByCommand() {
        try {
            List<String> commandLine = inputView.readCommand();
            Command command = Command.findCommandByString(commandLine.get(COMMAND_INDEX_IN_COMMANDLINE));
            actionsByCommand.get(command).accept(commandLine);
            return command;
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return playChessByCommand();
        }
    }
}
