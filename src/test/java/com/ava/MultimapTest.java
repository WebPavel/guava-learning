package com.ava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.*;

public class MultimapTest {
    Map<String,List<StudentScore>> studentScoreMap = new HashMap<>();

    @Test
    public void testStudentScore() {
        for (int i = 0; i < 20; i++) {
            StudentScore studentScore = new StudentScore();
            studentScore.setCourseId(1001+i);
            studentScore.setScore(100-i);
            addStudentScore("apple", studentScore);
        }
        System.out.println(studentScoreMap.size());
        System.out.println(studentScoreMap.containsKey("apple"));
        System.out.println(studentScoreMap.containsKey("jetty"));
        System.out.println(studentScoreMap.get("apple").size());

        List<StudentScore> scoreList = studentScoreMap.get("apple");
        if (scoreList != null && scoreList.size() > 0) {
            for (StudentScore ss :scoreList ) {
                System.out.println(ss.getCourseId()+" score: "+ss.getScore());
            }
        }
    }

    @Test
    public void testStuScoreMultimap() {
        Multimap<String,StudentScore> scoreMultimap = ArrayListMultimap.create();
        for (int i = 0; i < 20; i++) {
            StudentScore studentScore = new StudentScore();
            studentScore.setCourseId(1001+i);
            studentScore.setScore(100-i);
            scoreMultimap.put("apple",studentScore);
        }
        System.out.println(scoreMultimap.size());
        System.out.println(scoreMultimap.keys());
        System.out.println("===============================================");
        Collection<StudentScore> studentScore = scoreMultimap.get("apple");
        studentScore.clear();
        StudentScore studentScoreNew = new StudentScore();
        studentScoreNew.setCourseId(1050);
        studentScoreNew.setScore(60);
        studentScore.add(studentScoreNew);
        System.out.println(scoreMultimap.size());
        System.out.println(scoreMultimap.keys());
        System.out.println("===============================================");
        StudentScore studentScoreII = new StudentScore();
        studentScoreII.setCourseId(10086);
        studentScoreII.setScore(88);
        scoreMultimap.put("jetty",studentScoreII);
        System.out.println(scoreMultimap.size());
        System.out.println(scoreMultimap.keys());
        System.out.println("===============================================");
        for (StudentScore ss : scoreMultimap.values()) {
            System.out.println(ss.getCourseId()+"-"+ss.getScore());
        }
        scoreMultimap.remove("jetty",studentScoreII);
        System.out.println(scoreMultimap.size());
        System.out.println(scoreMultimap.get("jetty"));
        System.out.println("===============================================");
        scoreMultimap.put("hard",studentScoreII);
        scoreMultimap.removeAll("hard");
        System.out.println(scoreMultimap.size());
        System.out.println(scoreMultimap.get("hard"));
    }

    private void addStudentScore(final String stuName, final StudentScore studentScore) {
        List<StudentScore> studentScoreList = studentScoreMap.get(stuName);
        if (studentScoreList == null) {
            studentScoreList = new ArrayList<>();
            studentScoreMap.put(stuName, studentScoreList);
        }
        studentScoreList.add(studentScore);
    }
}

class StudentScore {
    private int courseId;
    private int score;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
