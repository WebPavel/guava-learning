package com.ava;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;
import com.google.common.primitives.Ints;
import org.junit.Test;

public class RangeTest {

    @Test
    public void testRange() {
        System.out.println(Range.open(1,10));
        System.out.println(Range.closed(1,10));
        System.out.println(Range.closedOpen(1,10));
        System.out.println(Range.openClosed(1,10));
        System.out.println(Range.greaterThan(10));
        System.out.println(Range.atLeast(10));
        System.out.println(Range.lessThan(10));
        System.out.println(Range.atMost(10));
        System.out.println(Range.all());
        System.out.println(Range.closed(10,10));
        System.out.println(Range.closedOpen(10,10));
        System.out.println(Range.open(10,10));  //抛出异常
    }

    @Test
    public void testRangeMethod() {
        System.out.println(Range.downTo(10, BoundType.OPEN));
        System.out.println(Range.upTo(10,BoundType.CLOSED));
        System.out.println(Range.range(1,BoundType.CLOSED,10,BoundType.OPEN));
        System.out.println("=========================================================");
        System.out.println(Range.closed(1,3).contains(2));
        System.out.println(Range.closed(1,3).contains(4));
        System.out.println(Range.lessThan(3).contains(3));
        System.out.println(Range.closed(1,3).containsAll(Ints.asList(1,2)));
        System.out.println("=========================================================");
        System.out.println(Range.closedOpen(4,4).hasLowerBound());
        System.out.println(Range.closedOpen(4,4).hasUpperBound());
        System.out.println(Range.closedOpen(4,4).isEmpty());
        System.out.println(Range.openClosed(4,4).isEmpty());
        System.out.println(Range.closed(4,4).isEmpty());
        System.out.println(Range.closed(1,10).lowerEndpoint());
        System.out.println(Range.closed(1,10).upperEndpoint());
        System.out.println(Range.open(1,10).lowerEndpoint());
        System.out.println(Range.open(1,10).upperEndpoint());
        System.out.println(Range.closed(1,10).lowerBoundType());
        System.out.println(Range.open(1,10).upperBoundType());
        System.out.println("=========================================================");
        Range<Integer> rangeBase = Range.open(1,5);
        Range<Integer> rangeClose = Range.closed(2,3);
        Range<Integer> rangeCloseOpen = Range.closedOpen(4,5);
        Range<Integer> rangeCloseBound = Range.closedOpen(2,7);
        System.out.println(rangeBase.encloses(rangeClose));
        System.out.println(rangeBase.encloses(rangeCloseOpen));
        System.out.println(rangeBase.encloses(rangeCloseBound));
        System.out.println("=========================================================");
        System.out.println(Range.closed(1,5).isConnected(Range.open(5,10)));
        System.out.println(Range.closed(1,10).isConnected(Range.closed(2,8)));
        System.out.println(Range.closed(1,5).isConnected(Range.closed(3,8)));
        System.out.println(Range.open(1,5).isConnected(Range.open(5,10)));
        System.out.println(Range.closed(1,5).isConnected(Range.closed(6,10)));
        System.out.println("=========================================================");
        System.out.println(Range.closed(1,5).intersection(Range.open(5,10)));
        System.out.println(Range.closed(1,10).intersection(Range.closed(4,7)));
        System.out.println(Range.closed(1,5).intersection(Range.closed(3,8)));
//        System.out.println(Range.open(1,5).intersection(Range.open(5,10)));  // 抛出异常
//        System.out.println(Range.closed(1,5).intersection(Range.closed(6,10)));  //抛出异常
        System.out.println("=========================================================");
        System.out.println(Range.closed(1,5).span(Range.open(5,10)));
        System.out.println(Range.closed(1,10).span(Range.closed(3,7)));
        System.out.println(Range.closed(1,5).span(Range.closed(3,8)));
        System.out.println(Range.open(1,5).span(Range.open(5,10)));
        System.out.println(Range.closed(1,5).span(Range.closed(6,10)));
        System.out.println(Range.closed(1,5).span(Range.closed(10,15)));
    }
}
