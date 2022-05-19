package mindustry.ai;

import arc.func.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.world.*;

import java.util.*;

import static mindustry.Vars.*;

public class Astar{
    public static final DistanceHeuristic manhattan = (x1, y1, x2, y2) -> Math.abs(x1 - x2) + Math.abs(y1 - y2);

    private static final Seq<Tile> out = new Seq<>();
    private static final PQueue<Tile> queue = new PQueue<>(200 * 200 / 4, (a, b) -> 0);
    private static float[] costs;
    private static byte[][] rotations;

    public static Seq<Tile> pathfind(Tile from, Tile to, TileHueristic th, Boolf<Tile> passable){
        return pathfind(from.x, from.y, to.x, to.y, th, manhattan, passable);
    }

    public static Seq<Tile> pathfind(int startX, int startY, int endX, int endY, TileHueristic th, Boolf<Tile> passable){
        return pathfind(startX, startY, endX, endY, th, manhattan, passable);
    }

    public static Seq<Tile> pathfind(int startX, int startY, int endX, int endY, TileHueristic th, DistanceHeuristic dh, Boolf<Tile> passable){
        Tiles tiles = world.tiles;

        Tile start = tiles.getn(startX, startY);
        Tile end = tiles.getn(endX, endY);

        GridBits closed = new GridBits(tiles.width, tiles.height);

        if(costs == null || costs.length != tiles.width * tiles.height){
            costs = new float[tiles.width * tiles.height];
        }

        Arrays.fill(costs, 0);

        queue.clear();
        queue.comparator = Structs.comparingFloat(a -> costs[a.array()] + dh.cost(a.x, a.y, end.x, end.y));
        queue.add(start);
        if(rotations == null || rotations.length != world.width() || rotations[0].length != world.height()){
            rotations = new byte[world.width()][world.height()];
        }

        boolean found = false;
        while(!queue.empty()){
            Tile next = queue.poll();
            float baseCost = costs[next.array()];
            if(next == end){
                found = true;
                break;
            }
            closed.set(next.x, next.y);
            for(Point2 point : Geometry.d4){
                int newX = next.x + point.x, newY = next.y + point.y;
                if(Structs.inBounds(newX, newY, tiles.width, tiles.height)){
                    Tile child = tiles.getn(newX, newY);
                    if(passable.get(child)){
                        float newCost = th.cost(next, child) + baseCost;
                        if(!closed.get(child.x, child.y)){
                            closed.set(child.x, child.y);
                            rotations[child.x][child.y] = child.relativeTo(next.x, next.y);
                            costs[child.array()] = newCost;
                            queue.add(child);
                        }
                    }
                }
            }
        }

        out.clear();

        if(!found) return out;

        Tile current = end;
        while(current != start){
            out.add(current);

            byte rot = rotations[current.x][current.y];
            current = tiles.getn(current.x + Geometry.d4x[rot], current.y + Geometry.d4y[rot]);
        }

        out.reverse();

        return out;
    }

    public interface DistanceHeuristic{
        float cost(int x1, int y1, int x2, int y2);
    }

    public interface TileHueristic{
        float cost(Tile tile);

        default float cost(Tile from, Tile tile){
            return cost(tile);
        }
    }
}
