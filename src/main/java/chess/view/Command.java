package chess.view;

import chess.domain.pieces.Position;
import chess.domain.board.File;
import chess.domain.board.Rank;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Command {

    private final static String START = "start";
    private final static String MOVE = "move";
    private final static String END = "end";

    private final List<String> command;

    public Command(String input) {
        List<String> inputString = Arrays.stream(input.split(" "))
                .map(String::trim)
                .collect(toList());

        validateInput(inputString);
        command = inputString;
    }

    public Position getCurrentPosition(){
        return changeStringToPosition(command.get(1));
    }

    public Position getTargetPosition(){
        return changeStringToPosition(command.get(2));
    }

    public boolean isStart(){
        return START.equals(command.get(0));
    }

    public boolean isMove(){
        return MOVE.equals(command.get(0));
    }

    public boolean isEnd(){
        return END.equals(command.get(0));
    }

    private void validateInput(List<String> inputString) {
        if(START.equals(inputString.get(0))){
            checkSizeOne(inputString);
            return;
        }
        if(MOVE.equals(inputString.get(0))){
            checkSizeThree(inputString);
            return;
        }
        if(END.equals(inputString.get(0))){
            checkSizeOne(inputString);
            return;
        }
        throw new IllegalArgumentException("찾을 수 없는 명령어 입니다.");
    }

    private void checkSizeOne(List<String> inputString){
        if(inputString.size()!=1){
            throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
        }
    }

    private void checkSizeThree(List<String> inputString){
        if(inputString.size()!=3){
            throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
        }
    }

    private Position changeStringToPosition(final String inputPosition) {
        String currentFile = String.valueOf(inputPosition.charAt(0));
        String currentRank = String.valueOf(inputPosition.charAt(1));
        return new Position(Rank.of(Integer.parseInt(currentRank)), File.ofByValue(currentFile));
    }
}
