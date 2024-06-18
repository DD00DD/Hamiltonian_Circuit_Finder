
public class Hamilton {

    private Hamilton() {}
    
    private static Graph g = null;

    private static int totalV;

    private static Walk hamiltonian = null;
  
    public static Walk getHamiltonian(Graph graph) {

        g = graph;
        totalV = g.getTotalVertices();
        hamiltonian = new Walk(totalV+1);
	int[] path = new int[totalV];

	path[0] = 0;
	g.visitV(0);
	foundHamiltonian(path, 0, 1);
	
	if(hamiltonian.isEmpty() == true){
	    return null;
	}
	hamiltonian.addVertex(0);
	return hamiltonian;
    }

    private static boolean foundHamiltonian(int path[], int vertex, int totalvisited) {
	//base case should be ok
	if (totalvisited == totalV){
	   if(g.getEdgeCount(vertex, path[totalvisited-1]) == 1){
		for (int v : path){
		    hamiltonian.addVertex(v);
		}
		return true;
	   }
	   return false;
	}

	for (int i = 1; i < totalV; i++){
	    //check if previous value is neighbours with the following value and if not visited
		if(g.isVisitedV(i) == false && g.getEdgeCount(path[totalvisited-1], i) == 1){ 

		   g.visitV(i);
		   path[totalvisited] = i;
		   if(foundHamiltonian(path, vertex, totalvisited+1) == true){
		   	return true;
		   }

        	   g.unvisitV(i);
		   path[totalvisited] = -1;
		}
	}
	return false;
    }



    public boolean isConnected() {
	 if (isEmpty()) {
                return true;
        }
        resetVisitation();
        DFSvisit(0);
        for (int i = 0; i < totalV; i++) {
                if (!visitedV[i]) {
                        return false;
                }
        }
        return true;
    }

    private void DFSvisit(int vertex) {
	visitedV[vertex] = true;
        for (int i = 0; i < totalV; i++) {
                if (edges[vertex][i] > 0 && !visitedV[i]) {
                        DFSvisit(i);
                }
        }
   } 
}
