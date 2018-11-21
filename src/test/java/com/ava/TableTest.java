package com.ava;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TableTest {

    @Test
    public void testTable() {
        Table<Character,Integer,String> table = HashBasedTable.create();
        for (char row = 'A'; row < 'D'; row++) {
            for (int col = 0; col < 3; col++) {
                table.put(row, col, String.format("%c%d",row,col));
            }
        }
        System.out.println(table.column(2));
        System.out.println(table.row('B'));
        System.out.println(table.get('A',1));

        System.out.println(table.contains('C',2));
        System.out.println(table.containsColumn(3));
        System.out.println(table.containsRow('D'));
        System.out.println(table.columnMap());
        System.out.println(table.rowMap());
        System.out.println(table.remove('A',2));
        System.out.println(table.columnKeySet());
        System.out.println(table.rowKeySet());
        System.out.println("===========================================================");
        Set<Table.Cell<Character, Integer, String>> setMark = table.cellSet();
        Iterator<Table.Cell<Character, Integer, String>> iterator = setMark.iterator();
        while (iterator.hasNext()) {
            Table.Cell cell = iterator.next();
            System.out.println(cell.getValue());
        }
    }

    @Test
    public void testClassToInstanceMap() {
        ClassToInstanceMap<Number> classToInstanceMapNumber = MutableClassToInstanceMap.create();
        classToInstanceMapNumber.putInstance(Integer.class, Integer.valueOf(0));
        System.out.println(classToInstanceMapNumber.getInstance(Integer.class));

        ClassToInstanceMap<String> classToInstanceMapString = MutableClassToInstanceMap.create();
        classToInstanceMapString.putInstance(String.class,"fragrant");
        classToInstanceMapString.put(String.class, "someday");
        System.out.println(classToInstanceMapString.getInstance(String.class));

        ClassToInstanceMap<Person> classToInstanceMap = MutableClassToInstanceMap.create();
        Person person = new Person("galaxy",19);
        System.out.println(person.getName()+":"+person.getAge());
        classToInstanceMap.putInstance(Person.class, person);
        Person person1 = classToInstanceMap.getInstance(Person.class);
        System.out.println(person1.getName()+":"+person1.getAge());
    }

    @Test
    public void testRangeSet() {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1,10));
        System.out.println(rangeSet);
        rangeSet.add(Range.closedOpen(11,15));
        System.out.println(rangeSet);
        rangeSet.add(Range.open(15,20));
        System.out.println(rangeSet);
        rangeSet.add(Range.openClosed(20, 30));
        System.out.println(rangeSet);
        rangeSet.add(Range.open(5,10));
        System.out.println(rangeSet);
        rangeSet.add(Range.closedOpen(0, 0));
        System.out.println(rangeSet);
        System.out.println("==========================================================");
        System.out.println(rangeSet.contains(11));
        System.out.println(rangeSet.rangeContaining(17));
        Range<Integer> queryRange = Range.closed(2,7);
        System.out.println(rangeSet.encloses(queryRange));
    }

    @Test
    public void testRangeMap() {
        RangeMap<Integer,String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1,10),"foo");
        System.out.println(rangeMap);
        rangeMap.put(Range.open(3,6), "bar");
        System.out.println(rangeMap);
        rangeMap.put(Range.open(10,20),"foo");
        System.out.println(rangeMap);
        rangeMap.remove(Range.closed(5,11));
        System.out.println(rangeMap);
        System.out.println("======================================================");
        Map<Range<Integer>, String> map = rangeMap.asMapOfRanges();
        for (Map.Entry<Range<Integer>, String> entry : map.entrySet()) {
            System.out.println(entry.getKey()+":::"+entry.getValue());
        }
        RangeMap subRangeMap = rangeMap.subRangeMap(Range.open(3, 5));
        System.out.println(subRangeMap);
    }
}
