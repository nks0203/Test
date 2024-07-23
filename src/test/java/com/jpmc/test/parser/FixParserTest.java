package com.jpmc.test.parser;

import static com.jpmc.test.fix.parser.FixParser.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class FixParserTest {

    @Test
    public void testParseFixMessage_UnsupportedVersion() {
        // Given
        byte[] fixMessageBytes = "8=FIX.4.2|9=42|35=0|49=A|56=B|34=12|52=20100304-07:59:30|10=185|".getBytes();

        // When
        StringBuilder actualMsg = parseFixMessage(fixMessageBytes);

        // Then
        String expectedMsg = "FIX version not supported . Please enter a FIX message of version 4.4\n";
        assertEquals(expectedMsg, actualMsg.toString().replace("\r", ""));
    }


    @Test
    public void testParseFixMessage_NewOrderSingle() {
        // Given
        byte[] fixMessageBytes = "8=FIX.4.4|9=122|35=D|34=215|49=CLIENT12|52=20100225-19:41:57.316|56=B|1=Marcel|11=13346|21=1|40=2|44=5|54=1|59=0|60=20100225-19:39:52.020|10=072|".getBytes();

        // When
        StringBuilder actualMsg = parseFixMessage(fixMessageBytes);

        // Then
        String expectedMsg = "8\t\tBeginString : FIX.4.4\n" +
                "9\t\tBodyLength : 122\n" +
                "35\t\tMsgType : D\n" +
                "34\t\tMsgSeqNum : 215\n" +
                "49\t\tSenderCompID : CLIENT12\n" +
                "52\t\tSendingTime : 20100225-19:41:57.316\n" +
                "56\t\tTargetCompID : B\n" +
                "1\t\tAccount : Marcel\n" +
                "11\t\tClOrdID : 13346\n" +
                "21\t\tHandlInst : 1\n" +
                "40\t\tOrdType : 2\n" +
                "44\t\tPrice : 5\n" +
                "54\t\tSide : 1\n" +
                "59\t\tTimeInForce : 0\n" +
                "60\t\tTransactTime : 20100225-19:39:52.020\n" +
                "10\t\tCheckSum : 072\n";
        assertEquals(expectedMsg, actualMsg.toString().replace("\r", ""));
    }

    @Test
    public void testParseFixMessage_Heartbeat() {
        // Given
        byte[] fixMessageBytes = "8=FIX.4.4|9=42|35=0|49=A|56=B|34=12|52=20100304-07:59:30|10=185|".getBytes();

        // When
        StringBuilder actualMsg = parseFixMessage(fixMessageBytes);

        // Then
        String expectedMsg = "8\t\tBeginString : FIX.4.4\n" +
                "9\t\tBodyLength : 42\n" +
                "35\t\tMsgType : 0\n" +
                "49\t\tSenderCompID : A\n" +
                "56\t\tTargetCompID : B\n" +
                "34\t\tMsgSeqNum : 12\n" +
                "52\t\tSendingTime : 20100304-07:59:30\n" +
                "10\t\tCheckSum : 185\n";
        assertEquals(expectedMsg, actualMsg.toString().replace("\r", ""));
    }

    @Test
    public void testParseFixMessage_SequenceReset() {
        // Given
        byte[] fixMessageBytes = "8=FIX.4.4|9=70|35=4|49=A|56=XYZ|34=129|52=20100302-19:38:21|43=Y|57=LOL|123=Y|36=175|10=192|".getBytes();

        // When
        StringBuilder actualMsg = parseFixMessage(fixMessageBytes);

        // Then
        String expectedMsg = "8\t\tBeginString : FIX.4.4\n" +
                "9\t\tBodyLength : 70\n" +
                "35\t\tMsgType : 4\n" +
                "49\t\tSenderCompID : A\n" +
                "56\t\tTargetCompID : XYZ\n" +
                "34\t\tMsgSeqNum : 129\n" +
                "52\t\tSendingTime : 20100302-19:38:21\n" +
                "43\t\tPossDupFlag : Y\n" +
                "57\t\tTargetSubID : LOL\n" +
                "123\t\tGapFillFlag : Y\n" +
                "36\t\tNewSeqNo : 175\n" +
                "10\t\tCheckSum : 192\n";
        assertEquals(expectedMsg, actualMsg.toString().replace("\r", ""));
    }

    @Test
    public void testParseFixMessage_MarketDataIncrementalRefresh() {
        // Given
        byte[] fixMessageBytes = "8=FIX.4.4|9=196|35=X|49=A|56=B|34=12|52=20100318-03:21:11.364|262=A|268=2|279=0|269=0|278=BID|55=EUR/USD|270=1.37215|15=EUR|271=2500000|346=1|279=0|269=1|278=OFFER|55=EUR/USD|270=1.37224|15=EUR|271=2503200|346=1|10=171|".getBytes();

        // When
        StringBuilder actualMsg = parseFixMessage(fixMessageBytes);

        // Then
        String expectedMsg = "8\t\tBeginString : FIX.4.4\n" +
                "9\t\tBodyLength : 196\n" +
                "35\t\tMsgType : X\n" +
                "49\t\tSenderCompID : A\n" +
                "56\t\tTargetCompID : B\n" +
                "34\t\tMsgSeqNum : 12\n" +
                "52\t\tSendingTime : 20100318-03:21:11.364\n" +
                "262\t\tMDReqID : A\n" +
                "268\t\tNoMDEntries : 2\n" +
                "279\t\tMDUpdateAction : 0\n" +
                "269\t\tMDEntryType : 0\n" +
                "278\t\tMDEntryID : BID\n" +
                "55\t\tSymbol : EUR/USD\n" +
                "270\t\tMDEntryPx : 1.37215\n" +
                "15\t\tCurrency : EUR\n" +
                "271\t\tMDEntrySize : 2500000\n" +
                "346\t\tNumberOfOrders : 1\n" +
                "279\t\tMDUpdateAction : 0\n" +
                "269\t\tMDEntryType : 1\n" +
                "278\t\tMDEntryID : OFFER\n" +
                "55\t\tSymbol : EUR/USD\n" +
                "270\t\tMDEntryPx : 1.37224\n" +
                "15\t\tCurrency : EUR\n" +
                "271\t\tMDEntrySize : 2503200\n" +
                "346\t\tNumberOfOrders : 1\n" +
                "10\t\tCheckSum : 171\n";
        assertEquals(expectedMsg, actualMsg.toString().replace("\r", ""));
    }

}
