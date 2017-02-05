package hackerrank.w28;

import java.io.IOError;
import java.io.IOException;
import java.util.*;

/**
 * Created by Muneer on 13-01-2017.
 */
public class ValueOfFriendshipRec {

    static long noOfNodesInComponent = 0;
    static long noOfEdgesInComponent = 0;

    static Graph g;
    static boolean[] vis;
    static long v, from;
    static HashSet<Long> hashSet;

    public static void main(String[] args) {

        try {
            Scanner in = new Scanner(System.in);

            long q = in.nextLong();
            for(long a0 = 0; a0 < q; a0++){
                long n = in.nextLong();
                long m = in.nextLong();
                long x, y;


                g = new Graph(n, m);
                for(long i = 0; i < m; i++)
                {
                    x = in.nextLong() - 1;
                    y = in.nextLong() - 1;
                    g.addEdge(x, y);
                }

                hashSet = new HashSet<>();
                List<Component> componentsList = new ArrayList<Component>();

                vis = new boolean[(int)n];
                for(long v = 0; v < n; v++) {
                    if(!vis[(int)v]) {
                        hashSet.clear();
                        noOfEdgesInComponent = 0;
                        noOfNodesInComponent = 0;
                        dfsUtil(g, vis, (int)v, (int)v, hashSet);
                        componentsList.add(new Component(noOfNodesInComponent, noOfEdgesInComponent));
                        //System.out.prlongln(noOfNodesInComponent + " -> " + noOfEdgesInComponent);
                    }
                }

                Collections.sort(componentsList);
                for (Component component : componentsList) {
                    System.out.println(component.toString());
                }

                ArrayList<Long> redundancyCountList = new ArrayList<>();
                for(Component component: componentsList) {
                    if(component.getNoOfEdges() >= component.getNoOfNodes()) {
                        redundancyCountList.add(component.getNoOfEdges() - (component.getNoOfNodes() - 1));
                    } else {
                        redundancyCountList.add(0L);
                    }
                }

                //gets result without redundancies
                long res = getRes(componentsList);

                long totalNumOfRedundancies = 0;
                long totalRedundantSum = 0;

                //add redundancies
                for(long i = 0; i < componentsList.size(); i++) {
                    totalRedundantSum += componentsList.get((int)i).getRedundancyValue();
                    totalNumOfRedundancies += redundancyCountList.get((int)i);
                }

                //add totalRedundantSum the totalNumOfRedundancies times to res
                res += totalNumOfRedundancies * totalRedundantSum;
                System.out.println(/*"res: " + */res);
            }
        } catch (IOError e) {
            e.printStackTrace();
        }

    }

    private static long getRes(List<Component> componentsList) {
        long res = 0;

        Component component;
        long componentSize, componentVal = 0, prevCompSum = 0;

        for(long ic = 0; ic < componentsList.size(); ic++) {
            component = componentsList.get((int)ic);
            componentVal = 0;
            //-1 because for a graph with n nodes n-1 nodes are sufficient to form a MST
            componentSize = component.getNoOfNodes() - 1;
            for(long i = 0; i < componentSize; i++) {
                componentVal = (i+1) * (i+2);
                component.setRedundancyValue(componentVal);
                res +=  prevCompSum + componentVal;
            }

            prevCompSum += componentVal;
        }

        return res;
    }

    private static void dfsUtil(Graph g, boolean[] vis, int v, int from, HashSet<Long> hashSet) {

        vis[v] = true;
        noOfNodesInComponent++;
        //System.out.print(v + " ");
        hashSet.add((long) v);

        for(long u: g.list.get(v)) {
            if(!vis[(int)u]) {
                //System.out.println((v+1) + "<->" + (u+1));
                noOfEdgesInComponent++;
                dfsUtil(g, vis, (int)u, v, hashSet);
            } else if(vis[(int)u] && u != from && hashSet.contains(u)) {
                //System.out.println((v+1) + "<->" + (u+1));
                noOfEdgesInComponent++;
            }
        }

        hashSet.remove(v);
    }
}
