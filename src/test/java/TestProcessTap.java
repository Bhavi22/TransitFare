import com.littlepay.ProcessTap;
import com.littlepay.Tap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestProcessTap {
    static int k = 0;
    List<Tap> tapList = new ArrayList<Tap>();
    TestTapData data = new TestTapData();
    List<Tap> tapOn = new ArrayList<Tap>();
    List<Tap> tapOff = new ArrayList<Tap>();

/* Data setup to add completed taps to the tapList */
    public void setupCompletedTaps() {
        //Create 5 Completed Trips
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                Tap tap = new Tap();
                tap.setId(k++);
                tap.setTapType(data.tapType[j]);
                tap.setPan(data.pan[i % 4]);
                tap.setStopId(data.stopId[(i + j) % 3]);
                tap.setBusId(data.busId);
                tap.setCompanyId(data.companyId);
                if (j == 0) {
                    tap.setDateUtc(data.startDateUTC);
                    tapOn.add(tap);
                } else {
                    tap.setDateUtc(data.finishedDateUTC);
                    tapOff.add(tap);
                }
                tapList.add(tap);
            }
        }
    }

    /* Data setup to add cancelled taps to the tapList */
    public void setupCancelledTaps() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                Tap tap = new Tap();
                tap.setId(k++);
                tap.setTapType(data.tapType[j]);
                tap.setStopId(data.stopId[i % 3]);
                tap.setPan(data.pan[i % 4]);
                tap.setBusId(data.busId);
                tap.setCompanyId(data.companyId);
                if (j == 0) {
                    tap.setDateUtc(data.startDateUTC);
                    tapOn.add(tap);
                } else {
                    tap.setDateUtc(data.finishedDateUTC);
                    tapOff.add(tap);
                }
                tapList.add(tap);
            }
        }
    }

    /* Data setup to add incomplete taps to the tapList */
    public void setupIncompleteTaps() {
        for (int i = 0; i < 4; i++) {
            Tap tap = new Tap();
            tap.setId(k++);
            tap.setPan(data.pan[i]);
            tap.setTapType(data.tapType[0]);
            tap.setStopId(data.stopId[i % 3]);
            tap.setBusId(data.busId);
            tap.setCompanyId(data.companyId);
            tap.setDateUtc(data.startDateUTC);
            tapOn.add(tap);
            tapList.add(tap);
        }
    }
    /* Test function to validate TapOns processed by processTapType() function
     * match the TapOns from the tapList
      */
    @Test
    public void testProcessTapTypeTapOn() {
        setupCompletedTaps();
        setupCancelledTaps();
        setupIncompleteTaps();
        ProcessTap processTap = new ProcessTap(tapList);
        processTap.processTapType();
        Iterator<Tap> tapOnItr = tapOn.iterator();
        Iterator<Tap> processTapItr = processTap.getTapOnList().iterator();
        while (tapOnItr.hasNext() && processTapItr.hasNext()) {
            assertEquals(tapOnItr.next().getId(), processTapItr.next().getId());
        }
    }
    /* Test function to validate TapOffs processed by processTapType() function
     * match the TapOffs from the tapList
     */

    @Test
    public void testProcessTapTypeTapOff() {
        setupCompletedTaps();
        setupCancelledTaps();
        setupIncompleteTaps();
        ProcessTap processTap = new ProcessTap(tapList);
        processTap.processTapType();
        Iterator<Tap> tapOffItr = tapOff.iterator();
        Iterator<Tap> processTapItr = processTap.getTapOffList().iterator();
        while (tapOffItr.hasNext() && processTapItr.hasNext()) {
            assertEquals(tapOffItr.next().getId(), processTapItr.next().getId());
        }
    }

}