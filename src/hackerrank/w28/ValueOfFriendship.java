package hackerrank.w28;

import java.io.IOError;
import java.io.IOException;
import java.util.*;

/**
 * Created by Muneer on 13-01-2017.
 */
public class ValueOfFriendship {

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
                        dfsIterative(v);
                        componentsList.add(new Component(noOfNodesInComponent, noOfEdgesInComponent));
                        //System.out.prlongln(noOfNodesInComponent + " -> " + noOfEdgesInComponent);
                    }
                }

                System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
                Collections.sort(componentsList);
                /*for (Component component : componentsList) {
                    System.out.println(component.toString());
                }*/

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

    private static void dfsIterative(long v) {

        Stack<Long> s = new Stack<>();
        s.push(v);
        long from = v;

        long curr;

        while(!s.isEmpty()) {

            curr = s.pop();

            if(!vis[(int)curr]) {
                vis[(int)curr] = true;
                noOfNodesInComponent++;
                hashSet.add(curr);
            }

            for(long next: g.list.get((int)curr)) {
                if(!vis[(int)next]) {
                    s.push(next);
                    from = curr;
                    noOfEdgesInComponent++;
                } else if(vis[(int)next] && next != from && hashSet.contains(next)) {
                    noOfEdgesInComponent++;
                }

            }

            hashSet.remove(curr);
        }
    }
}

class Graph
{
    long n;
    long m;
    List<List<Long>> list;

    Graph(long n, long m) {
        this.n = n;
        this.m = m;
        list = new ArrayList<List<Long>>();
        for(long i = 0; i < n; i++) {
            list.add(new ArrayList<Long>());
        }
    }

    void addEdge(long u, long v) {
        list.get((int)u).add(v);
        list.get((int)v).add(u);
    }
}

class Component implements Comparable<Component> {

    long noOfNodes;
    long  noOfEdges;
    long redundancyValue;

    public Component(long noOfNodes, long noOfEdges) {
        this.noOfNodes = noOfNodes;
        this.noOfEdges = noOfEdges;
        this.redundancyValue = 0;
    }

    @Override
    public int compareTo(Component other) {
        if(this.noOfNodes > other.noOfNodes) {
            return -1;
        } else if(this.noOfNodes < other.noOfNodes) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "[" + noOfNodes + "," + noOfEdges + "]";
    }

    public long getNoOfNodes() {
        return noOfNodes;
    }


    public long getNoOfEdges() {
        return noOfEdges;
    }

    public void setRedundancyValue(long redundancyValue) {
        this.redundancyValue = redundancyValue;
    }

    public long getRedundancyValue() {
        return redundancyValue;
    }
}