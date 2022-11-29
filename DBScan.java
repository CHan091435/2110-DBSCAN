/*
Project: Programming Assignment 1 for CSI 2110
Name: Chunhao Han
Student Number: 300226286
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;

public class DBScan {

    List<Point3D> theList;
    double eps=0;
    double minPts=0;
    int c = 0; // cluster label
    int numberOfNoise;


    /**
     * A constructor that accepts a List of Point3D
     * @param pointList a list contains all the Point3D points
     */
    public DBScan(List<Point3D> pointList){
        theList = pointList;
    }

    /**
     * set a new eps value
     * @param newEps the value that eps will change into
     * @return the new eps value
     */
    public double setEps(double newEps){
        eps = newEps;
        return eps;
    }

    /**
     * set a new minPts value
     * @param newMinPts the value that minPts will change into
     * @return the new minPts value
     */
    public double setMinPts(double newMinPts){
        minPts = newMinPts;
        return minPts;
    }



    /**
     * the findClusters method that executes the DBScan algorithm
     * the points within the list will be labeled with their cluster label or -1, which is noise.
     */
    public void findClusters(){
        //special labels
        int undefined = -2;
        int noise = -1;

        //array list of labels that is filled with undefined labels
        for (Point3D p : theList){
            if(p.getLabel()!=undefined){continue;}

            NearestNeighbors neighborsFinder = new NearestNeighbors(theList);
            List<Point3D> neighbours = neighborsFinder.rangeQuery(eps,p);

            if(neighbours.size()<minPts){
                p.setLabel(noise);
                continue;
            }

            // cluster label increment
            c++;
            p.setLabel(c);

            // create a collection of neighbours
            Stack<Point3D> theStack = new Stack<Point3D>();
            for(Point3D nei : neighbours){
                theStack.push(nei);
            }

            // Label all the neighbours with c
            while (!(theStack.isEmpty())){
                Point3D q = theStack.pop();
                if(q.getLabel() == noise){ // let the "noise" become the boarder
                    q.setLabel(c);
                }
                if(q.getLabel()!=undefined){continue;} //skip if already checked
                q.setLabel(c);

                NearestNeighbors neiOfNeiFinder = new NearestNeighbors(theList);
                List<Point3D> neiOfNei = neiOfNeiFinder.rangeQuery(eps,q);
                if(neiOfNei.size()>=minPts){ // looking for new possible points
                    for(Point3D newNei :neiOfNei){
                        theStack.push(newNei);
                    }
                }
            }
        }
    }

    /**
     * find the total amount of the clusters
     * @return the total amount of the clusters
     */
    public int getNumberOfClusters(){
        return c;
    }

    /**
     * get all the points with in the list
     * @return the list
     */
    public List<Point3D> getPoints(){
        return theList;
    }

    /**
     * The read static method that accept a filename and returns a list of Point3D
     * @param filename the full filepath of the csv file of points
     * @return a list with all the points
     * @throws FileNotFoundException
     */
    public static List<Point3D> read(String filename) throws FileNotFoundException {
        List<Point3D> fileList= new ArrayList<Point3D>();
        Scanner scanner = new Scanner(new File(filename));
        String str = scanner.nextLine();    //skip the title line in the file.
        String[] numbers;
        while (scanner.hasNext()) { // stop when reaches the end
            str = scanner.nextLine();
            numbers = str.split(","); // getting coordinates from the file
            Point3D p;
            double x =  Double.parseDouble(numbers[0]);
            double y =  Double.parseDouble(numbers[1]);
            double z =  Double.parseDouble(numbers[2]);

            p = new Point3D(x,y,z);// create a point based on the coordinates
            fileList.add(p); // adding the p to the list
        }
        return fileList;
    }

    /**
     * The save method that saves all the points with their cluster label and associated grayscale color
     * @param filename the name of the new file that will be created
     * @throws IOException
     */
    public void save(String filename) throws IOException {
        File file = new File(filename);
        FileWriter output = new FileWriter(file);

        double clust = getNumberOfClusters();
        double grayScale;

        //Title line
        output.append("x,y,z,C,R,G,B");
        output.append("\n");

        for(Point3D p :theList){
            int label = p.getLabel();
            if(label == -1){
                numberOfNoise++;
                continue;
            }
            double x = p.getX();
            double y = p.getY();
            double z = p.getZ();
            output.append(Double.toString(x));
            output.append(',');
            output.append(Double.toString(y));
            output.append(',');
            output.append(Double.toString(z));
            output.append(',');
            output.append(Integer.toString(label));
            output.append(',');

            grayScale = label/clust;//color
            output.append(Double.toString(grayScale));
            output.append(',');
            output.append(Double.toString(grayScale));
            output.append(',');
            output.append(Double.toString(grayScale));
            output.append("\n");
        }

    }

    /**
     *  sort the list based on label
     */
    public void sort(){

        List<Point3D> tmpList = new ArrayList<Point3D>();

        for(Point3D point : theList){
            Iterator<Point3D> cursor = tmpList.iterator();
            if(tmpList.isEmpty()){
                tmpList.add(point);
            }
            else{
                int index = -1; // note the location where we wish to insert the value
                boolean flag = true; // raise if the new point goes to the end of the sorted list
                while (cursor.hasNext()){
                    index++;
                    if(point.compareTo(cursor.next())<0){
                        tmpList.add(index,point);
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    tmpList.add(point);
                }

            }

        }

        theList = tmpList;
    }

    /**
     * the list must be sorted by labels when calling getSizes()
     * this method finds the size of each cluster and sort the size from largest to smallest
     * @return a sorted list of the sizes and their associate label
     */
    public List<ClusterSizePair>  getSizes(){
        List<ClusterSizePair> sizeList = new ArrayList<ClusterSizePair>();// list contains the size

        Iterator<Point3D> it = theList.iterator();
        Point3D p;
        int count = 0;
        int curLabel = -5;//dummy label
        while (it.hasNext()){
            p = it.next();
            if(p.getLabel()==-1){
                continue;
            }
            if(curLabel!=-5 && p.getLabel()!= curLabel){
                sizeList.add(new ClusterSizePair(curLabel,count));
                count = 0;
            }
            curLabel = p.getLabel();
            count++;
        }


        // SizeList sort
        List<ClusterSizePair> tmpList = new ArrayList<ClusterSizePair>();

        for(ClusterSizePair pair : sizeList) {
            Iterator<ClusterSizePair> cursor = tmpList.iterator();
            if (tmpList.isEmpty()) {
                tmpList.add(pair);
            } else {
                int index = -1; // note the location where we wish to insert the value
                boolean flag = true; // raise if the new point goes to the end of the sorted list
                while (cursor.hasNext()) {
                    index++;
                    if (pair.compareTo(cursor.next()) > 0) {
                        tmpList.add(index, pair);
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    tmpList.add(pair);
                }
            }
        }

        sizeList = tmpList;

        return sizeList;
    }

    public static void main(String[] args) throws IOException {


        // command reading part
        String filepath = args[0];
        List<Point3D> l = read(filepath);
        DBScan pointCloud = new DBScan(l);
        double e = Double.parseDouble(args[1]);
        double mp = Double.parseDouble(args[2]);
        String filename = filepath.replace(".csv","");

        //proceeding part
        pointCloud.setEps(e);
        pointCloud.setMinPts(mp);
        pointCloud.findClusters();
        pointCloud.sort();
        String newFilePath = (filename + "_cluster_" + e + "_" + mp + "_" + pointCloud.getNumberOfClusters() + ".csv");
        pointCloud.save(newFilePath);
        System.out.println(pointCloud.getSizes()+" with "+pointCloud.numberOfNoise+" noises");
    }
}
