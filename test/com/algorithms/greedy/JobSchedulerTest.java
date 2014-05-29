package com.algorithms.greedy;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JobSchedulerTest {

    private final String dataFileLocation = "test\\data\\greedy\\jobs.txt";
    private final Set<Job> jobs = new HashSet<Job>();

    @Before
    public void setUp() throws Exception {
        File dataFile = new File(dataFileLocation);
        Scanner scanner = new Scanner(dataFile);
        int numJobs = scanner.nextInt();
        for (int i = 0; i < numJobs; i++) {
            int weight = scanner.nextInt();
            int length = scanner.nextInt();
            jobs.add(new Job(length, weight));
        }
        scanner.close();
        Assert.assertEquals(numJobs, jobs.size());
    }

    @Test
    public void testDiffereneApproach() {
        testSchedule(JobScheduler.Approach.DIFFERENCE, 69119377652L);
    }

    @Test
    public void testRatioApproach() {
        testSchedule(JobScheduler.Approach.RATIO, 67311454237L);
    }

    private void testSchedule(JobScheduler.Approach approach, long expectedSum) {
        List<Job> schedule = JobScheduler.schedule(jobs, approach);
        Assert.assertEquals(jobs.size(), schedule.size());
        long actualSum = JobScheduler.sumCompletionTimes(schedule);
        Assert.assertEquals(expectedSum, actualSum);
    }

}
