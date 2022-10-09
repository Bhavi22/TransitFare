import com.littlepay.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


import java.time.LocalDateTime;
public class TestTripCost {

    TestTapData data = new TestTapData();
    CompleteTrip ct;
    IncompleteTrip ict;
    CancelledTrip cnt;

    /* setupTap function sets the tap object to contain tap on and tap off details */
    public Tap setupTap(int Id,LocalDateTime DateUTC,String Stop,String pan, String TapType)
    {
        Tap tap=new Tap();
        tap.setId(Id);
        tap.setDateUtc(DateUTC);
        tap.setStopId(Stop);
        tap.setPan(pan);
        tap.setTapType(TapType);
        tap.setBusId(data.busId);
        tap.setCompanyId(data.companyId);
        return tap;
    }
    @Test
    public void testCompletedTripCostStop1ToStop2() {

        ct=new CompleteTrip(setupTap(1,data.startDateUTC,data.stopId[0],data.pan[0],data.tapType[0]),setupTap(2,data.finishedDateUTC,data.stopId[1],data.pan[0],data.tapType[1]));
        assertEquals(3.25, (ct.calculateCost(data.stopId[0],data.stopId[1])),0.00);
    }
    @Test
    public void testCompletedTripCostStop1ToStop3() {
        ct=new CompleteTrip(setupTap(1,data.startDateUTC,data.stopId[0],data.pan[0],data.tapType[0]),setupTap(2,data.finishedDateUTC,data.stopId[2],data.pan[0],data.tapType[1]));
        assertEquals(7.30, (ct.calculateCost(data.stopId[0],data.stopId[2])),0.00);
    }
    @Test
    public void testCompletedTripCostStop2ToStop3() {
        ct=new CompleteTrip(setupTap(1,data.startDateUTC,data.stopId[1],data.pan[0],data.tapType[0]),setupTap(2,data.finishedDateUTC,data.stopId[2],data.pan[0],data.tapType[1]));
        assertEquals(5.50, ct.calculateCost(data.stopId[1],data.stopId[2]),0.00);
    }
    @Test
    public void testCompletedTripCostStop3ToStop1() {
        ct=new CompleteTrip(setupTap(1,data.startDateUTC,data.stopId[2],data.pan[0],data.tapType[0]),setupTap(2,data.finishedDateUTC,data.stopId[0],data.pan[0],data.tapType[1]));
        assertEquals(7.30, (ct.calculateCost(data.stopId[2],data.stopId[0])),0.00);
    }
    @Test
    public void testIncompleteTripCostFromStop1()
    {
        ict=new IncompleteTrip(setupTap(1,data.startDateUTC,data.stopId[0],data.pan[0],data.tapType[0]));
        assertEquals(7.30, (ict.calculateCost(data.stopId[0],data.stopId[0])),0.00);
    }
    @Test
    public void testIncompleteTripCostFromStop2()
    {
        ict=new IncompleteTrip(setupTap(1,data.startDateUTC,data.stopId[1],data.pan[0],data.tapType[0]));
        assertEquals(5.50, (ict.calculateCost(data.stopId[1],data.stopId[1])),0.00);
    }
    @Test
    public void testCancelledTripCostFromStop1ToStop1()
    {
        cnt=new CancelledTrip(setupTap(1,data.startDateUTC,data.stopId[0],data.pan[0],data.tapType[0]),setupTap(2,data.finishedDateUTC,data.stopId[0],data.pan[0],data.tapType[1]));

        assertEquals(0.00, (cnt.calculateCost(data.stopId[0],data.stopId[0])),0.00);
    }
    @Test
    public void testCancelledTripCostFromStop2ToStop2()
    {
        cnt=new CancelledTrip(setupTap(1,data.startDateUTC,data.stopId[1],data.pan[0],data.tapType[0]),setupTap(2,data.finishedDateUTC,data.stopId[1],data.pan[0],data.tapType[1]));
        assertEquals(0.00, (cnt.calculateCost(data.stopId[1],data.stopId[1])),0.00);
    }
    @Test
    public void testCancelledTripCostFromStop3ToStop3()
    {
        cnt=new CancelledTrip(setupTap(1,data.startDateUTC,data.stopId[2],data.pan[0],data.tapType[0]),setupTap(2,data.finishedDateUTC,data.stopId[2],data.pan[0],data.tapType[1]));
        assertEquals(0.00, (cnt.calculateCost(data.stopId[2],data.stopId[2])),0.00);
    }
}
