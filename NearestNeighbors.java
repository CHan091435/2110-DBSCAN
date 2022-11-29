/*
Project: Programming Assignment 1 for CSI 2110
Name: Chunhao Han
Student Number: 300226286
 */

import java.util.ArrayList;
import java.util.List;

public class NearestNeighbors {

    List<Point3D> theList;

    /**
     * a constructor that accepts a List of Point3D
     * @param pointList
     */
    public NearestNeighbors(List<Point3D> pointList){
        theList = pointList;
    }

    /**
     * a rangeQuery method that finds the nearest neighbors of a 3D point
     * @param eps A distance measure that is used to identify the points in the neighborhood of any point
     * @param P the 3D point that needs to find its neighbours
     * @return a list with P's neighbour
     */
    public List<Point3D> rangeQuery(double eps, Point3D P){
        List<Point3D> N = new ArrayList<Point3D>(); // An empty list to store points within the range
        for(Point3D thePoint : theList){
            if((P.distance(thePoint))<=eps){
                N.add(thePoint);
            }
        }
        return N;
    }
}
