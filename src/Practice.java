import java.util.HashSet;
import java.util.Set;

/**
 * A utility class providing various graph traversal methods using DFS.
 */
public class Practice {

  /**
   * Prints the value of every vertex reachable from the given starting vertex,
   * including the starting vertex itself. Each value is printed on a separate line.
   * The order of printing is unimportant.
   *
   * Each vertex's value should be printed only once, even if it is reachable via multiple paths.
   * It is guaranteed that no two vertices will have the same value.
   *
   * If the given vertex is null, this method prints nothing.
   *
   * @param vertex The starting vertex for the traversal.
   */
  public <T> void printVertexVals(Vertex<T> vertex) {
    Set<Vertex<T>> visited = new HashSet<>();
    printVertexVals(vertex, visited);
  }

  public <T> void printVertexVals(Vertex<T> vertex, Set<Vertex<T>> visited) {
    if (vertex == null || visited.contains(vertex)) return;

    System.out.println(vertex.data);
    visited.add(vertex);

    for (Vertex<T> neighbor : vertex.neighbors) printVertexVals(neighbor, visited);
  }

  /**
   * Returns a set of all vertices reachable from the given starting vertex,
   * including the starting vertex itself.
   *
   * If the given vertex is null, an empty set is returned.
   *
   * @param vertex The starting vertex for the traversal.
   * @return A set containing all reachable vertices, or an empty set if vertex is null.
   */
  public <T> Set<Vertex<T>> reachable(Vertex<T> vertex) {
    Set<Vertex<T>> reachable = new HashSet<>();
    reachableHelper(vertex, reachable);
    return reachable;
  }

  public <T> void reachableHelper(Vertex<T> vertex, Set<Vertex<T>> reachable) {
    if (vertex == null || reachable.contains(vertex)) return;

    reachable.add(vertex);

    for (Vertex<T> neighbor : vertex.neighbors) reachableHelper(neighbor, reachable);
  }

  /**
   * Returns the maximum value among all vertices reachable from the given starting vertex,
   * including the starting vertex itself.
   *
   * If the given vertex is null, the method returns Integer.MIN_VALUE.
   *
   * @param vertex The starting vertex for the traversal.
   * @return The maximum value of any reachable vertex, or Integer.MIN_VALUE if vertex is null.
   */
  public int max(Vertex<Integer> vertex) {
    Set<Vertex<Integer>> visited = new HashSet<>();
    return max(vertex, visited);
  }

  public int max(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) {
    if (vertex == null || visited.contains(vertex)) return Integer.MIN_VALUE;

    int max = vertex.data;
    visited.add(vertex);

    for (Vertex<Integer> neighbor : vertex.neighbors) max = Math.max(max, max(neighbor, visited));

    return max;
  }

  /**
   * Returns a set of all leaf vertices reachable from the given starting vertex.
   * A vertex is considered a leaf if it has no outgoing edges (no neighbors).
   *
   * The starting vertex itself is included in the set if it is a leaf.
   *
   * If the given vertex is null, an empty set is returned.
   *
   * @param vertex The starting vertex for the traversal.
   * @return A set containing all reachable leaf vertices, or an empty set if vertex is null.
   */
  public <T> Set<Vertex<T>> leaves(Vertex<T> vertex) {
    Set<Vertex<T>> leaves = new HashSet<>();
    Set<Vertex<T>> visited = new HashSet<>();
    leaves(vertex, visited, leaves);
    return leaves;
  }

  public <T> void leaves(Vertex<T> vertex, Set<Vertex<T>> visited, Set<Vertex<T>> leaves) {
    if (vertex == null || visited.contains(vertex)) return;

    visited.add(vertex);
    
    if(vertex.neighbors == null || vertex.neighbors.isEmpty()) leaves.add(vertex);

    for (Vertex<T> neighbor : vertex.neighbors) leaves(neighbor, visited, leaves);
  }


  /**
   * Returns whether all reachable vertices (including the starting vertex) hold
   * odd values. Returns false if at least one reachable vertex (including the starting vertex)
   * holds an even value.
   * 
   * If the given vertex is null, returns true.
   * 
   * @param vertex The starting vertex
   * @return true if all reachable vertices hold odd values, false otherwise
   */
  public boolean allOdd(Vertex<Integer> vertex) {
    Set<Vertex<Integer>> visited = new HashSet<>();
    return allOdd(vertex, visited);
  }

  public boolean allOdd(Vertex<Integer> vertex, Set<Vertex<Integer>> visited) {
    if (vertex == null || visited.contains(vertex)) return true;

    visited.add(vertex);
    boolean isOdd = true;
    if (vertex.data % 2 == 0) isOdd = false;

    for (Vertex<Integer> neighbor : vertex.neighbors) isOdd = isOdd && allOdd(neighbor, visited);
    return isOdd;
  }

  /**
   * Determines whether there exists a strictly increasing path from the given start vertex
   * to the target vertex.
   *
   * A path is strictly increasing if each visited vertex has a value strictly greater than
   * (not equal to) the previous vertex in the path.
   *
   * If either start or end is null, a NullPointerException is thrown.
   *
   * @param start The starting vertex.
   * @param end The target vertex.
   * @return True if a strictly increasing path exists, false otherwise.
   * @throws NullPointerException if either start or end is null.
   */
  public boolean hasStrictlyIncreasingPath(Vertex<Integer> start, Vertex<Integer> end) throws NullPointerException {
    if (start == null || end == null) throw new NullPointerException();
    Set<Vertex<Integer>> visited = new HashSet<>();
    return hasStrictlyIncreasingPath(start, end, visited);
  }

  public boolean hasStrictlyIncreasingPath(Vertex<Integer> start, Vertex<Integer> end, Set<Vertex<Integer>> visited) {
    if (start == null || visited.contains(start)) return false;
    visited.add(start);
    if (start.equals(end)) return true;
    
    for (Vertex<Integer> neighbor : start.neighbors) {
      boolean increasing = start.data < neighbor.data;
      boolean increasingPath = hasStrictlyIncreasingPath(neighbor, end, visited);
      if (increasing && increasingPath) return true;
    } 

    return false;
  }
}
