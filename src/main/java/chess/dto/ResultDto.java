package chess.dto;

public class ResultDto {
    private String name;
    private String team;
    private int count;

    public ResultDto() {
    }

    public ResultDto(final String name, final String team, final int count) {
        this.name = name;
        this.team = team;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(final String team) {
        this.team = team;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }
}
