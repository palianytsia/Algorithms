package com.algorithms.greedy;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Job scheduling showcase. Demonstrates different greedy algorithms for minimizing the weighted sum of job completion times.
 * 
 * @see <a href="https://class.coursera.org/algo2-2012-001">Algorithms: Design and Analysis, Part 2</a> by Tim Roughgarden for detailed algorithm description,
 *      analysis and pseudo code.
 * 
 * @author Ivan Palianytsia <a href="mailto:ivan.palianytsia@gmail.com">ivan.palianytsia@gmail.com</a>
 */
public class JobScheduler {

    public enum Approach implements Comparator<Job> {

	/**
	 * Approach that schedules jobs in decreasing order of the difference (weight - length), if two jobs have equal difference (weight - length), you
	 * schedules the job with higher weight first. This algorithm is not always optimal.
	 */
	DIFFERENCE {
	    @Override
	    public int compare(Job a, Job b) {
		int aDifference = a.getWeight() - a.getLength();
		int bDifference = b.getWeight() - b.getLength();
		if (aDifference != bDifference) {
		    return -1 * (aDifference - bDifference);
		}
		return -1 * (a.getWeight() - b.getWeight());
	    }
	},

	/**
	 * Schedules jobs (optimally) in decreasing order of the ratio (weight/length). Ties are broken arbitrary.
	 */
	RATIO {
	    @Override
	    public int compare(Job a, Job b) {
		float aRatio = (float) a.getWeight() / a.getLength();
		float bRatio = (float) b.getWeight() / b.getLength();
		return -1 * Float.compare(aRatio, bRatio);
	    }
	}
    }

    /**
     * Schedules the given set of jobs trying to achieve optimal schedule (the one that minimizes the weighted sum of completion times) using the suggested
     * greedy approach.
     * 
     * @param jobs
     *            - set of jobs to schedule.
     * 
     * @param approach
     *            - greedy approach to use to produce the schedule.
     * 
     * @return the optimal jobs schedule.
     */
    public static List<Job> schedule(Set<Job> jobs, Approach approach) {
	List<Job> schedule = new LinkedList<Job>(jobs);
	Collections.sort(schedule, approach);
	return schedule;
    }

    /**
     * Computes the weighted sum of completion times in the given schedule.
     * 
     * @return the weighted sum of completion times.
     */
    public static long sumCompletionTimes(List<Job> schedule) {
	long sum = 0;
	int completionTime = 0;
	for (Job job : schedule) {
	    completionTime += job.getLength();
	    sum += completionTime * job.getWeight();
	}
	return sum;
    }

}
