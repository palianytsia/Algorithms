package com.algorithms.greedy;

public class Job {

    private final int length;

    private final int weight;

    public Job(int length, int weight) {
        if (length <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Length and weight should be positive integeres: " + length + ", "
                    + weight + " given.");
        }
        this.length = length;
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Job [length=" + length + ", weight=" + weight + "]";
    }

}
