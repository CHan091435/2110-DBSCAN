# CHan

This is a project was a personal assignment second year CS course of UOttawa, CSI 2110.
The desicription of this assignment is as following:

In this programming assignment, I implement a data clustering algorithm named DBSCAN (with pseudocode provided)-

We were ask to use the DBSCAN algorithm in order to cluster the points of a LiDAR frame. At the end of
the process, each cluster should correspond to a scene object. You have to select the specific parameter
values of the DBSCANalgorithm that seems to produce the best results; but you have to use the same
parameters for the three datasets.

Point_Cloud files are raw outputs. The Point_Cloud files with suffix are the output files.

This program is not perfect. It only got 95%

Here is the


Test and diagnosis: 

Please select:

( ) program has compilation error (Max grade is 40%)

( ) program has run time error (Max grade is 40%)

( ) program runs but no output information provided (Max grade is 40%)

(x) program runs

 

Test results:                       16/20 marks

 

(ok/error)Point_Cloud_1

(ok/error)Point_Cloud_2

(ok/error)Point_Cloud_3

 

(ok/error) synthetic test 1

(ok/error) synthetic test 2

 

Student parameter selection for 3 files:   3/5 (these 5 are included on the 20)

 

comments: Hidden tests failed. Choice of optimal parameters not found. I guess eps = 1.2 and minPts = 10?

 

------------------------------------

Code:                                 79/80 marks

 

Class DBScan                      59/60 marks

findClusters: 30

save & output & file creation: 20

(-10 if no screen output with cluster stats is given (3 for #cluster; 7 for all sizes)).

other methods: 10

 

comments: The counting of cluster sizes in console is partially correct (-1).

 

Class Points3D                    10/10 marks

distance calculation:5

other:5

 

comments:

 

Class Nearest Neighbour      10/10 marks

rangeQuery: 8

rest: 2

 

comments:

 

Extra discount can be given for not enough documentation (up to -5).

comments:

 

------------------------------------

Total mark:   95

(TAs if there is late penalty, compute total mark and then *0.7 - show mark before and after penalty)

 

