------------------------------------------------------------------------
This Java class implements algorithms for solving the Traveling Salesman Problem (TSP) with graphical visualization. It interacts with a GUI (GUI_TSP) to display cities, edges, and tour progress in real-time. The class supports multiple heuristic and optimization methods, including nearest neighbor, minimum spanning tree (MST), and 2-opt local search.


How to start the project?
-Just run the Main.java code

Features

City management:
Add cities with coordinates, reset states, and track visited connections.

Distance calculation:
Compute Euclidean distances between cities and total tour length.

Nearest Neighbor heuristic (nearestN):
Constructs a tour by always visiting the closest unvisited city.

Christofides Algorithm (ChristofidesAlgo):
Begins with a minimum spanning tree (MST) to approximate a solution.

MST is constructed via a greedy approach connecting the nearest unvisited city.

Identifies vertices with odd degrees for potential further optimization.

2-opt optimization (twoopt):
Improves an existing tour by swapping edges to reduce overall distance.

Graphical interface integration:
Draws cities, edges, and updates tour length and computation time on the GUI.
------------------------------------------------------------------------

PROJEKTBEZEICHNUNG:
PROJEKTZWECK:Facharbeit
VERSION oder DATUM:02/2024
WIE IST DAS PROJEKT ZU STARTEN: School project
AUTOR(EN):Serhat Tunca
BENUTZERHINWEISE:

