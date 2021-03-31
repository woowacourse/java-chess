package chess.domain.dto;

public class MovementDto {
    private String source;
    private String target;

    public MovementDto(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public void setSource(String source){
        this.source = source;
    }

    public void setTarget(String target){
        this.target = target;
    }


    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

}
