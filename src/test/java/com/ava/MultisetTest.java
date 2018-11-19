package com.ava;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultisetTest {

    @Test
    public void testWordCount() {
        String strWord = "wer|dffd|ddsa|dfd|dreg|de|dr|ce|ghrt|cf|gt|ser|tg|ghrt|cf|gt|" +
                "ser|tg|gt|kldf|dfg|vcd|fg|gt|ls|lser|dfr|wer|dffd|ddsa|dfd|dreg|de|dr|" +
                "ce|ghrt|cf|gt|ser|tg|gt|kldf|dfg|vcd|fg|gt|ls|lser|dfr";
        String[] words = strWord.split("\\|");
        Map<String,Integer> countMap = new HashMap<>();
        for (String word : words) {
            Integer count = countMap.get(word);
            if (count == null) {
                countMap.put(word, 1);
            } else {
                countMap.put(word, count+1);
            }
        }
        System.out.println("countMap:");
        for (String word : countMap.keySet()) {
            System.out.println(word+":"+countMap.get(word));
        }
    }

    @Test
    public void testMultiset() {
        String strWords = "wer|dffd|ddsa|dfd|dreg|de|dr|ce|ghrt|cf|gt|ser|tg|ghrt|cf|gt|" +
                "ser|tg|gt|kldf|dfg|vcd|fg|gt|ls|lser|dfr|wer|dffd|ddsa|dfd|dreg|de|dr|" +
                "ce|ghrt|cf|gt|ser|tg|gt|kldf|dfg|vcd|fg|gt|ls|lser|dfr";
        String[] words = strWords.split("\\|");
        List<String> wordList = new ArrayList<>();
        for (String word : words) {
            wordList.add(word);
        }
        Multiset<String> wordMultiset = HashMultiset.create();
        wordMultiset.addAll(wordList);
        for (String word: wordMultiset.elementSet()) {
            System.out.println(word+":"+wordMultiset.count(word));
        }
    }
    /**
     * 跟踪每种对象的数量，进行数字统计
     */

    @Test
    public void testMultisetMethod() {
        String strWords = "wer|dfd|dd|dfd|dda|de|dr";
        String[] words = strWords.split("\\|");
        List<String> wordList = new ArrayList<>();
        for (String word: words) {
            wordList.add(word);
        }
        Multiset<String> wordMultiset = HashMultiset.create();
        wordMultiset.addAll(wordList);

        if (!wordMultiset.contains("pig")) {
            wordMultiset.add("pig", 7);
        }
        for (String word: wordMultiset.elementSet()) {
            System.out.println(word + ":" + wordMultiset.count(word));
        }
        System.out.println("==============================");
        if (wordMultiset.contains("pig")) {
            wordMultiset.setCount("pig", 77);
        }
        for (String word: wordMultiset.elementSet()) {
            System.out.println(word + ":" + wordMultiset.count(word));
        }
        System.out.println("==============================");
        if (wordMultiset.contains("pig")) {
            wordMultiset.setCount("pig", 77, 1);
        }
        for (String word: wordMultiset.elementSet()) {
            System.out.println(word + ":" + wordMultiset.count(word));
        }
        System.out.println("==============================");
        if (wordMultiset.contains("pig")) {
            wordMultiset.setCount("pig", 2, 3);
        }
        for (String word: wordMultiset.elementSet()) {
            System.out.println(word + ":" + wordMultiset.count(word));
        }
        System.out.println(wordMultiset.size());
    }
    /**
     * setCount(E e,int oldCount,int newCount)方法，如果传入oldCount和e的不一致，则不能将element的count设置为newCount
     */
}
