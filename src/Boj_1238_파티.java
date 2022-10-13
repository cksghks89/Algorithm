/*
    Boj 1238. 파티
    level. gold 3
    solved by 송찬환
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1238_파티 {
    static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N + 1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Node(e, w));
        }

        int[] time = new int[N + 1];

        for(int i = 1; i < N + 1; i++){
            int[] dist = new int[N + 1];
            boolean[] visited = new boolean[N + 1];

            dijkstra(i, dist, visited);

            if(i == X){
                for(int j = 1; j < N + 1; j++){
                    time[j] += dist[j];
                }
                continue;
            }
            time[i] += dist[X];
        }

        System.out.println(Arrays.stream(time).max().getAsInt());
    }

    static void dijkstra(int s, int[] dist, boolean[] visited) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>((x, y) -> x.weight - y.weight);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        pq.offer(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.to]) {
                continue;
            }
            visited[cur.to] = true;

            for (int i = 0; i < graph.get(cur.to).size(); i++) {
                Node adjNode = graph.get(cur.to).get(i);

                if (dist[adjNode.to] > cur.weight + adjNode.weight) {
                    dist[adjNode.to] = cur.weight + adjNode.weight;
                    pq.offer(new Node(adjNode.to, dist[adjNode.to]));
                }
            }
        }
    }
}
