/*
Project: Programming Assignment 1 for CSI 2110
Name: Chunhao Han
Student Number: 300226286
 */

public interface MyComparable {

    /**
     * This method compares a point with another point o, the labels first, and then x, y, and z.
     * @return 0 if equals, -1 if the point
     */
    public double compareTo(Point3D o);

}