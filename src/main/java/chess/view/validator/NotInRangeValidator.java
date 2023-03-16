package chess.view.validator;

import java.util.regex.Pattern;

public class NotInRangeValidator implements InputValidator{
    private static final Pattern FILE_RANGE = Pattern.compile("^[a-f]");
    private static final Pattern RANK_RANGE = Pattern.compile("^[1-8]");
    private static final String OUT_OF_RANGE_MASSAGE = "범위에서 벗어났습니다.";
    private InputValidator next;
    private void setNext(){
        this.next = new SuccessValidator();
    }
    @Override
    public void validate(InputRequest inputRequest) {
        if(inputRequest.notValidate(ValidateType.OUT_OF_RANGE)){
            setNext();
            next.validate(inputRequest);
            return;
        }
        validateFile(String.valueOf(inputRequest.getValue().get(1).charAt(0)));
        validateRank(String.valueOf(inputRequest.getValue().get(1).charAt(1)));
        validateFile(String.valueOf(inputRequest.getValue().get(2).charAt(0)));
        validateRank(String.valueOf(inputRequest.getValue().get(2).charAt(1)));
    }

    private void validateFile(String file){
        if(FILE_RANGE.matcher(file).matches()){
            throw new IllegalArgumentException(OUT_OF_RANGE_MASSAGE);
        }
    }
    private void validateRank(String rank){
        if(RANK_RANGE.matcher(rank).matches()){
            throw new IllegalArgumentException(OUT_OF_RANGE_MASSAGE);
        }
    }
}
