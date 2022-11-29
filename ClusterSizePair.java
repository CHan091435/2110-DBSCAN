/*
Project: Programming Assignment 1 for CSI 2110
Name: Chunhao Han
Student Number: 300226286
 */

public class ClusterSizePair implements Comparable{
    int clusterLabel;
    int size;

    /**
     * a constructor that accepts a label and the size of a cluster
     * @param c cluster label
     * @param s cluster size
     */
    public ClusterSizePair(int c,int s){
        clusterLabel = c;
        size = s;
    }

    /**
     *
     * @return cluster label
     */
    public int getClusterLabel() {
        return clusterLabel;
    }

    /**
     *
     * @return cluster size
     */
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return ("Cluster "+clusterLabel+" has "+size+" points ");
    }

    /**
     * a comparable compare method that is overridden to fit ClusterSizePair
     * @param p the second size pair that will be compared with this size pair
     * @return 0 if equals; 1 if this size is larger, -1 if this size is smaller
     */
    @Override
    public int compareTo(Object p) throws IllegalArgumentException {
        if (!(p instanceof ClusterSizePair)) {
            throw new IllegalArgumentException();
        }
        if(this.size > ((ClusterSizePair) p).getSize()){
            return 1;
        }
        else if(this.size < ((ClusterSizePair) p).getSize()){
            return -1;
        }
        else return 0;
    }
}
