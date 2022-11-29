/*
Project: Programming Assignment 1 for CSI 2110
Name: Chunhao Han
Student Number: 300226286
 */

public class Point3D implements MyComparable {
    double xCord;
    double yCord;
    double zCord;
    int label = -2;

    public Point3D(double x, double y, double z) {
        xCord=x;
        yCord=y;
        zCord=z;
    }

    /**
     *
     * @return X coordinate
     */
    public double getX() {
        return xCord;
    }

    /**
     *
     * @return Y coordinate
     */
    public double getY() {
        return yCord;
    }

    /**
     *
     * @return Z coordinate
     */
    public double getZ() {
        return zCord;
    }

    /**
     *
     * @return Label of this point
     */
    public int getLabel(){return label;}

    /**
     * a setter method for point's label
     * @param newLabel the new label that the point will change into
     */
    public void setLabel(int newLabel) {
        label = newLabel;
    }

    /**
     * a distance method that computes the Euclidean distance between two points
     * @param pt the second point that will be compared with this point
     * @return the Euclidean distance between two points
     */
    public double distance(Point3D pt){
        double dx = this.xCord - pt.getX();
        double dy = this.yCord - pt.getY();
        double dz = this.zCord - pt.getZ();
        return Math.sqrt((dx*dx)+(dy*dy)+(dz*dz));
    }

    @Override
    public String toString() {
        return "Point3D{ labels =" + label + ", x=" + xCord + ", y=" + yCord + ", z=" + zCord + '}';
    }

    /**
     * a comparable compare method that is overridden to fit Point3D
     * @param o the second point that will be compared with this point
     * @return 0 if equals; 1 if this point is larger, -1 if this point is smaller
     */
    @Override
    public double compareTo(Point3D o) {
        if(this==o){
            return 0;
        }
        else if(this.getLabel()!= o.getLabel()){
            if(this.getLabel() < o.getLabel()){
                return -1;
            }
            else return 1;
        }

        else if(this.getX()!=o.getX()){
            if(this.getX() < o.getX()){
                return -1;
            }
            else return 1;
        }

        else if(this.getY()!=o.getY()){
            if(this.getY() < o.getY()){
                return -1;
            }
            else return 1;
        }

        else if(this.getZ()!=o.getZ()){
            if(this.getZ() < o.getZ()){
                return -1;
            }
            else return 1;
        }
        else {return 0;}
    }
}
