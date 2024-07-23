package com.jpmc.test.fix;

import org.junit.Test;

import static com.jpmc.test.fix.parser.FixParser.parseFixMessage;

public class MainTest {

    @Test
    public void test() {
        {

            StringBuilder stringBuilder = new StringBuilder();

            byte[] singleOrderMsg = stringBuilder.append("8=FIX.4.4|9=122|35=D|34=215|49=").append("client").append(System.currentTimeMillis()).append("|52=20100225-19:41:57.316|56=B|1=").append("acc").append(Math.random())
                    .append("|11=13346|21=1|40=2|44=5|54=1|59=0|60=20100225-19:39:52.020|10=072|").toString().getBytes();
            byte[] sequenceResetMsg = "8=FIX.4.4|9=70|35=4|49=A|56=XYZ|34=129|52=20100302-19:38:21|43=Y|57=LOL|123=Y|36=175|10=192|".getBytes();
            byte[] MDIncRefreshMsg = "8=FIX.4.4|9=196|35=X|49=A|56=B|34=12|52=20100318-03:21:11.364|262=A|268=2|279=0|269=0|278=BID|55=EUR/USD|270=1.37215|15=EUR|271=2500000|346=1|279=0|269=1|278=OFFER|55=EUR/USD|270=1.37224|15=EUR|271=2503200|346=1|10=171|".getBytes();
            byte[] heartbeatMsg = "8=FIX.4.4|9=42|35=0|49=A|56=B|34=12|52=20100304-07:59:30|10=185|".getBytes();
            parseFixMessage(MDIncRefreshMsg);
            parseFixMessage(sequenceResetMsg);
            parseFixMessage(heartbeatMsg);
            parseFixMessage(singleOrderMsg);
        }
    }
}
