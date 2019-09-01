package com.tokopedia.testproject.problems.androidView.graphTraversal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tokopedia.testproject.R;

import java.util.ArrayList;
import java.util.List;

import de.blox.graphview.BaseGraphAdapter;
import de.blox.graphview.Graph;
import de.blox.graphview.GraphView;
import de.blox.graphview.Node;
import de.blox.graphview.energy.FruchtermanReingoldAlgorithm;

public class GraphActivity extends AppCompatActivity {
    private int nodeCount = 1;
    private Node currentNode;
    protected BaseGraphAdapter<ViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        final Graph graph = createGraph();
        setupAdapter(graph);
    }

    private void setupAdapter(final Graph graph) {
        final GraphView graphView = findViewById(R.id.graph2);

        adapter = new BaseGraphAdapter<ViewHolder>(this, R.layout.node, graph) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(View view) {
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, Object data, int position) {

                viewHolder.textView.setText(data.toString());
                traverseAndColorTheGraph(graph, graph.getNode(0), 2, viewHolder, position);


            }
        };

        adapter.setAlgorithm(new FruchtermanReingoldAlgorithm());

        graphView.setAdapter(adapter);
        graphView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentNode = adapter.getNode(position);
                adapter.notifyDataChanged(currentNode);
                Snackbar.make(graphView, "Clicked on " + currentNode.getData().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        /*
         * TODO
         * Given input:
         * 1. a graph that represents a tree (there is no cyclic node),
         * 2. a rootNode, and
         * 3. a target distance,
         * you have to traverse the graph and give the color to each node as below criteria:
         * 1. RootNode is purple
         * 2. Nodes with the distance are less than the target distance are colored green
         * 3. Nodes with the distance are equal to the target distance are colored orange
         * 4. Other Nodes are blue
         */


    }

    private void traverseAndColorTheGraph(Graph graph, Node rootNode, int target, ViewHolder viewHolder, int position) {

        /*for(int i=0;i<graph.getEdges().size();i++) {
            System.out.print(graph.getEdges().get(i).getSource().getData().toString()+"-");
            System.out.println(graph.getEdges().get(i).getDestination().getData().toString());
        }*/

        //Object myroot = graph.getNode(1).getData()

        /*System.out.println("START");
        for(int i=0;i<graph.getEdges().size();i++) {
            System.out.println("MY ROOT: SOURCE: "+graph.getEdges().get(i).getSource().data+" -- DEST: "+graph.getEdges().get(i).getDestination().data+" -- "+graph.getEdges().get(i).getSource().data.equals(rootNode.data) +" ---- "+graph.getEdges().get(i).getDestination().data.equals(graph.getNode(position).data));


            if (graph.getNode(position).data.equals(rootNode.data)) {
                ((CardView) viewHolder.cardView).setCardBackgroundColor(Color.rgb(255, 0, 255));
                //break;
            } else if (graph.getEdges().get(i).getSource().data.equals(rootNode.data) && graph.getEdges().get(i).getDestination().data.equals(graph.getNode(position).data)) {
                ((CardView) viewHolder.cardView).setCardBackgroundColor(Color.GREEN);
                //break;
            }/* else if (position == 3 || position == 4 || position == 5 || position == 6) {
                ((CardView) viewHolder.cardView).setCardBackgroundColor(Color.rgb(255, 165, 0));
                break;
            } else {
                ((CardView) viewHolder.cardView).setCardBackgroundColor(Color.rgb(0, 0, 255));
                break;
            }
        }*/

        ArrayList<ArrayList<Node>> Level = new ArrayList<ArrayList<Node>>();
        ArrayList<Node> Level1 = new ArrayList<Node>();
        ArrayList<Node> sudah = new ArrayList<Node>();
        Level1.add(rootNode);
        sudah.add(rootNode);
        Level.add(Level1);
        for(int m=0;m<target;m++){
            ArrayList<Node> Level3 = new ArrayList<Node>();

            for(int i=0;i<graph.getEdges().size();i++){
                for(int j=0;j<Level.get(m).size();j++) {
                    if (graph.getEdges().get(i).getSource() == Level.get(m).get(j)) {
                        int sudahHitung=0;
                        for(int n=0;n<sudah.size();n++) {
                            if (graph.getEdges().get(i).getDestination() != sudah.get(n)){
                                sudahHitung++;
                            }
                        }
                        if(sudahHitung==sudah.size()) {
                            Level3.add(graph.getEdges().get(i).getDestination());
                            sudah.add(graph.getEdges().get(i).getDestination());
                        }
                    }
                    if (graph.getEdges().get(i).getDestination() == Level.get(m).get(j)) {
                        int sudahHitung=0;
                        for(int n=0;n<sudah.size();n++) {
                            if (graph.getEdges().get(i).getSource() != sudah.get(n)){
                                sudahHitung++;
                            }
                        }
                        if(sudahHitung==sudah.size()) {
                            Level3.add(graph.getEdges().get(i).getSource());
                            sudah.add(graph.getEdges().get(i).getSource());
                        }
                    }
                }
            }
            Level.add(Level3);
        }

        /*for(int i=0;i<graph.getEdges().size();i++){
            if(graph.getEdges().get(i).getSource() == rootNode) {
                Level2.add(graph.getEdges().get(i).getDestination());
            }
            if(graph.getEdges().get(i).getDestination() == rootNode) {
                Level2.add(graph.getEdges().get(i).getSource());
            }
        }
        List<Node> Level3 = new ArrayList<Node>();
        for(int i=0;i<graph.getEdges().size();i++){
            for(int j=0;j<Level2.size();j++) {
                if (graph.getEdges().get(i).getSource() == Level2.get(j) && graph.getEdges().get(i).getDestination() != rootNode) {
                    Level3.add(graph.getEdges().get(i).getDestination());
                }
                if (graph.getEdges().get(i).getDestination() == Level2.get(j) && graph.getEdges().get(i).getSource() != rootNode) {
                    Level3.add(graph.getEdges().get(i).getSource());
                }
            }
        }*/
        ((CardView) viewHolder.cardView).setCardBackgroundColor(Color.rgb(0, 0, 255));

        for(int i=0;i<Level.size();i++){


            System.out.println("=========");
            System.out.println("Level "+i+" : ");
            for(int k=0;k<Level.get(i).size();k++){
                if (graph.getNode(position).data.equals(Level.get(i).get(k).data)  && i==0) {
                    ((CardView) viewHolder.cardView).setCardBackgroundColor(Color.rgb(255, 0, 255));
                }
                else if (graph.getNode(position).data.equals(Level.get(i).get(k).data)  && i==target) {
                    ((CardView) viewHolder.cardView).setCardBackgroundColor(Color.rgb(255, 165, 0));
                }
                else if (graph.getNode(position).data.equals(Level.get(i).get(k).data)  && i<target && i>0) {
                    ((CardView) viewHolder.cardView).setCardBackgroundColor(Color.GREEN);
                }

                System.out.print(Level.get(i).get(k).data+",");
            }
            System.out.println("\n=========");
        }


        }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public Graph createGraph() {
        final Graph graph = new Graph();
        final Node a = new Node(getNodeText());
        final Node b = new Node(getNodeText());
        final Node c = new Node(getNodeText());
        final Node d = new Node(getNodeText());
        final Node e = new Node(getNodeText());
        final Node f = new Node(getNodeText());
        final Node g = new Node(getNodeText());
        final Node h = new Node(getNodeText());
        final Node i = new Node(getNodeText());



        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(b, f);
        graph.addEdge(b, g);
        graph.addEdge(c, d);
        graph.addEdge(c, e);
        graph.addEdge(d, h);
        graph.addEdge(h, i);



        return graph;
    }

    private class ViewHolder {
        TextView textView;
        LinearLayout bgChanged;
        CardView cardView;

        ViewHolder(View view) {
            textView = view.findViewById(R.id.textView);
            bgChanged = view.findViewById(R.id.backgroud);
            cardView = view.findViewById(R.id.card_view);
        }
    }

    protected String getNodeText() {
        return "Node " + nodeCount++;
    }
}
