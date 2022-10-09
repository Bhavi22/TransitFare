import java.time.LocalDateTime;

public class TestTapData {

    protected LocalDateTime startDateUTC;
    protected LocalDateTime finishedDateUTC;
    protected String[] stopId;
    protected String busId;
    protected String companyId;
    protected String[] pan;
    protected String[] tapType;

    public TestTapData(){
        startDateUTC=LocalDateTime.of(2022,8,12,9,00);
        finishedDateUTC=LocalDateTime.of(2022,8,12,9,30);
        this.pan=new String[]{"5500005555555559","122000000000003","34343434343434","4484070000000000"};
        this.stopId=new String[]{"Stop1","Stop2","Stop3"};
        this.busId="Bus99";
        this.tapType=new String[]{"ON","OFF"};
        this.companyId="Company99";
    }
}
